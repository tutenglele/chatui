package cn.myweb.chat.ui;

import cn.myweb.chat.ui.view.chat.ChatController;
import cn.myweb.chat.ui.view.chat.IChatEvent;
import cn.myweb.chat.ui.view.chat.IChatMethod;
import cn.myweb.chat.ui.view.login.ILoginMethod;
import cn.myweb.chat.ui.view.login.LoginController;
import javafx.application.Application;
import javafx.stage.Stage;

import javax.xml.crypto.Data;
import java.util.Date;

public class App extends Application {
    @Override
    public void start(Stage stage) throws Exception {
//        ILoginMethod login = new LoginController((userId, userPassword) -> {
//            System.out.println("用户id:"+userId+"; userPassword: "+userPassword);
//        });
//
//        login.doShow();
        IChatMethod chat = new ChatController(new IChatEvent() {
        });
        chat.doShow();
        chat.setUserInfo("10000", "赛涉嫌", "02_50");
        chat.addTalkBox(-1, 0, "10001", "hah", "01_50", "sdhghfdkfd", new Date(), true);
        chat.addTalkMsgUserLeft("10001","你好啊，啊啊啊", new Date(), true, false, true);
        chat.addTalkMsgUserRight("10001","你好啊，啊啊啊", new Date(), true, true, false);
        chat.addTalkBox(-1, 0, "10002", "h对方的h", "02_50", "f分隔符豆腐干地方是d", new Date(), false);
        chat.addTalkBox(-1, 0, "10003", "h的h", "03_50", "f分隔符豆腐干地方是d", new Date(), false);
        chat.addTalkBox(-1, 0, "10004", "df方的h", "04_50", "f分隔符豆腐干地方是d", new Date(), false);
        chat.addTalkBox(-1, 1, "10005", "傻天月", "222", "f分隔符豆腐干地方是d", new Date(), false);
        chat.addTalkMsgUserLeft("10005","你好啊，啊啊啊", new Date(), true, false, true);
        chat.addTalkMsgUserRight("10005","你好啊，啊啊啊", new Date(), true, true, false);

        chat.addTalkBox(0, 1, "12345", "群", "group_1", "", new Date(), true);
        chat.addTalkMsgUserRight("12345", "hello qun", new Date(), true, true, false);
        chat.addTalkMsgGroupLeft("12345", "10001", "hah", "01_50", "你好十三点", new Date(), true, false, true);
    }

}
