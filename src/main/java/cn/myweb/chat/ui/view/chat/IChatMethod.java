package cn.myweb.chat.ui.view.chat;

import java.util.Date;

public interface IChatMethod {
    void doShow();

    /**
     * 设置登录用户头像
     * @param userId 用户ID
     * @param userNickName 用户昵称
     * @param userHead 头像图片名称
     */
    void setUserInfo(String userId, String userNickName, String userHead);

    /**
     * 填充对话框列表
     * @param talkIdx    对话框位置；首位0、默认-1
     * @param talkType   对话框类型；好友0、群组1
     * @param talkId     对话框ID，1v1聊天ID、1vn聊天ID
     * @param talkName   对话框名称
     * @param talkHead   对话框头像
     * @param talkSketch 对话框通信简述(聊天内容最后一组信息)
     * @param talkDate   对话框通信时间
     * @param selected   选中[true/false]
     */
    void addTalkBox(int talkIdx, Integer talkType, String talkId, String talkName, String talkHead, String talkSketch, Date talkDate, boolean selected);

    /**
     * 填充对话框消息，好友发来
     * @param takeId 对话框ID[用户ID]
     * @param msg 消息
     * @param msgDate 时间
     * @param idxFirst 是否设置首位
     * @param selected 是否选中
     * @param isRemind 是否提醒
     */
    void addTalkMsgUserLeft(String takeId, String msg, Date msgDate, Boolean idxFirst, Boolean selected, Boolean isRemind);

    /**
     * 填充对话框消息，自己发
     * @param takeId 对话框ID[用户ID]
     * @param msg 消息
     * @param msgDate 时间
     * @param idxFirst 是否设置首位
     * @param selected 是否选中
     * @param isRemind 是否提醒
     */
    void addTalkMsgUserRight(String takeId, String msg, Date msgDate, Boolean idxFirst, Boolean selected, Boolean isRemind);

    /**
     * 填充对话框消息，群组
     * @param takeId 对话框ID[群组ID]
     * @param userId 用户ID
     * @param userNickName 用户昵称
     * @param userHead 用户头像
     * @param msg 消息
     * @param msgDate 时间
     * @param idxFirst 是否设置首位
     * @param selected 是否选中
     * @param isRemind 是否提醒
     */
    void addTalkMsgGroupLeft(String takeId, String userId, String userNickName, String userHead, String msg, Date msgDate, Boolean idxFirst, Boolean selected, Boolean isRemind);
}
