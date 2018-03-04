package fu.prm391.project.androidcommerce;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import fu.prm391.project.androidcommerce.adminActivity.AdminHomeActivity;
import fu.prm391.project.androidcommerce.controller.LoginController;
import fu.prm391.project.androidcommerce.controller.listener.LoginListener;
import fu.prm391.project.androidcommerce.customerActivity.CustomerHomeActivity;
import fu.prm391.project.androidcommerce.database.AppDatabase;
import fu.prm391.project.androidcommerce.database.entity.User;

public class LoginActivity extends AppCompatActivity implements LoginListener {
    private Button btnRegister;
    private Button btnLogin;
    private EditText etLoginUsername;
    private EditText etLoginPassword;
    private AppDatabase db;
    private LoginController loginController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        db = AppDatabase.getAppDatabase(this);
        loginController = new LoginController(this, db);

        btnLogin = findViewById(R.id.btnLogin);
        btnRegister = findViewById(R.id.btnRegister);
        etLoginUsername = findViewById(R.id.etLoginUsername);
        etLoginPassword = findViewById(R.id.etLoginPassword);

        btnRegister.setOnClickListener(new btnRegisterListener());
        btnLogin.setOnClickListener(new btnLoginListener());
    }

    private class btnLoginListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            String username = etLoginUsername.getText().toString();
            String password = etLoginPassword.getText().toString();

            if (username.trim().length() == 0 || password.trim().length() == 0) {
                Toast.makeText(LoginActivity.this, "Please enter your username and password", Toast.LENGTH_LONG).show();
                return;
            }

            User user = new User(username, password, null, null, null, -1);

            loginController.login(user); //After LoginAsyncTask finishes, login() or loginFailed() will be called
        }
    }

    private class btnRegisterListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
            startActivity(intent);
        }
    }

    @Override
    public void login(final int userId) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                User user = db.userDAO().getUserByUserId(userId);
                if (user.getUserType() == 1){
                    startActivity(new Intent(LoginActivity.this,AdminHomeActivity.class));
                } else {
                    SharedPreferences preference = PreferenceManager.getDefaultSharedPreferences(LoginActivity.this);
                    SharedPreferences.Editor preferenceEditor = preference.edit();
                    preferenceEditor.putInt("userId", user.getUserId()).apply();
                    Intent intent = new Intent(LoginActivity.this, CustomerHomeActivity.class);
                    startActivity(intent);
                }
            }
        });
    }

    @Override
    public void loginFailed(String message) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(LoginActivity.this, "Login Failed", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
