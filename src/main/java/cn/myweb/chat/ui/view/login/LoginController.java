package cn.myweb.chat.ui.view.login;

/**
 * 窗体控制管理类，实现接口方法，初始化界面和事件定义
 */
public class LoginController extends LoginInit implements ILoginMethod {
    private LoginView loginView;
    private LoginEventDefine loginEventDefine;

    public LoginController(ILoginEvent loginEvent) {
        super(loginEvent);
    }
    @Override
    public void doShow() {
        super.show();
    }

    @Override
    public void doLoginError() {
        System.out.println("登录失败");
    }

    @Override
    public void doLoginSuccess() {
        System.out.println("登录成功，跳转");
        close();
    }

    @Override
    public void initView() {
        loginView = new LoginView(this, loginEvent);
    }

    @Override
    public void initEventDefine() {
        loginEventDefine = new LoginEventDefine(this, loginEvent, this);
    }
}
