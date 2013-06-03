/**
 * Date: 2013-5-27
 *
 * Copyright (C) 2013-2015 7Road. All rights reserved.
 */

package com.road.entity;


/**
 * 
 * @author yutao.chen
 */
public class DataObject
{
    private byte op = 0;

    public final void setOp(byte option)
    {
        if ((this.op == DataOption.INSERT) && (option == DataOption.UPDATE))
        {
            return;
        }
        this.op = option;
    }

    public final byte getOp()
    {
        return this.op;
    }
    
    /**
     * 
     * @author yutao.chen
     */
    public interface DataOption
    {
        /**
         * 无操作
         */
        public static final byte NONE = 0;
        
        /**
         * 插入
         */
        public static final byte INSERT = 1;
        
        /**
         * 更新
         */
        public static final byte UPDATE = 2;
        
        /**
         * 删除
         */
        public static final byte DELETE = 3;
    }
}
