package cn.myweb.chat.ui.view.chat;

import cn.myweb.chat.ui.util.CacheUtil;
import cn.myweb.chat.ui.util.Ids;
import cn.myweb.chat.ui.view.chat.data.GroupsData;
import cn.myweb.chat.ui.view.chat.data.RemindCount;
import cn.myweb.chat.ui.view.chat.data.TalkData;
import cn.myweb.chat.ui.view.chat.element.group_bar_chat.ElementInfoBox;
import cn.myweb.chat.ui.view.chat.element.group_bar_chat.ElementTalk;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.Pane;

import java.util.Date;

public class ChatController extends ChatInit implements IChatMethod{

    private ChatView chatView;
    private ChatEventDefine chatEventDefine;
    public ChatController(IChatEvent chatEvent) {
        super(chatEvent);
    }

    @Override
    public void initView() {
        chatView = new ChatView(this, chatEvent);
    }

    @Override
    public void initEventDefine() {
        chatEventDefine = new ChatEventDefine(this, chatEvent, this);
    }

    @Override
    public void doShow() {
        super.show();
    }

    @Override
    public void setUserInfo(String userId, String userNickName, String userHead) {
        super.userId = userId;
        super.userNickName = userNickName;
        super.userHead = userHead;
    }

    @Override
    public void addTalkBox(int talkIdx, Integer talkType, String talkId, String talkName, String talkHead, String talkSketch, Date talkDate, boolean selected) {
        //填充到对话框
        ListView<Pane> talkList = $("talkList", ListView.class);
        //判断会话框是否有该对象
        ElementTalk elementTalk = CacheUtil.talkMap.get(talkId);
        if (null != elementTalk) {
            Node talkNode = talkList.lookup("#" + Ids.ElementTalkId.createTalkPaneId(talkId));
            if (talkNode == null) {
                talkList.getItems().add(talkIdx, elementTalk.pane());
            }
            if(selected) {
                //设置选中
                talkList.getSelectionModel().select(elementTalk.pane());
            }
            return;
        }
        ElementTalk talkElement = new ElementTalk(talkId, talkType, talkName, talkHead, talkSketch, talkDate);
        CacheUtil.talkMap.put(talkId, talkElement);
        //填充到对话框；
        ObservableList<Pane> items = talkList.getItems();
        Pane pane = talkElement.pane();
        if (talkIdx >= 0) {
            items.add(talkIdx, pane);
        } else {
            items.add(pane);//顺序添加
        }
        pane.setOnMousePressed(event -> {
            System.out.println("点击对话框" + talkName + talkSketch);
            //填充消息栏
            fillInfoBox(talkElement, talkName);
            //清除消息提醒
            Label msgRemind = talkElement.msgRemind();
            msgRemind.setUserData(new RemindCount(0));
            msgRemind.setVisible(false);
        });
        pane.setOnMouseEntered(event -> {
            talkElement.delete().setVisible(true);
        });
        pane.setOnMouseExited(event -> {
            talkElement.delete().setVisible(false);
        });
        talkElement.delete().setOnMouseClicked(event -> {
            System.out.println("删除对话框" + talkName);
            talkList.getItems().remove(pane);
            talkElement.clearMsgSketch();
        });
    }

    @Override
    public void addTalkMsgUserLeft(String takeId, String msg, Date msgDate, Boolean idxFirst, Boolean selected, Boolean isRemind) {
        ElementTalk talkElement = CacheUtil.talkMap.get(takeId);
        ListView<Pane> listView = talkElement.infoBoxList();
        TalkData talkUserData = (TalkData) listView.getUserData();
        Pane left = new ElementInfoBox().left(talkUserData.getTalkName(), talkUserData.getTalkHead(), msg);
        //消息填充
        listView.getItems().add(left);
        //滚动条
        listView.scrollTo(left);
        talkElement.fillMsgSketch(msg, msgDate);
        //设置位置和选中
        chatView.updateTalkListIdxAndSelected(0, talkElement.pane(), talkElement.msgRemind(), idxFirst, selected, isRemind);
        //填充对话框聊天窗口
        fillInfoBox(talkElement, talkUserData.getTalkName());
    }

    @Override
    public void addTalkMsgUserRight(String takeId, String msg, Date msgDate, Boolean idxFirst, Boolean selected, Boolean isRemind) {
        ElementTalk elementTalk = CacheUtil.talkMap.get(takeId);
        ListView<Pane> listView = elementTalk.infoBoxList();
        Pane right = new ElementInfoBox().right(userNickName, userHead, msg);
        //消息填充
        listView.getItems().add(right);
        //滚动条
        listView.scrollTo(right);
        elementTalk.fillMsgSketch(msg, msgDate);
        //设置位置和选中
        chatView.updateTalkListIdxAndSelected(0, elementTalk.pane(), elementTalk.msgRemind(), idxFirst, selected, isRemind);
    }

    @Override
    public void addTalkMsgGroupLeft(String takeId, String userId, String userNickName, String userHead, String msg, Date msgDate, Boolean idxFirst, Boolean selected, Boolean isRemind) {
        if (super.userId.equals(userId)) {
            return; //自己的消息直接抛弃
        }
        ElementTalk talkElement = CacheUtil.talkMap.get(takeId);
        if (null == talkElement) {//群组会话不存在
            GroupsData groupsData = (GroupsData) $(Ids.ElementTalkId.createFriendGroupId(takeId), Pane.class).getUserData();
            if (null == groupsData) {//群组不存在
                return;
            }
            addTalkBox(0, 1, takeId, groupsData.getGroupName(), groupsData.getGroupHead(), userNickName+": "+msg, msgDate, false);
            talkElement = CacheUtil.talkMap.get(takeId);
            System.out.println("事件通知（开启和群组发送消息）");
        }
        ListView<Pane> listView = talkElement.infoBoxList();
        TalkData talkData = (TalkData) listView.getUserData();
        Pane left = new ElementInfoBox().left(userNickName, userHead, msg);
        //消息填充
        listView.getItems().add(left);
        //滚动条
        listView.scrollTo(left);
        talkElement.fillMsgSketch(userNickName + ": " + msg, msgDate);
        //设置位置和选中
        chatView.updateTalkListIdxAndSelected(1, talkElement.pane(), talkElement.msgRemind(), idxFirst, selected, isRemind);
        //填充对话框聊天窗口
        fillInfoBox(talkElement, talkData.getTalkName());
    }

    private void fillInfoBox(ElementTalk talkElement, String talkName) {
        String talkId = talkElement.pane().getUserData().toString();
        //填充对话列表
        Pane info_pane_box = $("info_pane_box", Pane.class);
        String boxUserId = (String) info_pane_box.getUserData();
        //如果已经填充，则返回
        if(talkId.equals(boxUserId)) {
            return;
        }
        ListView<Pane> listView = talkElement.infoBoxList();
        info_pane_box.setUserData(talkId);
        info_pane_box.getChildren().clear();
        info_pane_box.getChildren().add(listView);
        //对话框名称
        Label info_name = $("info_name", Label.class);
        info_name.setText(talkName);
    }



}
