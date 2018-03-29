package fu.prm391.project.androidcommerce.activity.customer;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import java.util.List;

import fu.prm391.project.androidcommerce.R;
import fu.prm391.project.androidcommerce.controller.listener.CustomCardViewListener;
import fu.prm391.project.androidcommerce.database.AppDatabase;
import fu.prm391.project.androidcommerce.database.entity.Order;
import fu.prm391.project.androidcommerce.utils.SharedPreferenceUtil;
import fu.prm391.project.androidcommerce.activity.customer.adapter.OrderAdapter;

public class CustomerViewOrderActivity extends BaseCustomerActivity {
    private RecyclerView customerViewOrders;
    private List<Order> orders;
    private AppDatabase db;
    private SharedPreferenceUtil util;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_view_order);
        db = AppDatabase.getAppDatabase(this);
        util = new SharedPreferenceUtil();
//        int userId =  util.getUser(this);
        customerViewOrders = findViewById(R.id.customerOrderList);
        customerViewOrders.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayout.VERTICAL);
        customerViewOrders.setLayoutManager(llm);
//        orders = db.orderDAO().getOrdersByUserId(userId);
        OrderAdapter adapter = new OrderAdapter(orders, this, new CustomCardViewListener() {
            @Override
            public void onItemClick(View view, int position) {
                Order order = orders.get(position);
                Intent intent = new Intent(CustomerViewOrderActivity.this, CustomerViewOrderDetailActivity.class);
                intent.putExtra("orderId", order.getOrderId());
                startActivity(intent);
            }
        });
        customerViewOrders.setAdapter(adapter);
    }
}
