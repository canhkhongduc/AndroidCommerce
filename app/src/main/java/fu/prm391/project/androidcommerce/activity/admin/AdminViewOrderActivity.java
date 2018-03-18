package fu.prm391.project.androidcommerce.activity.admin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.LinearLayout;

import java.util.List;

import fu.prm391.project.androidcommerce.R;
import fu.prm391.project.androidcommerce.database.AppDatabase;
import fu.prm391.project.androidcommerce.database.entity.OrderItem;
import fu.prm391.project.androidcommerce.utils.admin.AdminOrderAdapter;

public class AdminViewOrderActivity extends BaseAdminActivity {
    private RecyclerView viewOrderCardView;
    private List<OrderItem> orderItems;
    private AppDatabase db;
    private AdminOrderAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_view_order);
        Intent intent = getIntent();
        int orderId = intent.getIntExtra("orderId", 0);

        viewOrderCardView = findViewById(R.id.adminViewOrderList);
        viewOrderCardView.setHasFixedSize(true);
        db = AppDatabase.getAppDatabase(this);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayout.VERTICAL);
        viewOrderCardView.setLayoutManager(llm);
        orderItems = db.orderItemDAO().getOrderItemByOrderId(orderId);
        adapter = new AdminOrderAdapter(orderItems,this);
        viewOrderCardView.setAdapter(adapter);
    }
}
