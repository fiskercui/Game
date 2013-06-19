package com.road.pb.wrap;

import com.google.protobuf.InvalidProtocolBufferException;
import com.road.pb.ArmyMsgProto.ArmyMsg;


//message ArmyMsg {
//optional int32 id = 1;
//optional int32 user_id = 2;
//optional string name = 3;
//optional int32 type = 4;
//optional int32 side = 5;
//optional int32 formation = 6;
//}

public class ArmyMsgProtoWrap
{
    
    private ArmyMsg msg= null;

    public ArmyMsgProtoWrap(byte[] objBytes) throws InvalidProtocolBufferException
    {
        this.msg = ArmyMsg.parseFrom(objBytes);
    }
    
    public void fromByteArray (byte[] objBytes) throws InvalidProtocolBufferException
    {
       this.msg = ArmyMsg.parseFrom(objBytes);
    }
    
    public byte[] toByteArray() 
    {
        return msg.toByteArray();
    }

    public static Builder newBuilder()
    {
        return new Builder();
    }
    
    public static final class Builder
    {
        private ArmyMsg.Builder builder = ArmyMsg.newBuilder();

        
        public void setId(int id)
        {
            builder.setId(id);
        }
        
        public void setUserId(int userId)
        {
            builder.setUserId(userId);
        }
        
        public void setUserName(String value)
        {
            builder.setName(value);
        }
        
        public ArmyMsg build()
        {
            return builder.build();      
        }
        
    }
    
    public int getId()
    {
        return msg.getId();
    }
    
    public int getUserId()
    {
        return msg.getUserId();
    }
    
    public String getUserName()
    {
        return msg.getName();
    }
    
    public int getType()
    {
        return msg.getType();
    }
    
    public int getSide()
    {
        return msg.getSide();
    }
    
    public int getFormation()
    {
        return msg.getFormation();
    }
    

}
