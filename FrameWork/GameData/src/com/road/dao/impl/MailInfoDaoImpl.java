/**
 * All rights reserved. This material is confidential and proprietary to 7ROAD
 */
package com.road.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.road.dao.IMailInfoDao;
import com.road.entity.info.MailInfo;
import com.road.entity.info.PlayerInfo;
import com.road.pitaya.database.BaseDao;
import com.road.pitaya.database.DBParameter;
import com.road.pitaya.database.pool.DBHelper;

/**
 * 
 * @author yong.xu
 * @date 2013-5-28
 * @version
 * 
 */
public class MailInfoDaoImpl extends BaseDao<MailInfo> implements IMailInfoDao
{
    public MailInfoDaoImpl(DBHelper helper)
    {
        super(helper);
    }

    @Override
    public boolean add(MailInfo mailInfo)
    {
        boolean result = false;
        String sql = "insert into t_u_mail('SendDate','ReadDate','SenderUserID','SenderNickname','ReceiverUserID','ReceiverNickname','Type','Title','Content','IsExist','ValidPeriod','Gold','IsGoldGet','Money','IsMoneyGet','Annex1','Annex1Name','IsAnnex1Get','Annex2','Annex2Name','IsAnnex2Get','Annex3','Annex3Name','IsAnnex3Get','Annex4','Annex4Name','IsAnnex4Get','Annex5','Annex5Name','IsAnnex5Get','AnnexRemark')  VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
        Map<Integer, DBParameter> para = new HashMap<Integer, DBParameter>();
        int index = 0;
        para.put(++index,
                new DBParameter(Types.TIMESTAMP, mailInfo.getSendDate()));
        para.put(++index,
                new DBParameter(Types.TIMESTAMP, mailInfo.getReadDate()));
        para.put(++index,
                new DBParameter(Types.INTEGER, mailInfo.getSenderUserID()));
        para.put(++index,
                new DBParameter(Types.VARCHAR, mailInfo.getSenderNickname()));
        para.put(++index,
                new DBParameter(Types.INTEGER, mailInfo.getReceiverUserID()));
        para.put(++index,
                new DBParameter(Types.VARCHAR, mailInfo.getReceiverNickname()));
        para.put(++index, new DBParameter(Types.INTEGER, mailInfo.getType()));
        para.put(++index, new DBParameter(Types.VARCHAR, mailInfo.getTitle()));
        para.put(++index, new DBParameter(Types.VARCHAR, mailInfo.getContent()));
        para.put(++index, new DBParameter(Types.TINYINT, mailInfo.getIsExist()));
        para.put(++index,
                new DBParameter(Types.SMALLINT, mailInfo.getValidPeriod()));
        para.put(++index, new DBParameter(Types.INTEGER, mailInfo.getGold()));
        para.put(++index,
                new DBParameter(Types.TINYINT, mailInfo.getIsGoldGet()));
        para.put(++index, new DBParameter(Types.INTEGER, mailInfo.getMoney()));
        para.put(++index,
                new DBParameter(Types.TINYINT, mailInfo.getIsMoneyGet()));
        para.put(++index, new DBParameter(Types.INTEGER, mailInfo.getAnnex1()));
        para.put(++index,
                new DBParameter(Types.VARCHAR, mailInfo.getAnnex1Name()));
        para.put(++index,
                new DBParameter(Types.TINYINT, mailInfo.getIsAnnex1Get()));
        para.put(++index, new DBParameter(Types.INTEGER, mailInfo.getAnnex2()));
        para.put(++index,
                new DBParameter(Types.VARCHAR, mailInfo.getAnnex2Name()));
        para.put(++index,
                new DBParameter(Types.TINYINT, mailInfo.getIsAnnex2Get()));
        para.put(++index, new DBParameter(Types.INTEGER, mailInfo.getAnnex3()));
        para.put(++index,
                new DBParameter(Types.VARCHAR, mailInfo.getAnnex3Name()));
        para.put(++index,
                new DBParameter(Types.TINYINT, mailInfo.getIsAnnex3Get()));
        para.put(++index, new DBParameter(Types.INTEGER, mailInfo.getAnnex4()));
        para.put(++index,
                new DBParameter(Types.VARCHAR, mailInfo.getAnnex4Name()));
        para.put(++index,
                new DBParameter(Types.TINYINT, mailInfo.getIsAnnex4Get()));
        para.put(++index, new DBParameter(Types.INTEGER, mailInfo.getAnnex5()));
        para.put(++index,
                new DBParameter(Types.VARCHAR, mailInfo.getAnnex5Name()));
        para.put(++index,
                new DBParameter(Types.TINYINT, mailInfo.getIsAnnex5Get()));
        para.put(++index,
                new DBParameter(Types.VARCHAR, mailInfo.getAnnexRemark()));
        result = getDBHelper().execNoneQuery(sql, para) > -1 ? true : false;
        return result;
    }

