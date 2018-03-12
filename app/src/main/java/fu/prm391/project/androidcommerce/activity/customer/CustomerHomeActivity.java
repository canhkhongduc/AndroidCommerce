package fu.prm391.project.androidcommerce.activity.customer;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import fu.prm391.project.androidcommerce.R;
import fu.prm391.project.androidcommerce.controller.listener.CustomProductViewListener;
import fu.prm391.project.androidcommerce.database.entity.OrderItem;
import fu.prm391.project.androidcommerce.database.utils.DatabaseInitializer;
import fu.prm391.project.androidcommerce.utils.ProductAdapter;
import fu.prm391.project.androidcommerce.database.AppDatabase;
import fu.prm391.project.androidcommerce.database.entity.Product;
import fu.prm391.project.androidcommerce.utils.SharedPreferenceUtil;

public class CustomerHomeActivity extends BaseCustomerActivity {
    private RecyclerView cardList;
    private List<Product> listProducts;
    private AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_home);

        cardList = findViewById(R.id.cardList);
        cardList.setHasFixedSize(true);

        db = AppDatabase.getAppDatabase(this);

        GridLayoutManager glm = new GridLayoutManager(this, 2);
        cardList.setLayoutManager(glm);
        listProducts = db.productDAO().getAll();

        ProductAdapter adapter = new ProductAdapter(listProducts, CustomerHomeActivity.this, new CustomProductViewListener() {
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
