/**
 * Date: Mar 15, 2013
 * 
 * Copyright (C) 2013-2015 7Road. All rights reserved.
 */

package com.road.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 
 * @author jiayi.wei
 */
public class StringUtil
{
    /**
     * email地址是否合法
     * 
     * @param address
     * @return
     */
    public static boolean isValidEmailAddress(String address)
    {
        if (address == null)
        {
            return false;
        }
        Pattern p = Pattern.compile("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*");
        Matcher m = p.matcher(address);
        return m.find();
    }

    /**
     * 字符串是否为空。
     * 
     * @param src
     * @return
     */
    public static boolean isNullOrEmpty(String src)
    {
        if (src == null || src.isEmpty())
        {
            return true;
        }

        return false;
    }

    public static boolean isEmptyOrWhitespaceOnly(String src)
    {
        if (src == null || src.isEmpty())
        {
            return true;
        }

        if (src.trim().length() == 0)
        {
            return true;
        }

        return false;
    }
}
