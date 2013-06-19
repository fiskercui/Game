package com.road.pitaya;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.road.pitaya.cache.AccountLoader;
import com.road.pitaya.cache.AccountWriter;
import com.road.pitaya.cache.CacherFactory;
import com.road.pitaya.cache.CacherManager;
import com.road.pitaya.cache.CacherConfiguration;
import com.road.pitaya.cache.core.ICacher;
import com.road.pitaya.dao.DaoManager;
import com.road.pitaya.database.DBPoolManager;
import com.road.pitaya.entity.AccountInfo;

public class TestEHCache
{
    public static void main(String[] args) throws InterruptedException
    {
        test();
        test2();

        testget();
        testlist();

        testlistAll();
        testdelete();

        while (true)
        {
            Thread.sleep(1000);
        }
    }

    private static void testdelete()
    {
        System.out.println("=======================test testdelete=========");
        CacherManager.getInstance().getCacher(AccountInfo.class, Long.class).delete(3L);
        System.out.println("=======================test over================");
    }

    private static void testlistAll()
    {
        System.out.println("=======================test testlistAll=========");
        List<AccountInfo> accountInfos = (List<AccountInfo>) CacherManager.getInstance()
                .getCacher(AccountInfo.class, Long.class).getAll();
        for (AccountInfo accountInfo : accountInfos)
        {
            System.out.println(accountInfo.toString());
        }
        System.out.println("=======================test over================");
    }

    /**
     * 
     */
    private static void testlist()
    {
        System.out.println("=======================test testlist=========");
        List<Long> keys = new ArrayList<Long>();
        keys.add(2L);
        keys.add(3L);
        List<AccountInfo> accountInfos = CacherManager.getInstance().getCacher(AccountInfo.class, Long.class)
                .getList(keys);

        for (AccountInfo accountInfo : accountInfos)
        {
            System.err.println(accountInfo.toString());
            accountInfo.setAccountName("test 0000");
            CacherManager.getInstance().getCacher(AccountInfo.class, Long.class).put(accountInfo.getId(), accountInfo);
        }
        System.out.println("=======================test over================");
    }

    public static void testget()
    {
        AccountInfo accountInfo = CacherManager.getInstance().getCacher(AccountInfo.class, Long.class).get(3L);
        System.err.println(accountInfo.toString());
    }

    public static void test()
    {


        String path = "F:/New_framework/trunk/Test/config/Text.xml";
        File f = new File(path);
        SAXReader reader = new SAXReader();
        try
        {
            Document doc = reader.read(f);
            Element root = doc.getRootElement();
            DBPoolManager.getInstance().initWithDbXML(root);
        }
        catch (DocumentException e)
        {
            e.printStackTrace();
        }

        DBPoolManager.getInstance().start();
    }

    public static void test2()
    {
        CacherConfiguration cacheConfiguration = new CacherConfiguration("account");
        ICacher<Long, AccountInfo> cacher = CacherFactory.createCacher(new AccountLoader(), new AccountWriter(DaoManager.getAccountDao()), cacheConfiguration);
        CacherManager.getInstance().putCacher(AccountInfo.class, cacher);
        // 应该交由组件管理器去启动
        CacherManager.getInstance().start();
    }

}
