package cn.myweb.chat.ui.view.chat.element.group_bar_chat;

import cn.myweb.chat.ui.util.AutoSizeTool;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Pane;

/**
 * 消息对话内容承担，有左侧信息和右侧信息，头像，昵称，内容，内容箭头。
 */
public class ElementInfoBox {
    private Pane pane;
    private Pane head; //头像
    private Label nikeName; //称呼
    private Label infoContentArrow; //内容箭头
    private TextArea infoContent;

    public Pane left(String userNikeName, String userHead, String msg) {
        double autoHeight = AutoSizeTool.getHeight(msg);
        double autoWidth = AutoSizeTool.getWidth(msg);

        pane = new Pane();
        pane.setPrefSize(500, 50+autoHeight);
        pane.getStyleClass().add("infoBoxElement");
        ObservableList<Node> children = pane.getChildren();
        //头像
        head = new Pane();
        head.setPrefSize(50, 50);
        head.setLayoutX(15);
        head.setLayoutY(15);
        head.getStyleClass().add("box_head");
        head.setStyle(String.format("-fx-background-image:url('/fxml/chat/img/head/%s.png')", userHead));
        children.add(head);

        //昵称
        nikeName = new Label();
        nikeName.setPrefSize(450, 20);
        nikeName.setLayoutX(75);
        nikeName.setLayoutY(5);
        nikeName.setText(userNikeName);
        nikeName.getStyleClass().add("box_nikeName");
        children.add(nikeName);
        //箭头
        infoContentArrow = new Label();
        infoContentArrow.setPrefSize(5, 20);
        infoContentArrow.setLayoutY(30);
        infoContentArrow.setLayoutX(75);
        infoContentArrow.getStyleClass().add("box_infoContent_arrow");
        children.add(infoContentArrow);

        infoContent = new TextArea();
        infoContent.setPrefWidth(autoWidth);
        infoContent.setPrefHeight(autoHeight);
        infoContent.setLayoutX(80);
        infoContent.setLayoutY(30);
        infoContent.setWrapText(true);//实现自动换行
        infoContent.setEditable(true);
        infoContent.setText(msg);
        infoContent.getStyleClass().add("box_infoContent_left");
        children.add(infoContent);
        return pane;
    }

    public Pane right(String userNikeName, String userHead, String msg) {
        double autoHeight = AutoSizeTool.getHeight(msg);
        double autoWidth = AutoSizeTool.getWidth(msg);

        pane = new Pane();
        pane.setPrefSize(500, 50+autoHeight);
        pane.setLayoutX(853);
        pane.setLayoutY(0);
        pane.getStyleClass().add("infoBoxElement");
        ObservableList<Node> children = pane.getChildren();
        //头像
        head = new Pane();
        head.setPrefSize(50, 50);
        head.setLayoutX(770);
        head.setLayoutY(15);
        head.getStyleClass().add("box_head");
        head.setStyle(String.format("-fx-background-image:url('/fxml/chat/img/head/%s.png')", userHead));
        children.add(head);

//        //昵称
//        nikeName = new Label();
//        nikeName.setPrefSize(450, 20);
//        nikeName.setLayoutX(75);
//        nikeName.setLayoutY(5);
//        nikeName.setText(userNikeName);
//        nikeName.getStyleClass().add("box_nikeName");
//        children.add(nikeName);
        //箭头
        infoContentArrow = new Label();
        infoContentArrow.setPrefSize(5, 20);
        infoContentArrow.setLayoutX(755);
        infoContentArrow.setLayoutY(15);
        infoContentArrow.getStyleClass().add("box_infoContent_arrow");
        children.add(infoContentArrow);

        infoContent = new TextArea();
        infoContent.setPrefWidth(autoWidth);
        infoContent.setPrefHeight(autoHeight);
        infoContent.setLayoutX(755 - autoWidth);
        infoContent.setLayoutY(15);
        infoContent.setWrapText(true);//实现自动换行
        infoContent.setEditable(false);
        infoContent.setText(msg);
        infoContent.getStyleClass().add("box_infoContent_right");
        children.add(infoContent);
        return pane;
    }
}
