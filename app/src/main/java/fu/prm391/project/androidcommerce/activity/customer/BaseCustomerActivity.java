package fu.prm391.project.androidcommerce.activity.customer;

import android.content.Context;
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
import fu.prm391.project.androidcommerce.database.entity.Review;
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

    protected void changeActivityBottomNav(Context context, Class destinationClass){
        Intent intent = new Intent(context, destinationClass);
        intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivity(intent);
        finish();
        overridePendingTransition(0, 0);
    }

    protected float calculateAveragePoint(List<Review> reviewList) {
        float point = 0;

        for (Review review : reviewList) {
            point += review.getRating();
        }

        return reviewList.size() != 0 ? point / reviewList.size() : 0;
    }
}
