package fu.prm391.project.androidcommerce.customerActivity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.List;

import fu.prm391.project.androidcommerce.R;
import fu.prm391.project.androidcommerce.controller.listener.CustomCheckOutListener;
import fu.prm391.project.androidcommerce.database.AppDatabase;
import fu.prm391.project.androidcommerce.database.entity.OrderItem;
import fu.prm391.project.androidcommerce.utils.OrderItemAdapter;
import fu.prm391.project.androidcommerce.utils.SharedPreferenceUtil;

public class CheckOutActivity extends AppCompatActivity {
    private RecyclerView checkoutCardView;
    private List<OrderItem> orderItems;
    private AppDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_out);
        checkoutCardView = findViewById(R.id.checkOutItemList);
        checkoutCardView.setHasFixedSize(true);
        db = AppDatabase.getAppDatabase(this);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayout.VERTICAL);
        checkoutCardView.setLayoutManager(llm);

        SharedPreferenceUtil util = new SharedPreferenceUtil();
        orderItems = util.getCart(this,"cartItem");

        CustomCheckOutListener listener = new CustomCheckOutListener() {
            @Override
            public void onItemEdit(View view, int position) {
                Toast.makeText(CheckOutActivity.this, "Edit " + position, Toast.LENGTH_SHORT).show();
            }
            public void onItemRemove(View view, int position){
                orderItems.remove(position);

            }
        };
        OrderItemAdapter adapter = new OrderItemAdapter(orderItems, CheckOutActivity.this, listener);
        adapter.notifyDataSetChanged();
        checkoutCardView.setAdapter(adapter);
    }
}
