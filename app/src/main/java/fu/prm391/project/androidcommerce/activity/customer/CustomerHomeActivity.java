package fu.prm391.project.androidcommerce.activity.customer;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.auth.api.signin.GoogleSignInAccount;

import java.util.ArrayList;
import java.util.List;

import fu.prm391.project.androidcommerce.R;
import fu.prm391.project.androidcommerce.controller.listener.CustomCardViewListener;
import fu.prm391.project.androidcommerce.database.entity.Category;
import fu.prm391.project.androidcommerce.database.entity.OrderItem;
import fu.prm391.project.androidcommerce.database.entity.User;
import fu.prm391.project.androidcommerce.utils.URIUtil;
import fu.prm391.project.androidcommerce.utils.customer.ProductAdapter;
import fu.prm391.project.androidcommerce.database.AppDatabase;
import fu.prm391.project.androidcommerce.database.entity.Product;
import fu.prm391.project.androidcommerce.utils.SharedPreferenceUtil;

public class CustomerHomeActivity extends BaseCustomerActivity {
    private RecyclerView cardList;
    private TextView tvCustomer;
    private List<Product> listProducts;
    private AppDatabase db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_home);
        cardList = findViewById(R.id.cardList);
        cardList.setHasFixedSize(true);
        tvCustomer = findViewById(R.id.tvCustomer);
        Intent intent = getIntent();
        String name = intent.getStringExtra("account");
        tvCustomer.setText(name);
        db = AppDatabase.getAppDatabase(this);

        GridLayoutManager glm = new GridLayoutManager(this, 2);
        cardList.setLayoutManager(glm);
        listProducts = db.productDAO().getAll();
        ProductAdapter adapter = new ProductAdapter(listProducts, CustomerHomeActivity.this, new CustomCardViewListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(CustomerHomeActivity.this, ProductDetailActivity.class);
                Product product = listProducts.get(position);
                intent.putExtra("productId", product.getProductId());
                startActivity(intent);
            }
        });
        cardList.setAdapter(adapter);

    }

    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferenceUtil util = new SharedPreferenceUtil();
        ArrayList<OrderItem> orderItems = util.getCart(CustomerHomeActivity.this, "cartItem");
    }

}


