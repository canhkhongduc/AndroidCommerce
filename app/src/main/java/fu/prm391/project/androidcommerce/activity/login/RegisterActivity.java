package fu.prm391.project.androidcommerce.activity.login;

import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import fu.prm391.project.androidcommerce.R;
import fu.prm391.project.androidcommerce.controller.RegisterController;
import fu.prm391.project.androidcommerce.controller.listener.RegisterListener;
import fu.prm391.project.androidcommerce.database.AppDatabase;
import fu.prm391.project.androidcommerce.database.entity.User;

public class RegisterActivity extends AppCompatActivity implements RegisterListener {
    private TextInputLayout etUsername;
    private TextInputLayout etPassword;
    private TextInputLayout etEmail;
    private TextInputLayout etPhone;
    private TextInputLayout etAddress;
    private Button btnSignup;
    private AppDatabase db;
    private RegisterController registerController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        db = AppDatabase.getAppDatabase(this);
        registerController = new RegisterController(this, db);

        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        etEmail = findViewById(R.id.etEmail);
        etPhone = findViewById(R.id.etPhone);
        etAddress = findViewById(R.id.etAddress);
        btnSignup = findViewById(R.id.btnSignup);

        btnSignup.setOnClickListener(new btnSignupListener());
    }

    private class btnSignupListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            String username = etUsername.getEditText().getText().toString();
            String password = etPassword.getEditText().getText().toString();
            String email = etEmail.getEditText().getText().toString();
            String phone = etPhone.getEditText().getText().toString();
            String address = etAddress.getEditText().getText().toString();

            if (username.trim().length() == 0 || password.trim().length() == 0 || email.trim().length() == 0 || phone.trim().length() == 0 || address.trim().length() == 0) {
                Toast.makeText(RegisterActivity.this, "Please fill all the credentials!", Toast.LENGTH_LONG).show();
                return;
            } else {
                User user = new User(username, password, email, phone, address, db.userTypeDAO().TYPE_USER);

                registerController.register(user); //After RegisterAsyncTask finishes, registerDone() will be called
            }
        }
    }

    @Override
    public void registerDone(final String message) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
            }
        });
    }
}
