/**
 * Date: 2013-5-27
 * 
 * Copyright (C) 2013-2015 7Road. All rights reserved.
 */

package com.road.pitaya.web.login;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.security.interfaces.RSAPrivateKey;
import java.sql.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.road.bll.DaoManager;
import com.road.dota.component.inf.IUserLoginComponent;
import com.road.entity.AccountInfo;
import com.road.pitaya.component.ComponentManager;
import com.road.pitaya.web.BaseHandlerServlet;
import com.road.util.ConfigWrapper;
import com.road.util.RSAUtil;

/**
 * 用户登陆第二步，通过玩家帐号进行登录
 * 
 * @author yip
 */
public class LoginServlet extends BaseHandlerServlet
{

    /**
     * 
     */
    private static final long serialVersionUID = -6030822097107708571L;

    /**
     * 日志
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(LoginServlet.class);
    /**
     * 
     */
    private static Map<String, RSAPrivateKey> privateKeyMap = new ConcurrentHashMap<String, RSAPrivateKey>();

    private synchronized static RSAPrivateKey genPrivateKey(String site)
    {
        try
        {
            // String priModString = WebConfig.initPrivateModulus(site);
            String priModString = ConfigWrapper.getValue("PrivateModulus.".concat(site));
            // String priPriExpString = WebConfig.initPrivateExponent(site);
            String priPriExpString = ConfigWrapper.getValue("PrivateExponent.".concat(site));
            if (priModString == null || priPriExpString == null)
            {
                return null;
            }
            byte[] priModBytesNew = Base64.decodeBase64(priModString);
            byte[] priPriExpBytesNew = Base64.decodeBase64(priPriExpString);
            RSAPrivateKey privateKey = RSAUtil.generateRSAPrivateKey(priModBytesNew, priPriExpBytesNew);
            privateKeyMap.put(site, privateKey);
            return privateKey;
        }
        catch (Exception e)
        {
            LOGGER.error("生成密钥异常  site : " + site, e);
            return null;
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.road.pitaya.web.IHandlerCmd#execute(java.lang.String)
     */
    @Override
    public String execute(String jsonString, String... strings)
    {
        String ip = null;
        if (strings.length == 1)
        {
            ip = strings[0];
        }
        LoginRecvBean accountInfo = gson.fromJson(jsonString, LoginRecvBean.class);
        accountInfo.setIp(ip);
        return process(accountInfo);// 处理传入的登录信息
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException
    {
        String jsonString = request.getParameter("params");
        String result = execute(jsonString, getIp(request));
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html");
        response.setStatus(HttpServletResponse.SC_OK);
        PrintWriter out = response.getWriter();
        out.print(result);
        out.flush();
        out.close();
    }

    /**
     * 处理传入的登录信息
     * 
     * @param recv
     * @return
     */
    private String process(LoginRecvBean recv)
    {
        String message = LoginMsgType.LOGINWAITE_MAX;
        // 检查收包
        if (recv == null)
        {
            message = LoginMsgType.NO_MESSAGE;
            return getResultBeanString(false, message, 0);
        }

        // 检测站点参数
        String loginKey = checkLoginSite(recv.getSite());
        if (loginKey == null)
        {
            return getResultBeanString(false, LoginMsgType.SITE_ERROR, 0);
        }
        // 通过RSA加密的P参数错误
        String[] pList = checkLoginP(recv.getP(), recv.getSite());
        if (pList == null)
        {
            message = LoginMsgType.P_ERROR;
            return getResultBeanString(false, message, 0);
        }
        // 检测key值，检查帐号密码
        String userName = pList[0]; // 帐号
        String passWords = pList[1];// 新密码

        // 查看密码是否和第一步的密码一致
        if (!checkLogin(userName, passWords, recv.getSite()))
        {
            message = LoginMsgType.LOGINKEY_ERROR;
            return getResultBeanString(false, message, 0);
        }
        // 第三部分为userId
        int userId = Integer.parseInt(pList[3]);
        AccountInfo account = updateDb(userId, userName, passWords, recv.getSite(), recv.getIp());
        if (account == null)
        {
            message = LoginMsgType.SAVEDB_ERROR;
            return getResultBeanString(false, message, userId);
        }
        // 帐号未激活，通知激活
        if (account.getIsActived() == false)
        {
            message = LoginMsgType.SUCCEED_NOVISUALIZE;
            return getResultBeanString(false, message, account.getUserId());
        }
//        帐号被禁
        else if (account.getForbidDate().after(new Date(System.currentTimeMillis())))
        {
            ((IUserLoginComponent) ComponentManager.getInstance().getComponent("UserManager")).removeUser(userName
                    .concat("_").concat(recv.getSite()));
            return getResultBeanString(false, LoginMsgType.USERDISABLE_ERROR, userId);
        }
        else
        {
//这里可以用来分配ip和端口，现在用配置来实现
//            创建角色缓存，TODO:要实现这个缓存5分钟后自动消失。
            ((IUserLoginComponent)ComponentManager.getInstance().getComponent("UserManager")).createOnline(userId, passWords.toUpperCase());
            String address = ConfigWrapper.getValue("ServerAddress");
            String port  = ConfigWrapper.getValue("port");
            LoginResultBean result = new LoginResultBean(true, LoginMsgType.SUCCEED, userId);
            result.setAddress(address);
            result.setPort(port);
            return gson.toJson(result);
        }

        // http://127.0.0.1:8088/Login?params={} 测试网址TODO:中控和登录使用不同的端口，配置文件实现
    }

    /**
     * @param userId
     * @param userName
     * @param passWords
     * @param site
     * @param activeIp
     * @return
     */
    private AccountInfo updateDb(int userId, String userName, String passWords, String site, String activeIp)
    {
        AccountInfo info = DaoManager.getAccountInfoDao().getAccountInfo(userName, site);
        if (info != null)
        {
            // 检测客户端传进的userid匹配
            if (info.getUserId() == userId)
            {
                info.setLastLoginIP(activeIp);
                ((IUserLoginComponent) ComponentManager.getInstance().getComponent("UserManager"))
                        .updateLoginUser(info);
            }
            else
            {
                info = null;
                String msg = "当前数据库中用户存在，但客户端在调用LoginSelectList时获取的UserId不对，请检查客户端";
                LOGGER.error(msg);
            }
        }
        return info;
    }

    /**
     * @param userName
     * @param passWords
     * @param site
     * @return
     */
    private boolean checkLogin(String userName, String passWords, String site)
    {
        return ((IUserLoginComponent) ComponentManager.getInstance().getComponent("UserManager")).checkLogin(userName
                .concat("_").concat(site), passWords);
    }

    /**
     * 
     * @param site
     * @return
     */
    private String checkLoginSite(String site)
    {
        return null;
    }

    /**
     * 检查加密后的字符串
     * 
     * @param string
     * @param site
     * @return
     */
    private String[] checkLoginP(String pString, String site)
    {
        if ((pString == null) || (pString.equals("")))
            return null;
        String str = null;

        try
        {
            RSAPrivateKey privateKey = privateKeyMap.get(site);
            if (privateKey == null)
            {
                privateKey = genPrivateKey(site);
            }
            if (privateKey == null)
            {
                LOGGER.error("site : " + site + "privateKey is null");
            }
            byte[] enRaw = Base64.decodeBase64(pString.getBytes());
            byte[] data = RSAUtil.decrypt(privateKey, enRaw);
            if (data == null)
            {
                String msg = "RSA解密出错:解密前的数据" + pString + ",site=" + site + "";
                LOGGER.error(msg);
                return null;
            }
            else
            {
                str = new String(data);
            }
        }
        catch (Exception e1)
        {
            LOGGER.error("Login checkLoginP", e1);
            return null;
        }

        String[] strList = str.split("\\|");
        if (strList.length != 4)
        {
            return null;
        }
        for (int i = 0; i < 4; i++)
        {
            if ((strList[i] == null) || (strList[i].equals("")))
            {
                return null;
            }
            if (i == 0)
            {
                try
                {
                    strList[i] = java.net.URLDecoder.decode(strList[i], "UTF-8");
                }
                catch (UnsupportedEncodingException e)
                {
                    LOGGER.error("编码异常", e);
                    return null;
                }
            }
        }
        return strList;
    }

    /**
     * 获得Json结果
     * 
     * @param result
     *            是否成功
     * @param message
     *            反馈消息
     * @param userId
     *            玩家ID
     * @return
     */
    public String getResultBeanString(boolean result, String message, int userId)
    {
        return gson.toJson(new LoginResultBean(result, message, userId));
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.road.pitaya.web.IHandlerCmd#execute(java.lang.String)
     */
    @Override
    public String execute(String jsonString)
    {
        return null;
    }

    /**
     * 登录消息类型
     * 
     * @author yip
     */
    public interface LoginMsgType
    {
        public static String SITE_ERROR = "Error SITE";
        public static String NO_MESSAGE = "null Message";
        // P参数错误
        public static String P_ERROR = "Parameter P Data Error";
        // 登陆Key值错误
        public static String LOGINKEY_ERROR = "Login Invalidation";
        // 新增到数据库失败
        public static String SAVEDB_ERROR = "Data Update Error";
        // 当前用户已禁号
        public static String USERDISABLE_ERROR = "User IS Forbid";
        // 登陆成功
        public static String SUCCEED = "Login Succeed";
        // Web服务加载用户信息失败
        public static String FAILED = "Login Failed";
        // 登陆成功，但形象未初始化
        public static String SUCCEED_NOVISUALIZE = "Login Succeed No Visualize";
        // 登陆入口已关闭
        public static String LOGIN_CLOSE = "Login Enter Is Close";
        // 登陆人数已达上限
        public static String LOGINWAITE_MAX = "Login Max";
        // 检测到客户端异常,2分钟以后才能再进行登陆
        public static String CLIENT_EXCEPTION = "Client Exception";
        // 检测到IP被封
        public static String P_FILTER = "Ip Is Forbid ";
        // 检测到客户端用外挂,30分钟以后才能登陆
        public static String CLIENT_PLUG_EXCEPTION = "Client Plug Exception";
    }
}
