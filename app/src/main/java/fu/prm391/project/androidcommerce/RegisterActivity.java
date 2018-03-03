package fu.prm391.project.androidcommerce;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import fu.prm391.project.androidcommerce.controller.RegisterController;
import fu.prm391.project.androidcommerce.controller.listener.RegisterListener;
import fu.prm391.project.androidcommerce.database.AppDatabase;
import fu.prm391.project.androidcommerce.database.entity.User;

public class RegisterActivity extends AppCompatActivity implements RegisterListener {
    private EditText etUsername;
    private EditText etPassword;
    private EditText etEmail;
    private EditText etPhone;
    private EditText etAddress;
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
            String username = etUsername.getText().toString();
            String password = etPassword.getText().toString();
            String email = etEmail.getText().toString();
            String phone = etPhone.getText().toString();
            String address = etAddress.getText().toString();

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
