package fu.prm391.project.androidcommerce.activity.customer;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.rowland.cartcounter.view.CartCounterActionView;

import java.util.ArrayList;
import java.util.List;

import fu.prm391.project.androidcommerce.R;
import fu.prm391.project.androidcommerce.activity.admin.BaseAdminActivity;
import fu.prm391.project.androidcommerce.activity.login.LoginActivity;
import fu.prm391.project.androidcommerce.database.entity.OrderItem;
import fu.prm391.project.androidcommerce.utils.SharedPreferenceUtil;

/**
 * Created by Lam on 3/12/2018.
 */

public class BaseCustomerActivity extends AppCompatActivity {
    private SharedPreferenceUtil util;
    private CartCounterActionView actionView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setBackgroundDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.gradient_action_bar, null));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.customer_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        util = new SharedPreferenceUtil();
        switch (item.getItemId()) {
            case R.id.cart:
                if (util.getCart(this, "cartItem") != null) {
                    Intent intent1 = new Intent(BaseCustomerActivity.this, CheckOutActivity.class);
                    startActivity(intent1);
                } else {
                    Toast.makeText(this, "Cart empty!", Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.logout:
                util = new SharedPreferenceUtil();
                util.destroyPreference(this);
                startActivity(new Intent(BaseCustomerActivity.this, LoginActivity.class));
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        MenuItem itemData = menu.findItem(R.id.cart);
        actionView = (CartCounterActionView) itemData.getActionView();
        actionView.setItemData(menu, itemData);

        SharedPreferenceUtil util = new SharedPreferenceUtil();
        ArrayList<OrderItem> orderItems = util.getCart(BaseCustomerActivity.this, "cartItem");
        if (orderItems != null) {
            actionView.setCount(calculateCartSize(orderItems));
        } else {
            actionView.setCount(0);
        }
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    protected void onResume() {
        super.onResume();

        invalidateOptionsMenu();
    }

    protected void addCartCount(int step) {
        CartCounterActionView.setCountStep(this, step);
    }

    private int calculateCartSize(List<OrderItem> orderItemList) {
        int size = 0;
        for (OrderItem orderItem : orderItemList) {
            size += orderItem.getQuantity();
        }

        return size;
    }
}
