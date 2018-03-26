package fu.prm391.project.androidcommerce.activity.admin;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import fu.prm391.project.androidcommerce.R;
import fu.prm391.project.androidcommerce.activity.login.LoginActivity;
import fu.prm391.project.androidcommerce.utils.SharedPreferenceUtil;

public class BaseAdminActivity extends AppCompatActivity {
    private DrawerLayout drawerLayout;
    private SharedPreferenceUtil util;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_admin);
        drawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.nav_product:
                        Log.d("navadmin", "" + R.id.nav_admin_home);
                        startActivity(new Intent(BaseAdminActivity.this, AdminViewProductActivity.class));
                        return true;
                    case R.id.nav_category:
                        startActivity(new Intent(BaseAdminActivity.this, AdminViewCategoryActivity.class));
                        return true;
                    case R.id.nav_user:
                        startActivity(new Intent(BaseAdminActivity.this, AdminViewUserActivity.class));
                        return true;
                    case R.id.nav_order:
                        startActivity(new Intent(BaseAdminActivity.this, AdminViewOrderActivity.class));
                        return true;
                    case R.id.nav_admin_home:
                        startActivity(new Intent(BaseAdminActivity.this, AdminHomeActivity.class));
                        return true;
                }
                item.setChecked(true);
                drawerLayout.closeDrawers();
                return true;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.admin_nav_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_product:
                startActivity(new Intent(BaseAdminActivity.this, AdminViewProductActivity.class));
                return true;
            case R.id.nav_category:
                startActivity(new Intent(BaseAdminActivity.this, AdminViewCategoryActivity.class));
                return true;
            case R.id.nav_user:
                startActivity(new Intent(BaseAdminActivity.this, AdminViewUserActivity.class));
                return true;
            case R.id.nav_order:
                startActivity(new Intent(BaseAdminActivity.this, AdminViewOrderActivity.class));
                return true;
            case R.id.nav_logout:
                util = new SharedPreferenceUtil();
                util.destroyPreference(this);
                startActivity(new Intent(BaseAdminActivity.this, LoginActivity.class));

        }
        return super.onOptionsItemSelected(item);
    }
}
