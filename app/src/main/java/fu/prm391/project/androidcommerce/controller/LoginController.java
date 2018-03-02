package fu.prm391.project.androidcommerce.controller;

import android.os.AsyncTask;

import fu.prm391.project.androidcommerce.controller.listener.LoginListener;
import fu.prm391.project.androidcommerce.database.AppDatabase;
import fu.prm391.project.androidcommerce.database.entity.User;

/**
 * Created by Lam on 3/2/2018.
 */

public class LoginController {
    private final static String LOGIN_FAILED = "Login failed";

    private LoginListener loginListener;
    private AppDatabase db;

    public LoginController(LoginListener loginListener, AppDatabase db) {
        this.loginListener = loginListener;
        this.db = db;
    }

    public LoginListener getLoginListener() {
        return loginListener;
    }

    public void setLoginListener(LoginListener loginListener) {
        this.loginListener = loginListener;
    }

    public void login(User loginUser) {
        new LoginAsyncTask().execute(loginUser);
    }

    private class LoginAsyncTask extends AsyncTask<User, Void, Void> {
        @Override
        protected Void doInBackground(User... users) {
            User user = db.userDAO().userLogin(users[0].getUsername(), users[0].getPassword());

            if (user == null) {
                loginListener.loginFailed(LOGIN_FAILED);
            } else {
                loginListener.login(user.getUserId());
            }
            return null;
        }
    }
}
