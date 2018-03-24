package fu.prm391.project.androidcommerce.activity.customer;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import java.util.List;

import fu.prm391.project.androidcommerce.R;
import fu.prm391.project.androidcommerce.database.AppDatabase;
import fu.prm391.project.androidcommerce.database.entity.OrderItem;
import fu.prm391.project.androidcommerce.activity.admin.adapter.AdminOrderAdapter;

public class CustomerViewOrderDetailActivity extends BaseCustomerActivity {
    private RecyclerView customerViewOrderDetail;
    private List<OrderItem> orderItems;
    private AppDatabase db;
    private AdminOrderAdapter adapter;
    private Button btnReceiveProducts;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_view_order_detail);
        Intent intent = getIntent();
        final int orderId = intent.getIntExtra("orderId", 0);
        customerViewOrderDetail = findViewById(R.id.customerViewOrderDetail);
        customerViewOrderDetail.setHasFixedSize(true);
        db = AppDatabase.getAppDatabase(this);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayout.VERTICAL);
        customerViewOrderDetail.setLayoutManager(llm);
        orderItems = db.orderItemDAO().getOrderItemByOrderId(orderId);
        adapter = new AdminOrderAdapter(orderItems,this);
        customerViewOrderDetail.setAdapter(adapter);
        btnReceiveProducts = findViewById(R.id.btnReceivedProducts);
        if (db.orderDAO().getOrderByOrderId(orderId).isCompleted())
            btnReceiveProducts.setVisibility(View.INVISIBLE);
        btnReceiveProducts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.orderDAO().updateOrderStatus(orderId, true);
                Intent intent1 = new Intent(CustomerViewOrderDetailActivity.this, CustomerReviewActivity.class);
                intent1.putExtra("orderId", orderId);
                startActivity(intent1);

            }
        });
    }
}
