package cn.myweb.chat.ui.view.chat.data;

public class TalkData {
    private String talkName;
    private String talkHead;

    public TalkData(){}
    public TalkData(String talkName, String talkHead) {
        this.talkHead = talkHead;
        this.talkName = talkName;
    }

    public String getTalkName() {
        return talkName;
    }

    public void setTalkName(String talkName) {
        this.talkName = talkName;
    }

    public String getTalkHead() {
        return talkHead;
    }

    public void setTalkHead(String talkHead) {
        this.talkHead = talkHead;
    }
}
