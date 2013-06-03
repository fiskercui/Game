package com.road.pitaya.chat;

public interface ChannelType
{
    static final byte CHANNEL_START = 0;

    /**
     * CHANNEL_PUBLIC_START --- CHANNEL_PUBLIC_END 1vn聊天 CHANNEL_WORLD 世界频道
     * CHANNEL_CLAW 帮派频道 CHANNEL_NEAR 附近频道 CHANNEL_NEAR_DYNAMIC 需要实时获取接受列表的附近频道
     */
    static final byte CHANNEL_PUBLIC_START = CHANNEL_START;
    static final byte CHANNEL_WORLD = 0;
    static final byte CHANNEL_CLAW = 1;
    static final byte CHANNEL_NEAR = 2;    
    static final byte CHANNEL_NEAR_DYNAMIC = 3;
    static final byte CHANNEL_PUBLIC_END = CHANNEL_NEAR_DYNAMIC;

    /** 1v1聊天 */
    static final byte CHANNEL_PRIVATE = CHANNEL_PUBLIC_END + 1;

    static final String[] CHANNEL_NAME = { "World", "Claw", "Near", "Near",
            "Private" };

}
