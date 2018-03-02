package fu.prm391.project.androidcommerce.controller;

import android.os.AsyncTask;

import fu.prm391.project.androidcommerce.controller.listener.RegisterListener;
import fu.prm391.project.androidcommerce.database.AppDatabase;
import fu.prm391.project.androidcommerce.database.entity.User;

/**
 * Created by Lam on 3/2/2018.
 */

public class RegisterController {
    private final static String REGISTER_SUCCESS_MSG = "Register successfully";
    private final static String REGISTER_FAILED_MSG = "Register failed, username existed";

    private RegisterListener registerListener;
    private AppDatabase db;

    public RegisterController(RegisterListener registerListener, AppDatabase db) {
        this.registerListener = registerListener;
        this.db = db;
    }

    public RegisterListener getRegisterListener() {
        return registerListener;
    }

    public void setRegisterListener(RegisterListener registerListener) {
        this.registerListener = registerListener;
    }

    public void register(User registerUser) {
        new RegisterAsyncTask().execute(registerUser);
    }

    private class RegisterAsyncTask extends AsyncTask<User, Void, Void> {

        @Override
        protected Void doInBackground(User... users) {

            if (db.userDAO().getUserByUsername(users[0].getUsername()) != null) {
                registerListener.registerDone(REGISTER_FAILED_MSG);
            } else {
                db.userDAO().insert(users[0]);
                registerListener.registerDone(REGISTER_SUCCESS_MSG);
            }

            return null;
        }
    }
}
