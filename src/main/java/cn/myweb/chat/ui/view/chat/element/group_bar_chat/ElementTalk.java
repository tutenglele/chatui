package cn.myweb.chat.ui.view.chat.element.group_bar_chat;

import cn.myweb.chat.ui.util.DateUtil;
import cn.myweb.chat.ui.util.Ids;
import cn.myweb.chat.ui.view.chat.data.RemindCount;
import cn.myweb.chat.ui.view.chat.data.TalkBoxData;
import cn.myweb.chat.ui.view.chat.data.TalkData;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.Pane;

import java.util.Date;

/**
 * 对话框元素类
 */
public class ElementTalk {
    private Pane pane; //对话面板，与好友对话，群组对话
    private Label head; //头像区域
    private Label nikeName;
    private Label msgSketch; //信息简述
    private Label msgDate; //信息时间
    private Label msgRemind; //消息提醒
    private Button delete; //// 删除对话框按钮

    private ListView<Pane> infoBoxList;//消息对话框

    public ElementTalk(String talkId, Integer talkType, String talkName, String talkHead, String talkSketch, Date talkDate) {
        //面板
        pane = new Pane();
        pane.setId(Ids.ElementTalkId.createTalkPaneId(talkId));
        pane.setUserData(new TalkBoxData(talkId, talkType, talkName, talkHead));
        pane.setPrefSize(270, 80);
        pane.getStyleClass().add("talkElement");
        ObservableList<Node> children = pane.getChildren();
        //头像
        head = new Label();
        head.setPrefSize(50, 50);
        head.setLayoutX(15);
        head.setLayoutY(15);
        head.getStyleClass().add("element_head");
        head.setStyle(String.format("-fx-background-image: url('/fxml/chat/img/head/%s.png')", talkHead));
        children.add(head);
        //昵称
        nikeName = new Label();
        nikeName.setPrefSize(140, 25);
        nikeName.setLayoutX(80);
        nikeName.setLayoutY(15);
        nikeName.setText(talkName);
        nikeName.getStyleClass().add("element_nikeName");
        children.add(nikeName);
        // 信息简述
        msgSketch = new Label();
        msgSketch.setId(Ids.ElementTalkId.createMsgSketchId(talkId));
        msgSketch.setPrefSize(200, 25);
        msgSketch.setLayoutX(80);
        msgSketch.setLayoutY(40);
        msgSketch.getStyleClass().add("element_msgSketch");
        children.add(msgSketch);

        // 信息时间
        msgDate = new Label();
        msgDate.setId(Ids.ElementTalkId.createMsgDataId(talkId));
        msgDate.setPrefSize(60, 25);
        msgDate.setLayoutX(220);
        msgDate.setLayoutY(15);
        msgDate.getStyleClass().add("element_msgData");
        children.add(msgDate);

        // 填充；信息简述 & 信息时间
        fillMsgSketch(talkSketch, talkDate);

        // 消息提醒
        msgRemind = new Label();
        msgRemind.setPrefSize(15, 15);
        msgRemind.setLayoutX(60);
        msgRemind.setLayoutY(5);
        msgRemind.setUserData(new RemindCount());
        msgRemind.setText("");
        msgRemind.setVisible(false);
        msgRemind.getStyleClass().add("element_msgRemind");
        children.add(msgRemind);

        // 删除对话框按钮
        delete = new Button();
        delete.setVisible(false);
        delete.setPrefSize(4, 4);
        delete.setLayoutY(26);
        delete.setLayoutX(-8);
        delete.getStyleClass().add("element_delete");
        children.add(delete);

        // 消息框[初始化，未装载]，承载对话信息内容，点击按钮时候填充到聊天框体面板中infopanebox
        infoBoxList = new ListView<>();
        infoBoxList.setId(Ids.ElementTalkId.createInfoBoxListId(talkId));
        infoBoxList.setUserData(new TalkData(talkName, talkHead));
        infoBoxList.setPrefSize(850, 560);
        infoBoxList.getStyleClass().add("infoBoxStyle");
    }

    public void fillMsgSketch(String talkSketch, Date talkDate) {
        if(null != talkSketch) {
            if(talkSketch.length() > 30) {
                talkSketch = talkSketch.substring(0, 30);
            }
            msgSketch.setText(talkSketch);
        }
        if(null == talkDate) {
            talkDate = new Date();
        }
        //格式化日期
        String talkSimpleDate = DateUtil.simpleDate(talkDate);
        msgDate.setText(talkSimpleDate);
    }

    public Pane pane() {
        return pane;
    }
    public ListView<Pane> infoBoxList() {
        return infoBoxList;
    }
    public Button delete() {
        return delete;
    }
    public void clearMsgSketch() {
        msgSketch.setText("");
    }
    public Label msgRemind() {
        return msgRemind;
    }
}
