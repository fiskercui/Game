// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: proto/ChatMessageReply.proto

package com.road.pb;

public final class ChatMsgReplyProto {
  private ChatMsgReplyProto() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
  }
  public interface ChatMsgReplyOrBuilder
      extends com.google.protobuf.MessageOrBuilder {
    
    // required int32 chatType = 1;
    boolean hasChatType();
    int getChatType();
    
    // optional string sendName = 3;
    boolean hasSendName();
    String getSendName();
    
    // optional int32 channelType = 4;
    boolean hasChannelType();
    int getChannelType();
    
    // optional string message = 5;
    boolean hasMessage();
    String getMessage();
  }
  public static final class ChatMsgReply extends
      com.google.protobuf.GeneratedMessage
      implements ChatMsgReplyOrBuilder {
    // Use ChatMsgReply.newBuilder() to construct.
    private ChatMsgReply(Builder builder) {
      super(builder);
    }
    private ChatMsgReply(boolean noInit) {}
    
    private static final ChatMsgReply defaultInstance;
    public static ChatMsgReply getDefaultInstance() {
      return defaultInstance;
    }
    
    public ChatMsgReply getDefaultInstanceForType() {
      return defaultInstance;
    }
    
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return com.road.pb.ChatMsgReplyProto.internal_static_com_road_proto_ChatMsgReply_descriptor;
    }
    
    protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.road.pb.ChatMsgReplyProto.internal_static_com_road_proto_ChatMsgReply_fieldAccessorTable;
    }
    
    private int bitField0_;
    // required int32 chatType = 1;
    public static final int CHATTYPE_FIELD_NUMBER = 1;
    private int chatType_;
    public boolean hasChatType() {
      return ((bitField0_ & 0x00000001) == 0x00000001);
    }
    public int getChatType() {
      return chatType_;
    }
    
    // optional string sendName = 3;
    public static final int SENDNAME_FIELD_NUMBER = 3;
    private java.lang.Object sendName_;
    public boolean hasSendName() {
      return ((bitField0_ & 0x00000002) == 0x00000002);
    }
    public String getSendName() {
      java.lang.Object ref = sendName_;
      if (ref instanceof String) {
        return (String) ref;
      } else {
        com.google.protobuf.ByteString bs = 
            (com.google.protobuf.ByteString) ref;
        String s = bs.toStringUtf8();
        if (com.google.protobuf.Internal.isValidUtf8(bs)) {
          sendName_ = s;
        }
        return s;
      }
    }
    private com.google.protobuf.ByteString getSendNameBytes() {
      java.lang.Object ref = sendName_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8((String) ref);
        sendName_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    
    // optional int32 channelType = 4;
    public static final int CHANNELTYPE_FIELD_NUMBER = 4;
    private int channelType_;
    public boolean hasChannelType() {
      return ((bitField0_ & 0x00000004) == 0x00000004);
    }
    public int getChannelType() {
      return channelType_;
    }
    
    // optional string message = 5;
    public static final int MESSAGE_FIELD_NUMBER = 5;
    private java.lang.Object message_;
    public boolean hasMessage() {
      return ((bitField0_ & 0x00000008) == 0x00000008);
    }
    public String getMessage() {
      java.lang.Object ref = message_;
      if (ref instanceof String) {
        return (String) ref;
      } else {
        com.google.protobuf.ByteString bs = 
            (com.google.protobuf.ByteString) ref;
        String s = bs.toStringUtf8();
        if (com.google.protobuf.Internal.isValidUtf8(bs)) {
          message_ = s;
        }
        return s;
      }
    }
    private com.google.protobuf.ByteString getMessageBytes() {
      java.lang.Object ref = message_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8((String) ref);
        message_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    
    private void initFields() {
      chatType_ = 0;
      sendName_ = "";
      channelType_ = 0;
      message_ = "";
    }
    private byte memoizedIsInitialized = -1;
    public final boolean isInitialized() {
      byte isInitialized = memoizedIsInitialized;
      if (isInitialized != -1) return isInitialized == 1;
      
      if (!hasChatType()) {
        memoizedIsInitialized = 0;
        return false;
      }
      memoizedIsInitialized = 1;
      return true;
    }
    
    public void writeTo(com.google.protobuf.CodedOutputStream output)
                        throws java.io.IOException {
      getSerializedSize();
      if (((bitField0_ & 0x00000001) == 0x00000001)) {
        output.writeInt32(1, chatType_);
      }
      if (((bitField0_ & 0x00000002) == 0x00000002)) {
        output.writeBytes(3, getSendNameBytes());
      }
      if (((bitField0_ & 0x00000004) == 0x00000004)) {
        output.writeInt32(4, channelType_);
      }
      if (((bitField0_ & 0x00000008) == 0x00000008)) {
        output.writeBytes(5, getMessageBytes());
      }
      getUnknownFields().writeTo(output);
    }
    
    private int memoizedSerializedSize = -1;
    public int getSerializedSize() {
      int size = memoizedSerializedSize;
      if (size != -1) return size;
    
      size = 0;
      if (((bitField0_ & 0x00000001) == 0x00000001)) {
        size += com.google.protobuf.CodedOutputStream
          .computeInt32Size(1, chatType_);
      }
      if (((bitField0_ & 0x00000002) == 0x00000002)) {
        size += com.google.protobuf.CodedOutputStream
          .computeBytesSize(3, getSendNameBytes());
      }
      if (((bitField0_ & 0x00000004) == 0x00000004)) {
        size += com.google.protobuf.CodedOutputStream
          .computeInt32Size(4, channelType_);
      }
      if (((bitField0_ & 0x00000008) == 0x00000008)) {
        size += com.google.protobuf.CodedOutputStream
          .computeBytesSize(5, getMessageBytes());
      }
      size += getUnknownFields().getSerializedSize();
      memoizedSerializedSize = size;
      return size;
    }
    
    private static final long serialVersionUID = 0L;
    @java.lang.Override
    protected java.lang.Object writeReplace()
        throws java.io.ObjectStreamException {
      return super.writeReplace();
    }
    
    public static com.road.pb.ChatMsgReplyProto.ChatMsgReply parseFrom(
        com.google.protobuf.ByteString data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return newBuilder().mergeFrom(data).buildParsed();
    }
    public static com.road.pb.ChatMsgReplyProto.ChatMsgReply parseFrom(
        com.google.protobuf.ByteString data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return newBuilder().mergeFrom(data, extensionRegistry)
               .buildParsed();
    }
    public static com.road.pb.ChatMsgReplyProto.ChatMsgReply parseFrom(byte[] data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return newBuilder().mergeFrom(data).buildParsed();
    }
    public static com.road.pb.ChatMsgReplyProto.ChatMsgReply parseFrom(
        byte[] data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return newBuilder().mergeFrom(data, extensionRegistry)
               .buildParsed();
    }
    public static com.road.pb.ChatMsgReplyProto.ChatMsgReply parseFrom(java.io.InputStream input)
        throws java.io.IOException {
      return newBuilder().mergeFrom(input).buildParsed();
    }
    public static com.road.pb.ChatMsgReplyProto.ChatMsgReply parseFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return newBuilder().mergeFrom(input, extensionRegistry)
               .buildParsed();
    }
    public static com.road.pb.ChatMsgReplyProto.ChatMsgReply parseDelimitedFrom(java.io.InputStream input)
        throws java.io.IOException {
      Builder builder = newBuilder();
      if (builder.mergeDelimitedFrom(input)) {
        return builder.buildParsed();
      } else {
        return null;
      }
    }
    public static com.road.pb.ChatMsgReplyProto.ChatMsgReply parseDelimitedFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      Builder builder = newBuilder();
      if (builder.mergeDelimitedFrom(input, extensionRegistry)) {
        return builder.buildParsed();
      } else {
        return null;
      }
    }
    public static com.road.pb.ChatMsgReplyProto.ChatMsgReply parseFrom(
        com.google.protobuf.CodedInputStream input)
        throws java.io.IOException {
      return newBuilder().mergeFrom(input).buildParsed();
    }
    public static com.road.pb.ChatMsgReplyProto.ChatMsgReply parseFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return newBuilder().mergeFrom(input, extensionRegistry)
               .buildParsed();
    }
    
    public static Builder newBuilder() { return Builder.create(); }
    public Builder newBuilderForType() { return newBuilder(); }
    public static Builder newBuilder(com.road.pb.ChatMsgReplyProto.ChatMsgReply prototype) {
      return newBuilder().mergeFrom(prototype);
    }
    public Builder toBuilder() { return newBuilder(this); }
    
    @java.lang.Override
    protected Builder newBuilderForType(
        com.google.protobuf.GeneratedMessage.BuilderParent parent) {
      Builder builder = new Builder(parent);
      return builder;
    }
    public static final class Builder extends
        com.google.protobuf.GeneratedMessage.Builder<Builder>
       implements com.road.pb.ChatMsgReplyProto.ChatMsgReplyOrBuilder {
      public static final com.google.protobuf.Descriptors.Descriptor
          getDescriptor() {
        return com.road.pb.ChatMsgReplyProto.internal_static_com_road_proto_ChatMsgReply_descriptor;
      }
      
      protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
          internalGetFieldAccessorTable() {
        return com.road.pb.ChatMsgReplyProto.internal_static_com_road_proto_ChatMsgReply_fieldAccessorTable;
      }
      
      // Construct using com.road.pb.ChatMsgReplyProto.ChatMsgReply.newBuilder()
      private Builder() {
        maybeForceBuilderInitialization();
      }
      
      private Builder(BuilderParent parent) {
        super(parent);
        maybeForceBuilderInitialization();
      }
      private void maybeForceBuilderInitialization() {
        if (com.google.protobuf.GeneratedMessage.alwaysUseFieldBuilders) {
        }
      }
      private static Builder create() {
        return new Builder();
      }
      
      public Builder clear() {
        super.clear();
        chatType_ = 0;
        bitField0_ = (bitField0_ & ~0x00000001);
        sendName_ = "";
        bitField0_ = (bitField0_ & ~0x00000002);
        channelType_ = 0;
        bitField0_ = (bitField0_ & ~0x00000004);
        message_ = "";
        bitField0_ = (bitField0_ & ~0x00000008);
        return this;
      }
      
      public Builder clone() {
        return create().mergeFrom(buildPartial());
      }
      
      public com.google.protobuf.Descriptors.Descriptor
          getDescriptorForType() {
        return com.road.pb.ChatMsgReplyProto.ChatMsgReply.getDescriptor();
      }
      
      public com.road.pb.ChatMsgReplyProto.ChatMsgReply getDefaultInstanceForType() {
        return com.road.pb.ChatMsgReplyProto.ChatMsgReply.getDefaultInstance();
      }
      
      public com.road.pb.ChatMsgReplyProto.ChatMsgReply build() {
        com.road.pb.ChatMsgReplyProto.ChatMsgReply result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(result);
        }
        return result;
      }
      
      private com.road.pb.ChatMsgReplyProto.ChatMsgReply buildParsed()
          throws com.google.protobuf.InvalidProtocolBufferException {
        com.road.pb.ChatMsgReplyProto.ChatMsgReply result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(
            result).asInvalidProtocolBufferException();
        }
        return result;
      }
      
      public com.road.pb.ChatMsgReplyProto.ChatMsgReply buildPartial() {
        com.road.pb.ChatMsgReplyProto.ChatMsgReply result = new com.road.pb.ChatMsgReplyProto.ChatMsgReply(this);
        int from_bitField0_ = bitField0_;
        int to_bitField0_ = 0;
        if (((from_bitField0_ & 0x00000001) == 0x00000001)) {
          to_bitField0_ |= 0x00000001;
        }
        result.chatType_ = chatType_;
        if (((from_bitField0_ & 0x00000002) == 0x00000002)) {
          to_bitField0_ |= 0x00000002;
        }
        result.sendName_ = sendName_;
        if (((from_bitField0_ & 0x00000004) == 0x00000004)) {
          to_bitField0_ |= 0x00000004;
        }
        result.channelType_ = channelType_;
        if (((from_bitField0_ & 0x00000008) == 0x00000008)) {
          to_bitField0_ |= 0x00000008;
        }
        result.message_ = message_;
        result.bitField0_ = to_bitField0_;
        onBuilt();
        return result;
      }
      
      public Builder mergeFrom(com.google.protobuf.Message other) {
        if (other instanceof com.road.pb.ChatMsgReplyProto.ChatMsgReply) {
          return mergeFrom((com.road.pb.ChatMsgReplyProto.ChatMsgReply)other);
        } else {
          super.mergeFrom(other);
          return this;
        }
      }
      
      public Builder mergeFrom(com.road.pb.ChatMsgReplyProto.ChatMsgReply other) {
        if (other == com.road.pb.ChatMsgReplyProto.ChatMsgReply.getDefaultInstance()) return this;
        if (other.hasChatType()) {
          setChatType(other.getChatType());
        }
        if (other.hasSendName()) {
          setSendName(other.getSendName());
        }
        if (other.hasChannelType()) {
          setChannelType(other.getChannelType());
        }
        if (other.hasMessage()) {
          setMessage(other.getMessage());
        }
        this.mergeUnknownFields(other.getUnknownFields());
        return this;
      }
      
      public final boolean isInitialized() {
        if (!hasChatType()) {
          
          return false;
        }
        return true;
      }
      
      public Builder mergeFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws java.io.IOException {
        com.google.protobuf.UnknownFieldSet.Builder unknownFields =
          com.google.protobuf.UnknownFieldSet.newBuilder(
            this.getUnknownFields());
        while (true) {
          int tag = input.readTag();
          switch (tag) {
            case 0:
              this.setUnknownFields(unknownFields.build());
              onChanged();
              return this;
            default: {
              if (!parseUnknownField(input, unknownFields,
                                     extensionRegistry, tag)) {
                this.setUnknownFields(unknownFields.build());
                onChanged();
                return this;
              }
              break;
            }
            case 8: {
              bitField0_ |= 0x00000001;
              chatType_ = input.readInt32();
              break;
            }
            case 26: {
              bitField0_ |= 0x00000002;
              sendName_ = input.readBytes();
              break;
            }
            case 32: {
              bitField0_ |= 0x00000004;
              channelType_ = input.readInt32();
              break;
            }
            case 42: {
              bitField0_ |= 0x00000008;
              message_ = input.readBytes();
              break;
            }
          }
        }
      }
      
      private int bitField0_;
      
      // required int32 chatType = 1;
      private int chatType_ ;
      public boolean hasChatType() {
        return ((bitField0_ & 0x00000001) == 0x00000001);
      }
      public int getChatType() {
        return chatType_;
      }
      public Builder setChatType(int value) {
        bitField0_ |= 0x00000001;
        chatType_ = value;
        onChanged();
        return this;
      }
      public Builder clearChatType() {
        bitField0_ = (bitField0_ & ~0x00000001);
        chatType_ = 0;
        onChanged();
        return this;
      }
      
      // optional string sendName = 3;
      private java.lang.Object sendName_ = "";
      public boolean hasSendName() {
        return ((bitField0_ & 0x00000002) == 0x00000002);
      }
      public String getSendName() {
        java.lang.Object ref = sendName_;
        if (!(ref instanceof String)) {
          String s = ((com.google.protobuf.ByteString) ref).toStringUtf8();
          sendName_ = s;
          return s;
        } else {
          return (String) ref;
        }
      }
      public Builder setSendName(String value) {
        if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000002;
        sendName_ = value;
        onChanged();
        return this;
      }
      public Builder clearSendName() {
        bitField0_ = (bitField0_ & ~0x00000002);
        sendName_ = getDefaultInstance().getSendName();
        onChanged();
        return this;
      }
      void setSendName(com.google.protobuf.ByteString value) {
        bitField0_ |= 0x00000002;
        sendName_ = value;
        onChanged();
      }
      
      // optional int32 channelType = 4;
      private int channelType_ ;
      public boolean hasChannelType() {
        return ((bitField0_ & 0x00000004) == 0x00000004);
      }
      public int getChannelType() {
        return channelType_;
      }
      public Builder setChannelType(int value) {
        bitField0_ |= 0x00000004;
        channelType_ = value;
        onChanged();
        return this;
      }
      public Builder clearChannelType() {
        bitField0_ = (bitField0_ & ~0x00000004);
        channelType_ = 0;
        onChanged();
        return this;
      }
      
      // optional string message = 5;
      private java.lang.Object message_ = "";
      public boolean hasMessage() {
        return ((bitField0_ & 0x00000008) == 0x00000008);
      }
      public String getMessage() {
        java.lang.Object ref = message_;
        if (!(ref instanceof String)) {
          String s = ((com.google.protobuf.ByteString) ref).toStringUtf8();
          message_ = s;
          return s;
        } else {
          return (String) ref;
        }
      }
      public Builder setMessage(String value) {
        if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000008;
        message_ = value;
        onChanged();
        return this;
      }
      public Builder clearMessage() {
        bitField0_ = (bitField0_ & ~0x00000008);
        message_ = getDefaultInstance().getMessage();
        onChanged();
        return this;
      }
      void setMessage(com.google.protobuf.ByteString value) {
        bitField0_ |= 0x00000008;
        message_ = value;
        onChanged();
      }
      
      // @@protoc_insertion_point(builder_scope:com.road.proto.ChatMsgReply)
    }
    
    static {
      defaultInstance = new ChatMsgReply(true);
      defaultInstance.initFields();
    }
    
    // @@protoc_insertion_point(class_scope:com.road.proto.ChatMsgReply)
  }
  
  private static com.google.protobuf.Descriptors.Descriptor
    internal_static_com_road_proto_ChatMsgReply_descriptor;
  private static
    com.google.protobuf.GeneratedMessage.FieldAccessorTable
      internal_static_com_road_proto_ChatMsgReply_fieldAccessorTable;
  
  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\034proto/ChatMessageReply.proto\022\016com.road" +
      ".proto\"X\n\014ChatMsgReply\022\020\n\010chatType\030\001 \002(\005" +
      "\022\020\n\010sendName\030\003 \001(\t\022\023\n\013channelType\030\004 \001(\005\022" +
      "\017\n\007message\030\005 \001(\tB \n\013com.road.pbB\021ChatMsg" +
      "ReplyProto"
    };
    com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner assigner =
      new com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner() {
        public com.google.protobuf.ExtensionRegistry assignDescriptors(
            com.google.protobuf.Descriptors.FileDescriptor root) {
          descriptor = root;
          internal_static_com_road_proto_ChatMsgReply_descriptor =
            getDescriptor().getMessageTypes().get(0);
          internal_static_com_road_proto_ChatMsgReply_fieldAccessorTable = new
            com.google.protobuf.GeneratedMessage.FieldAccessorTable(
              internal_static_com_road_proto_ChatMsgReply_descriptor,
              new java.lang.String[] { "ChatType", "SendName", "ChannelType", "Message", },
              com.road.pb.ChatMsgReplyProto.ChatMsgReply.class,
              com.road.pb.ChatMsgReplyProto.ChatMsgReply.Builder.class);
          return null;
        }
      };
    com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
        }, assigner);
  }
  
  // @@protoc_insertion_point(outer_class_scope)
}
