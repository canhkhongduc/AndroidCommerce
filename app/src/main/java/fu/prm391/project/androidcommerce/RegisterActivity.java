package fu.prm391.project.androidcommerce;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import fu.prm391.project.androidcommerce.dao.MyHelper;
import fu.prm391.project.androidcommerce.model.User;

public class RegisterActivity extends AppCompatActivity {
    private EditText etUsername, etPassword, etEmail, etPhone, etAddress;
    private Button btnSignup;
    MyHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        db = new MyHelper(this);
        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        etEmail = findViewById(R.id.etEmail);
        etPhone = findViewById(R.id.etPhone);
        etAddress = findViewById(R.id.etAddress);
        btnSignup = findViewById(R.id.btnSignup);
        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = etUsername.getText().toString();
                String password = etPassword.getText().toString();
                String email = etEmail.getText().toString();
                String phone = etPhone.getText().toString();
                String address = etAddress.getText().toString();
                User user = new User();
                if(!username.equals("") && !password.equals("") && !email.equals("") && !phone.equals("") && !address.equals("")) {
                   user.setUserName(username);
                   user.setUserPassword(password);
                   user.setUserEmail(email);
                   user.setUserPhone(phone);
                   user.setUserAddress(address);
                    if(db.getUserByUsername(user.getUserName()) == null){
                        db.insertUser(user);
                        Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                        intent.putExtra("user", user);
                        setResult(5,intent);
                        finish();
                    } else{
                        Toast.makeText(RegisterActivity.this,"User existed!",Toast.LENGTH_LONG).show();
                    }
                } else{
                    Toast.makeText(RegisterActivity.this,"Please fill all the credentials!",Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
