package com.road.pitaya.entity;

public class Player
{

    private long id;
    private String name;

    private String nick;

    public long getId()
    {
        return id;
    }

    public void setId(long id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getNick()
    {
        return nick;
    }

    public void setNick(String nick)
    {
        this.nick = nick;
    }

    @Override
    public String toString()
    {
        // TODO Auto-generated method stub
        return "id : " + id + " ,name : " + name + "; ";
    }

}