    @Override
    public boolean update(MailInfo mailInfo)
    {
        boolean result = false;
        String sql = "update t_u_mail set SendDate=?,ReadDate=?,SenderUserID=?,SenderNickname=?,ReceiverUserID=?,ReceiverNickname=?,Type=?,Title=?,Content=?,IsExist=?,ValidPeriod=?,Gold=?,IsGoldGet=?,Money=?,IsMoneyGet=?,Annex1=?,Annex1Name=?,IsAnnex1Get=?,Annex2=?,Annex2Name=?,IsAnnex2Get=?,Annex3=?,Annex3Name=?,IsAnnex3Get=?,Annex4=?,Annex4Name=?,IsAnnex4Get=?,Annex5=?,Annex5Name=?,IsAnnex5Get=?,AnnexRemark=? where MailID = ?;";
        Map<Integer, DBParameter> para = new HashMap<Integer, DBParameter>();
        int index = 0;
        para.put(++index,
                new DBParameter(Types.TIMESTAMP, mailInfo.getSendDate()));
        para.put(++index,
                new DBParameter(Types.TIMESTAMP, mailInfo.getReadDate()));
        para.put(++index,
                new DBParameter(Types.INTEGER, mailInfo.getSenderUserID()));
        para.put(++index,
                new DBParameter(Types.VARCHAR, mailInfo.getSenderNickname()));
        para.put(++index,
                new DBParameter(Types.INTEGER, mailInfo.getReceiverUserID()));
        para.put(++index,
                new DBParameter(Types.VARCHAR, mailInfo.getReceiverNickname()));
        para.put(++index, new DBParameter(Types.INTEGER, mailInfo.getType()));
        para.put(++index, new DBParameter(Types.VARCHAR, mailInfo.getTitle()));
        para.put(++index, new DBParameter(Types.VARCHAR, mailInfo.getContent()));
        para.put(++index, new DBParameter(Types.TINYINT, mailInfo.getIsExist()));
        para.put(++index,
                new DBParameter(Types.SMALLINT, mailInfo.getValidPeriod()));
        para.put(++index, new DBParameter(Types.INTEGER, mailInfo.getGold()));
        para.put(++index,
                new DBParameter(Types.TINYINT, mailInfo.getIsGoldGet()));
        para.put(++index, new DBParameter(Types.INTEGER, mailInfo.getMoney()));
        para.put(++index,
                new DBParameter(Types.TINYINT, mailInfo.getIsMoneyGet()));
        para.put(++index, new DBParameter(Types.INTEGER, mailInfo.getAnnex1()));
        para.put(++index,
                new DBParameter(Types.VARCHAR, mailInfo.getAnnex1Name()));
        para.put(++index,
                new DBParameter(Types.TINYINT, mailInfo.getIsAnnex1Get()));
        para.put(++index, new DBParameter(Types.INTEGER, mailInfo.getAnnex2()));
        para.put(++index,
                new DBParameter(Types.VARCHAR, mailInfo.getAnnex2Name()));
        para.put(++index,
                new DBParameter(Types.TINYINT, mailInfo.getIsAnnex2Get()));
        para.put(++index, new DBParameter(Types.INTEGER, mailInfo.getAnnex3()));
        para.put(++index,
                new DBParameter(Types.VARCHAR, mailInfo.getAnnex3Name()));
        para.put(++index,
                new DBParameter(Types.TINYINT, mailInfo.getIsAnnex3Get()));
        para.put(++index, new DBParameter(Types.INTEGER, mailInfo.getAnnex4()));
        para.put(++index,
                new DBParameter(Types.VARCHAR, mailInfo.getAnnex4Name()));
        para.put(++index,
                new DBParameter(Types.TINYINT, mailInfo.getIsAnnex4Get()));
        para.put(++index, new DBParameter(Types.INTEGER, mailInfo.getAnnex5()));
        para.put(++index,
                new DBParameter(Types.VARCHAR, mailInfo.getAnnex5Name()));
        para.put(++index,
                new DBParameter(Types.TINYINT, mailInfo.getIsAnnex5Get()));
        para.put(++index,
                new DBParameter(Types.VARCHAR, mailInfo.getAnnexRemark()));
        para.put(++index,
                new DBParameter(Types.VARCHAR, mailInfo.getAnnexRemark()));
        para.put(++index, new DBParameter(Types.INTEGER, mailInfo.getMailID()));
        result = getDBHelper().execNoneQuery(sql, para) > -1 ? true : false;
        return result;
    }

