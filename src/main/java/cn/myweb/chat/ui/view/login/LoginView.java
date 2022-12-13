package cn.myweb.chat.ui.view.login;

/**
 * 页面展示元素和事件定义
 */
public class LoginView {
    private LoginInit loginInit;
    private ILoginEvent loginEvent;

    public LoginView(LoginInit loginInit, ILoginEvent loginEvent) {
        this.loginEvent = loginEvent;
        this.loginInit = loginInit;
    }
}
