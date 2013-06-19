/**
 * Date: 2013-5-22
 * 
 * Copyright (C) 2013-2015 7Road. All rights reserved.
 */

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
import com.road.pitaya.cache.CacherManager;
import com.road.pitaya.cache.CacherConfiguration;
import com.road.pitaya.cache.core.ICacher;
import com.road.pitaya.cache.core.abcache.AbCacher;
import com.road.pitaya.dao.DaoManager;
import com.road.pitaya.database.DBPoolManager;
import com.road.pitaya.entity.AccountInfo;

/**
 * 
 * @author jinjin.chen
 */
public class TestAbCacher
{
    public static void main(String[] args)
    {

        test();
        test01();
        testget();
        testlist();

        testlistAll();

        testdelete();

    }

    /**
     * 
     */
    private static void testdelete()
    {
        System.out.println("=======================test testdelete=========");
        CacherManager.getInstance().getCacher(AccountInfo.class, Long.class).delete(2L);
        System.out.println("=======================test over================");
    }

    private static void testlistAll()
    {
        System.out.println("=======================test testlistAll=========");
        List<AccountInfo> accountInfos = CacherManager.getInstance().getCacher(AccountInfo.class, Long.class).getAll();
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
        List<Long> keys = new ArrayList<Long>();
        keys.add(2L);
        keys.add(3L);

        List<AccountInfo> accountInfos = CacherManager.getInstance().getCacher(AccountInfo.class, Long.class)
                .getList(keys);
        for (AccountInfo accountInfo : accountInfos)
        {
            System.out.println(accountInfo.toString());
        }
    }

    private static void testget()
    {
        AccountInfo accountInfo = CacherManager.getInstance().getCacher(AccountInfo.class, Long.class).get(2L);
        accountInfo.setAccountName("tony");
        CacherManager.getInstance().getCacher(AccountInfo.class, Long.class).put(2L, accountInfo);
        System.err.println(accountInfo.toString());
    }

    /**
     * 
     */
    private static void test01()
    {

        ICacher<Long, AccountInfo> cacher = new AbCacher<Long, AccountInfo>(new AccountLoader(), new AccountWriter(
                DaoManager.getAccountDao()), new CacherConfiguration("account"));
        CacherManager.getInstance().putCacher(AccountInfo.class, cacher);
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
}
