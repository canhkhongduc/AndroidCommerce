package fu.prm391.project.androidcommerce.controller.listener;

/**
 * Created by Lam on 3/2/2018.
 */

public interface LoginListener {
    void login(int userId);
    void loginFailed(String message);
}
