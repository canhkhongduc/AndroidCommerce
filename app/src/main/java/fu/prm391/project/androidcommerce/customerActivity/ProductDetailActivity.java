package fu.prm391.project.androidcommerce.customerActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import fu.prm391.project.androidcommerce.LoginActivity;
import fu.prm391.project.androidcommerce.R;
import fu.prm391.project.androidcommerce.database.AppDatabase;
import fu.prm391.project.androidcommerce.database.entity.Category;
import fu.prm391.project.androidcommerce.database.entity.OrderItem;
import fu.prm391.project.androidcommerce.database.entity.Product;
import fu.prm391.project.androidcommerce.utils.SharedPreferenceUtil;

public class ProductDetailActivity extends AppCompatActivity {
    private AppDatabase db;
    private ImageView ivProduct;
    private TextView tvProductName;
    private TextView tvProductPrice;
    private TextView tvCategoryName;
    private TextView tvProductDescription;
    private TextView tvProductRating;
    private Button btnAddToCart;
    private Button btnBuyNow;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);
        ivProduct = (ImageView) findViewById(R.id.ivProductDetail);
        tvProductName = (TextView) findViewById(R.id.tvProductNameDetail);
        tvCategoryName = (TextView) findViewById(R.id.tvCategoryDetail);
        tvProductPrice = (TextView) findViewById(R.id.tvProductPriceDetail);
        tvProductDescription = (TextView) findViewById(R.id.tvProductDescription);
        tvProductRating = (TextView) findViewById(R.id.tvRating);
        Intent intent = getIntent();
        int productId = intent.getIntExtra("productId",0);
        db = AppDatabase.getAppDatabase(this);
        final Product product = db.productDAO().getProductByProductId(productId);
        Category category = db.categoryDAO().getCategoryByCategoryId(product.getCategoryId());
        ivProduct.setImageResource(product.getProductImagePath());
        tvProductName.setText(product.getProductName());
        tvProductPrice.setText("" + product.getProductPrice() + "00 VND");
        tvCategoryName.setText(category.getCategoryName());
        tvProductRating.setText("" + product.getAverageRating());
        tvProductDescription.setText(product.getProductDescription());

        btnAddToCart = findViewById(R.id.btnAddToCart);
        btnBuyNow = findViewById(R.id.btnBuyNow);
        btnAddToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(ProductDetailActivity.this, "Item added to cart", Toast.LENGTH_LONG).show();
                OrderItem orderItem = new OrderItem(-1,product.getProductId(), 1);
                SharedPreferenceUtil util = new SharedPreferenceUtil();
                ArrayList<OrderItem> orderItems = util.getCart(ProductDetailActivity.this, "cartItem");;
                if (orderItems == null) {
                    orderItems = new ArrayList<>();
                    orderItems.add(orderItem);
                    util.addToCart(ProductDetailActivity.this, orderItems, "cartItem");
                } else if(orderItems.contains(orderItem)){
                    orderItems.remove(orderItem);
                    orderItem.setQuantity(orderItem.getQuantity() + 1);
                    orderItems.add(orderItem);
                    util.removePreference(ProductDetailActivity.this, "cartItem");
                    util.addToCart(ProductDetailActivity.this, orderItems, "cartItem");
                } else {
                    orderItems.add(orderItem);
                    util.removePreference(ProductDetailActivity.this, "cartItem");
                    util.addToCart(ProductDetailActivity.this, orderItems, "cartItem");
                }
            }
        });
        btnBuyNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(ProductDetailActivity.this, "Item added to Buy now", Toast.LENGTH_LONG).show();
            }
        });
    }

}
