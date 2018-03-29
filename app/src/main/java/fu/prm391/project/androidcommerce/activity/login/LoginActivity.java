package fu.prm391.project.androidcommerce.activity.login;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import fu.prm391.project.androidcommerce.R;
import fu.prm391.project.androidcommerce.activity.customer.CustomerMenuActivity;
import fu.prm391.project.androidcommerce.firebase.model.User;

public class LoginActivity extends AppCompatActivity {
    private TextView btnRegister;
    private Button btnLogin;
    private TextInputLayout etLoginUsername;
    private TextInputLayout etLoginPassword;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        databaseReference = FirebaseDatabase.getInstance().getReference();

        btnLogin = findViewById(R.id.btnLogin);
        btnRegister = findViewById(R.id.txtRegister);
        etLoginUsername = findViewById(R.id.etLoginUsername);
        etLoginPassword = findViewById(R.id.etLoginPassword);

        btnRegister.setOnClickListener(new btnRegisterListener());
        btnLogin.setOnClickListener(new btnLoginListener());
    }

    private class btnLoginListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            final String username = etLoginUsername.getEditText().getText().toString();
            final String password = etLoginPassword.getEditText().getText().toString();

            Query query = databaseReference.child("users").orderByChild("username").equalTo(username);

            if (username.trim().length() == 0 || password.trim().length() == 0) {
                Toast.makeText(LoginActivity.this, "Please enter your username and password", Toast.LENGTH_LONG).show();
                return;
            }

            ValueEventListener userListener = new ValueEventListener() {
                User user;
                String userId;

                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    Log.d("test", dataSnapshot.toString());
                    for (DataSnapshot singleSnapshot : dataSnapshot.getChildren()) {
                        user = singleSnapshot.getValue(User.class);
                        userId = singleSnapshot.getKey();
                    }

                    if (user.getPassword().equals(password)) {
                        SharedPreferences preference = PreferenceManager.getDefaultSharedPreferences(LoginActivity.this);
                        SharedPreferences.Editor preferenceEditor = preference.edit();
                        preferenceEditor.putString("userId", userId).apply();
                        Intent intent = new Intent(LoginActivity.this, CustomerMenuActivity.class);
                        startActivity(intent);
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            };

            query.addListenerForSingleValueEvent(userListener);
        }
    }

    private class btnRegisterListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
            startActivity(intent);
        }
    }
}
