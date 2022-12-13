package cn.myweb.chat.ui.view.chat;

import cn.myweb.chat.ui.view.chat.data.RemindCount;
import cn.myweb.chat.ui.view.chat.data.TalkBoxData;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.MultipleSelectionModel;
import javafx.scene.layout.Pane;

public class ChatView {
    private ChatInit chatInit;
    private IChatEvent chatEvent;

    public ChatView(ChatInit chatInit, IChatEvent chatEvent) {
        this.chatInit = chatInit;
        this.chatEvent = chatEvent;
        //1.好友列表添加工具方法，新的朋友

        //2 好友列表添加公众号

        //3.好友群组框体

        //4.好友框体

    }

    /**
     * 更新对话框列表元素位置，指定并选中，在聊天消息发送时触达
     * @param talkType        对话框类型[0好友、1群组]
     * @param talkElementPane 对话框元素面板
     * @param msgRemindLabel  消息提醒标签
     * @param idxFirst        是否设置首位
     * @param selected        是否选中
     * @param isRemind        是否提醒
     */
    public void updateTalkListIdxAndSelected(int talkType, Pane talkElementPane, Label msgRemindLabel, Boolean idxFirst, Boolean selected, Boolean isRemind) {
        //对话框ID，好友ID
        TalkBoxData talkBoxData = (TalkBoxData) talkElementPane.getUserData();
        //填充对话框
        ListView talkList = chatInit.$("talkList", ListView.class);
        //对话框为空，初始化【置顶，选中，提醒】
        if (talkList.getItems().isEmpty()) {
            if (idxFirst) {
                talkList.getItems().add(0, talkElementPane);
            }
            if (selected) {
                talkList.getSelectionModel().select(talkElementPane);
            }
            isRemind(msgRemindLabel, talkType, isRemind);
            return;
        }
        //对话框不为空，判断第一个元素是否当前聊天Pane
        Pane firstPane = (Pane) talkList.getItems().get(0);
        // 判断元素是否在首位，如果是首位可返回不需要重新设置首位
        if (talkBoxData.getTalkId().equals(((TalkBoxData) firstPane.getUserData()).getTalkId())) {
            Pane selectedItem = (Pane) talkList.getSelectionModel().getSelectedItem();
            // 选中判断；如果第一个元素已经选中[说明正在会话]，那么清空消息提醒
            if (selectedItem == null) {
                isRemind(msgRemindLabel, talkType, isRemind);
                return;
            }
            TalkBoxData selectedItemUserData = (TalkBoxData) selectedItem.getUserData();
            if (null != selectedItemUserData && talkBoxData.getTalkId().equals(selectedItemUserData.getTalkId())) {
                clearRemind(msgRemindLabel);
            } else {
                isRemind(msgRemindLabel, talkType, isRemind);
            }
            return;
        }
        if (idxFirst) {
            talkList.getItems().remove(talkElementPane);
            talkList.getItems().add(0, talkElementPane);
        }
        if (selected) {
            talkList.getSelectionModel().select(talkElementPane);
        }
        isRemind(msgRemindLabel, talkType, isRemind);
    }

    /**
     * 消息提醒
     * @param msgRemindLabel 消息面板
     * @param talkType
     * @param isRemind
     */
    private void isRemind(Label msgRemindLabel, int talkType, Boolean isRemind) {
        if (!isRemind) {
            return;
        }
        msgRemindLabel.setVisible(true);
        //群组展示小红点
        if (1==talkType) {
            return;
        }
        RemindCount remindCount = (RemindCount) msgRemindLabel.getUserData();
        if(remindCount.getCount() > 99) {
            msgRemindLabel.setText("...");
            return;
        } else {
            int count = remindCount.getCount() + 1;
            msgRemindLabel.setUserData(new RemindCount(count));
            msgRemindLabel.setText(String.valueOf(count));
        }
    }

    private void clearRemind(Label msgRemindLabel) {
        msgRemindLabel.setVisible(false);
        msgRemindLabel.setUserData(new RemindCount(0));
    }
}
