package cn.myweb.chat.ui.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class Login extends Stage {
    private static final String RESOURCE_NAME = "/fxml/demo/login.fxml";
    private Parent root;

    public Login() {
        try {
            root = FXMLLoader.load(getClass().getResource(RESOURCE_NAME));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene scene = new Scene(root);
        scene.setFill(Color.TRANSPARENT);
        setScene(scene);
        initStyle(StageStyle.TRANSPARENT);
        setResizable(false);
        this.getIcons().add(new Image("/fxml/demo/img/system/show.png"));

        move();
        min();
        quit();
        login();
    }
    private double xOffset;
    private double yOffset;

    private void move() {
        root.setOnMousePressed(event -> {
            xOffset = getX() - event.getScreenX();
            yOffset = getY() - event.getScreenY();
            root.setCursor(Cursor.CLOSED_HAND);
        });
        root.setOnMouseDragged(event -> {
            setX(event.getScreenX() + xOffset);
            setY(event.getScreenY() + yOffset);
        });

        root.setOnMouseReleased(event -> {
            root.setCursor(Cursor.DEFAULT);
        });
    }
    public <T> T $(String id, Class<T> clazz) {
        return (T) root.lookup("#" + id);
    }
    private void min() {
        Button login_min = $("login_min", Button.class);
        login_min.setOnAction(event -> {
            System.out.println("进行最小化");
            setIconified(true);
        });
    }
    private void quit() {
        Button login_close = $("login_close", Button.class);
        login_close.setOnAction(event -> {
            System.out.println("退出");
            close();
            System.exit(0);
        });
    }
    private void login() {
        TextField userId = $("userId", TextField.class);
        PasswordField userPassword = $("userPassword", PasswordField.class);
        $("login_button", Button.class).setOnAction(event -> {
            System.out.println("开始登录：用户名：" + userId.getText() + ", 密码为：" + userPassword.getText());
        });
    }
}
