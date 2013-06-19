package com.road.pitaya.entity;

import java.io.Serializable;

public class AccountInfo implements Serializable
{

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private long id;
    private String accountName;
    private String accountPassword;

    public long getId()
    {
        return id;
    }

    public void setId(long id)
    {
        this.id = id;
    }

    public String getAccountName()
    {
        return accountName;
    }

    public void setAccountName(String accountName)
    {
        this.accountName = accountName;
    }

    public String getAccountPassword()
    {
        return accountPassword;
    }

    public void setAccountPassword(String accountPassword)
    {
        this.accountPassword = accountPassword;
    }

    @Override
    public String toString()
    {
        String message = "[\nid:" + getId() + ",name:" + getAccountName() + ",password:" + getAccountPassword() + "\n]";
        return message;
    }
}
