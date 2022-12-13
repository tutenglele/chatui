package cn.myweb.chat.ui.view.login;

/**
 * 窗体事件定义
 */
public class LoginEventDefine {
    private LoginInit loginInit;
    private ILoginEvent loginEvent;
    private ILoginMethod loginMethod;

    public LoginEventDefine(LoginInit loginInit, ILoginEvent loginEvent, ILoginMethod loginMethod) {
        this.loginInit = loginInit;
        this.loginEvent = loginEvent;
        this.loginMethod = loginMethod;
        loginInit.move();
        min();
        quit();
        doEventLogin();
    }
    private void min() {
        loginInit.login_min.setOnAction(event -> {
            loginInit.setIconified(true);
        });
    }
    private void quit() {
        loginInit.login_close.setOnAction(actionEvent -> {
            loginInit.close();
            System.exit(0);
        });
    }
    private void doEventLogin() {
        loginInit.login_button.setOnAction(event -> {
            loginEvent.doLogicCheck(loginInit.userId.getText(), loginInit.userPassword.getText());
        });
    }
}
