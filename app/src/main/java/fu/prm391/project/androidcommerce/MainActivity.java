package fu.prm391.project.androidcommerce;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import fu.prm391.project.androidcommerce.dao.MyHelper;
import fu.prm391.project.androidcommerce.model.User;

public class MainActivity extends AppCompatActivity {
    private Button btnSignin;
    private TextView tvUserWelcome;
    MyHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnSignin = findViewById(R.id.btnSignin);
        tvUserWelcome = findViewById(R.id.tvUserWelcome);
        btnSignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivityForResult(intent, 1);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == 5 && data != null){
            btnSignin.setVisibility(View.GONE);
            User user = (User) data.getSerializableExtra("user");
            tvUserWelcome.setVisibility(View.VISIBLE);
            tvUserWelcome.setText(user.getUserName());
        }
    }
}