    @Override
    public boolean delete(MailInfo mailInfo)
    {
        boolean result = false;
        String sql = "delete from t_u_mail where MailID = ?";
        Map<Integer, DBParameter> para = new HashMap<Integer, DBParameter>();
        int index = 0;
        para.put(++index, new DBParameter(Types.INTEGER, mailInfo.getMailID()));
        result = getDBHelper().execNoneQuery(sql, para) > -1 ? true : false;
        return result;
    }

    @Override
    public boolean addOrUpdate(MailInfo mailInfo)
    {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean deleteByKey(Object... ids)
    {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public MailInfo getByKey(Object... ids)
    {
        String sql = "select * from t_u_mail where MailID=?;";
        Map<Integer, DBParameter> para = new HashMap<Integer, DBParameter>();
        para.put(1, new DBParameter(Types.INTEGER, ids[0]));
        ResultSet rs = getDBHelper().execQuery(sql, para);
        List<MailInfo> mailInfoList = rsToEntityList(rs);
        if (mailInfoList.size() > 0)
        {
            return mailInfoList.get(0);
        }
        return null;
    }

    @Override
    public List<MailInfo> listAll()
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean addOrUpdateBatch(List<MailInfo> mailInfoList)
    {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean deleteBatch(List<MailInfo> mailInfoList)
    {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    protected MailInfo rsToEntity(ResultSet rs) throws SQLException
    {
        MailInfo mailInfo = new MailInfo();
        mailInfo.setMailID(rs.getInt("MailID"));
        mailInfo.setSendDate(rs.getTimestamp("SendDate"));
        mailInfo.setReadDate(rs.getTimestamp("ReadDate"));
        mailInfo.setSenderUserID(rs.getInt("SenderUserID"));
        mailInfo.setSenderNickname(rs.getString("SenderNickname"));
        mailInfo.setReceiverUserID(rs.getInt("ReceiverUserID"));
        mailInfo.setReceiverNickname(rs.getString("ReceiverNickname"));
        mailInfo.setTitle(rs.getString("Title"));
        mailInfo.setContent(rs.getString("Content"));
        mailInfo.setIsExist(rs.getBoolean("IsExist"));
        mailInfo.setValidPeriod(rs.getInt("ValidPeriod"));
        mailInfo.setGold(rs.getInt("Gold"));
        mailInfo.setIsGoldGet(rs.getBoolean("IsGoldGet"));
        mailInfo.setMoney(rs.getInt("Money"));
        mailInfo.setIsMoneyGet(rs.getBoolean("IsMoneyGet"));
        mailInfo.setAnnex1(rs.getInt("Annex1"));
        mailInfo.setAnnex1Name(rs.getString("Annex1Name"));
        mailInfo.setIsAnnex1Get(rs.getBoolean("IsAnnex1Get"));
        mailInfo.setAnnex2(rs.getInt("Annex2"));
        mailInfo.setAnnex2Name(rs.getString("Annex2Name"));
        mailInfo.setIsAnnex2Get(rs.getBoolean("IsAnnex2Get"));
        mailInfo.setAnnex3(rs.getInt("Annex3"));
        mailInfo.setAnnex3Name(rs.getString("Annex3Name"));
        mailInfo.setIsAnnex3Get(rs.getBoolean("IsAnnex3Get"));
        mailInfo.setAnnex4(rs.getInt("Annex4"));
        mailInfo.setAnnex4Name(rs.getString("Annex4Name"));
        mailInfo.setIsAnnex4Get(rs.getBoolean("IsAnnex4Get"));
        mailInfo.setAnnex5(rs.getInt("Annex5"));
        mailInfo.setAnnex5Name(rs.getString("Annex5Name"));
        mailInfo.setIsAnnex5Get(rs.getBoolean("IsAnnex5Get"));
        mailInfo.setAnnexRemark(rs.getString("AnnexRemark"));
        return mailInfo;
    }

}
