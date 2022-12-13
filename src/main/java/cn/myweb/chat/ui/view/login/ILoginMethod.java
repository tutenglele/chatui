package cn.myweb.chat.ui.view.login;

/**
 * 方法接口类，桌面开发基本是事件触达和回调。
 */
public interface ILoginMethod {
    /**
     * 打开登录窗口
     */
    void doShow();

    /**
     * 登录失败
     */
    void doLoginError();

    /**
     * 登录成功，跳转聊天窗口，关闭老窗口，打开新
     */
    void doLoginSuccess();
}
