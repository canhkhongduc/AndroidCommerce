package fu.prm391.project.androidcommerce.activity.admin;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import fu.prm391.project.androidcommerce.R;
import fu.prm391.project.androidcommerce.activity.customer.BaseCustomerActivity;
import fu.prm391.project.androidcommerce.activity.customer.CheckOutActivity;
import fu.prm391.project.androidcommerce.utils.SharedPreferenceUtil;

public class BaseAdminActivity extends AppCompatActivity {
    private DrawerLayout drawerLayout;
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
                        startActivity(new Intent(BaseAdminActivity.this, AdminViewProductActivity.class));
                        return true;
                    case R.id.nav_category:
                        startActivity(new Intent(BaseAdminActivity.this, AdminViewCategoryActivity.class));
                        return true;
                    case R.id.nav_user:
                        return true;
                    case R.id.nav_order:
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
                return true;
            case R.id.nav_order:
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
