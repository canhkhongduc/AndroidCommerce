package fu.prm391.project.androidcommerce.activity.admin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.List;

import fu.prm391.project.androidcommerce.R;
import fu.prm391.project.androidcommerce.controller.listener.CustomCardViewListener;
import fu.prm391.project.androidcommerce.database.AppDatabase;
import fu.prm391.project.androidcommerce.database.entity.Order;
import fu.prm391.project.androidcommerce.utils.OrderAdapter;

public class AdminHomeActivity extends AppCompatActivity {
    private RecyclerView orderCardView;
    private List<Order> orders;
    private AppDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home);
        orderCardView = findViewById(R.id.orderList);
        orderCardView.setHasFixedSize(true);
        db = AppDatabase.getAppDatabase(this);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayout.VERTICAL);
        orderCardView.setLayoutManager(llm);
        orders = db.orderDAO().getAll();
        OrderAdapter adapter = new OrderAdapter(orders, this, new CustomCardViewListener() {
            @Override
            public void onItemClick(View view, int position) {
                Order order = orders.get(position);
            }
        });
        orderCardView.setAdapter(adapter);
    }
}
