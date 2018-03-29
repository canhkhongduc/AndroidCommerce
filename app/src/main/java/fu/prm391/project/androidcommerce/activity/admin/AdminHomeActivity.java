package fu.prm391.project.androidcommerce.activity.admin;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import fu.prm391.project.androidcommerce.R;
import fu.prm391.project.androidcommerce.database.AppDatabase;
import fu.prm391.project.androidcommerce.database.entity.Order;
import fu.prm391.project.androidcommerce.database.entity.Product;
import fu.prm391.project.androidcommerce.database.entity.User;

public class AdminHomeActivity extends BaseAdminActivity {
    private TextView tvOrderNumber, tvUserNumber, tvProductNumber, tvOutOfStockNumber;
    private ImageView ivOrderNumber, ivUserNumber, ivProductNumber, ivOutOfStockNumber;
    private AppDatabase db;
    private Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home);
        context = this;
        tvOrderNumber = findViewById(R.id.tvOrderNumber);
        tvUserNumber = findViewById(R.id.tvAdminUserName);
        tvProductNumber = findViewById(R.id.tvProductNumber);
        tvOutOfStockNumber = findViewById(R.id.tvStockNumber);
        ivOrderNumber = findViewById(R.id.ivOrder);
        ivUserNumber = findViewById(R.id.ivUserNumber);
        ivProductNumber = findViewById(R.id.ivProductNumber);
        ivOutOfStockNumber = findViewById(R.id.ivOutOfStock);
        db = AppDatabase.getAppDatabase(this);
        final List<Order> orders = db.orderDAO().getNewOrders(false);
        List<Product> products = db.productDAO().getAll();
        List<User> users = db.userDAO().getAll();
        List<Product> outOfStockProducts = db.productDAO().getOutOfStockProducts();
        tvOrderNumber.setText("" + orders.size());
        tvProductNumber.setText("" + products.size());
        tvUserNumber.setText("" + users.size());
        tvOutOfStockNumber.setText("" + outOfStockProducts.size());
        ivOrderNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (orders.size() == 0){
                    Toast.makeText(context, "You have 0 new orders", Toast.LENGTH_LONG).show();
                } else {
                    startActivity(new Intent(AdminHomeActivity.this, AdminViewOrderActivity.class));
                }
            }
        });
        ivUserNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AdminHomeActivity.this, AdminViewUserActivity.class));
            }
        });
        ivProductNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AdminHomeActivity.this, AdminViewProductActivity.class));
            }
        });
        ivOutOfStockNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (db.productDAO().getOutOfStockProducts().size() == 0){
                    Toast.makeText(context, "You have 0 out of stock products", Toast.LENGTH_LONG).show();
                } else {
                    Intent intent = new Intent(AdminHomeActivity.this, AdminViewProductActivity.class);
                    intent.putExtra("stock", 10);
                    startActivity(intent);
                }
            }
        });
    }
}
