package fu.prm391.project.androidcommerce.activity.customer;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import fu.prm391.project.androidcommerce.R;
import fu.prm391.project.androidcommerce.activity.login.LoginActivity;
import fu.prm391.project.androidcommerce.utils.SharedPreferenceUtil;

public class ProfileActivity extends BaseCustomerActivity {

    private BottomNavigationView navigationView;
    private ConstraintLayout rowProfile;
    private ConstraintLayout rowLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        navigationView = findViewById(R.id.profileActivity_bottomNav);
        rowProfile = findViewById(R.id.profileActivity_rowProfile);
        rowLogout = findViewById(R.id.profileActivity_rowLogout);

        rowLogout.setOnClickListener(new rowOnClickListener(this, LoginActivity.class, true));
        rowProfile.setOnClickListener(new rowOnClickListener(this, CustomerViewProfileActivity.class, false));

        navigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_category:
                        changeActivityBottomNav(ProfileActivity.this, CategoryActivity.class);
                        break;
                    case R.id.action_home:
                        changeActivityBottomNav(ProfileActivity.this, CustomerMenuActivity.class);
                        break;
                    case R.id.action_profile:
                        break;
                }
                return true;
            }
        });

        Menu menu = navigationView.getMenu();
        MenuItem menuItem = menu.getItem(2);
        menuItem.setChecked(true);
    }

    private class rowOnClickListener implements View.OnClickListener {
        private Context context;
        private Class destinationClass;
        private boolean logout;

        public rowOnClickListener(Context context, Class destinationClass, boolean logout) {
            this.context = context;
            this.destinationClass = destinationClass;
            this.logout = logout;
        }

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(context, destinationClass);
            if (logout) {
                SharedPreferenceUtil util = new SharedPreferenceUtil();
                util.removePreference(context, "userId");
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            }
            startActivity(intent);
        }
    }
}
