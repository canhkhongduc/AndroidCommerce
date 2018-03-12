package fu.prm391.project.androidcommerce.customerActivity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import fu.prm391.project.androidcommerce.R;
import fu.prm391.project.androidcommerce.controller.listener.CustomCardViewListener;
import fu.prm391.project.androidcommerce.database.entity.Category;
import fu.prm391.project.androidcommerce.database.entity.OrderItem;
import fu.prm391.project.androidcommerce.utils.ProductAdapter;
import fu.prm391.project.androidcommerce.database.AppDatabase;
import fu.prm391.project.androidcommerce.database.entity.Product;
import fu.prm391.project.androidcommerce.utils.SharedPreferenceUtil;

public class CustomerHomeActivity extends AppCompatActivity {
    private RecyclerView cardList;
    private TextView tvCart;
    private List<Product> listProducts;
    private AppDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_home);
        tvCart = findViewById(R.id.tvCart);
        cardList = (RecyclerView) findViewById(R.id.cardList);
        cardList.setHasFixedSize(true);
        db = AppDatabase.getAppDatabase(this);
        GridLayoutManager glm = new GridLayoutManager(this, 2);
        cardList.setLayoutManager(glm);
        listProducts = db.productDAO().getAll();
        if (listProducts.size() == 0){
            Category cat1 = new Category("fruit", false);
            db.categoryDAO().insert(cat1);
            Category cat2 = new Category("car", false);
            db.categoryDAO().insert(cat2);
            Category cat3 = new Category("girl", false);
            db.categoryDAO().insert(cat3);
            Category cat4 = new Category("computer", false);
            db.categoryDAO().insert(cat4);
            Category cat5 = new Category("shoe", false);
            db.categoryDAO().insert(cat5);
            Product product1 = new Product("banana", R.drawable.banana, 1, 1000,
                    "Cheapest banana in the world!",false, 4.5);
            db.productDAO().insert(product1);
            Product product2 = new Product("apple", R.drawable.apple, 1, 1200,
                    "Cheapest apple in the world!",false, 4.6);
            db.productDAO().insert(product2);
            Product product3 = new Product("lamborghini", R.drawable.lamborgini, 2, 1000000,
                    "Fastest car in the world!",false, 5);
            db.productDAO().insert(product3);
            Product product4 = new Product("Tesla", R.drawable.tesla, 2, 1200000,
                    "You want an electric car?",false, 4.5);
            db.productDAO().insert(product4);
            Product product5 = new Product("Ngoc Trinh", R.drawable.ngoctrinh, 3, 100000000,
                    "Want a date with a Viet super model?",false, 4.9);
            db.productDAO().insert(product5);
            Product product6 = new Product("Hera", R.drawable.hera, 3, 1200000,
                    "She's gonna blow your mind!",false, 4.2);
            db.productDAO().insert(product6);
            Product product7 = new Product("Mac book", R.drawable.mac, 4, 10000,
                    "Better computer, better life",false, 4.3);
            db.productDAO().insert(product7);
            Product product8 = new Product("Women' running shoe", R.drawable.shoe1, 5, 1200,
                    "Its a running shoe. And it's for women!",false, 4.5);
            db.productDAO().insert(product8);
            listProducts = db.productDAO().getAll();
        }
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
        if (orderItems != null && orderItems.size() > 0){
            tvCart.setText(""+orderItems.size());
        }
    }
}
