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

public class LoginActivity extends AppCompatActivity {
    private Button btnRegister;
    private Button btnLogin;
    private EditText etLoginUsername, etLoginPassword;
    MyHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        btnLogin = findViewById(R.id.btnLogin);
        btnRegister = findViewById(R.id.btnRegister);
        etLoginUsername = findViewById(R.id.etLoginUsername);
        etLoginPassword = findViewById(R.id.etLoginPassword);
        db = new MyHelper(this);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivityForResult(intent, 1);
            }
        });
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = etLoginUsername.getText().toString();
                String password = etLoginPassword.getText().toString();
                User user = db.getUserByUsername(username);
                if(user == null){
                    Toast.makeText(LoginActivity.this,"User doesn't exist. Try again!", Toast.LENGTH_LONG).show();
                } else{
                    if(!password.equals( user.getUserPassword())){
                        Toast.makeText(LoginActivity.this,"Wrong password. Try again!", Toast.LENGTH_LONG).show();
                    } else {
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        intent.putExtra("user", user);
                        setResult(5,intent);
                        finish();
                    }
                }
            }
        });
    }

}
