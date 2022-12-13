package cn.myweb.chat.ui.view.login;

/**
 * 事件接口类，具体实现交给调用方。例如点击登录后，将属于窗体的功能处理完毕后，实际的验证交给外部
 */
public interface ILoginEvent {
    /**
     * 登录验证，实际开发，在这里扩展IP地址，设备信息，请求时间等
     * @param userId 用户ID
     * @param userPassword 用户密码
     */
    void doLogicCheck(String userId, String userPassword);
}
