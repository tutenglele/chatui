package cn.myweb.chat.ui.view.chat;

import cn.myweb.chat.ui.view.UIObject;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.StageStyle;

import java.io.IOException;

public abstract class ChatInit extends UIObject {
    private static final String RESOURCE_NAME = "/fxml/chat/chat.fxml";
    protected IChatEvent chatEvent;

    public String userId;//用户ID
    public String userNickName;
    public String userHead;
    public TextArea txt_input;

    public ChatInit(IChatEvent chatEvent) {
        this.chatEvent = chatEvent;
        try {
            root = FXMLLoader.load(getClass().getResource(RESOURCE_NAME));
        } catch (IOException e) {
            e.printStackTrace();
        }
        //窗口-场景-布局-控件
        Scene scene = new Scene(root);
        scene.setFill(Color.TRANSPARENT);
        setScene(scene);
        initStyle(StageStyle.TRANSPARENT);
        setResizable(false);
        this.getIcons().add(new Image("/fxml/chat/img/head/logo.png"));
        obtain();
        initView();
        initEventDefine();
    }

    private void obtain() {
        txt_input = $("txt_input", TextArea.class);
    }
    public Parent root() {
        return super.root;
    }
}
