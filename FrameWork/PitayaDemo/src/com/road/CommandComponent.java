package com.road;


import com.road.pitaya.command.ICode;
import com.road.pitaya.component.AbstractCommandComponent;

public class CommandComponent extends AbstractCommandComponent<ICode>
{

    @Override
    public String getCommandPacketName()
    {
        return "com.road.command";
    }

    @Override
    public Class<ICode> getAnnotationClass()
    {
        // TODO Auto-generated method stub
        return ICode.class;
    }

    @Override
    public Short getNodeType(ICode annotation)
    {
        // TODO Auto-generated method stub
        return annotation.code();
    }

}
