public class CmdPacket {
    private String CmdType;
    private String CmdParams;
    private String SenderType;
    private long SentTime;

    public CmdPacket() {
    }

    public CmdPacket(String cmdType, String cmdParams, String senderType, long sentTime) {
        CmdType = cmdType;
        CmdParams = cmdParams;
        SenderType = senderType;
        SentTime = sentTime;
    }

    public String getCmdType() {
        return CmdType;
    }

    public void setCmdType(String cmdType) {
        CmdType = cmdType;
    }

    public String getCmdParams() {
        return CmdParams;
    }

    public void setCmdParams(String cmdParams) {
        CmdParams = cmdParams;
    }

    public String getSenderType() {
        return SenderType;
    }

    public void setSenderType(String senderType) {
        SenderType = senderType;
    }

    public long getSentTime() {
        return SentTime;
    }

    public void setSentTime(long sentTime) {
        SentTime = sentTime;
    }
}
