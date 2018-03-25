package fu.prm391.project.androidcommerce.activity.customer;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import fu.prm391.project.androidcommerce.R;
import fu.prm391.project.androidcommerce.database.AppDatabase;
import fu.prm391.project.androidcommerce.database.entity.Category;
import fu.prm391.project.androidcommerce.database.entity.OrderItem;
import fu.prm391.project.androidcommerce.database.entity.Product;
import fu.prm391.project.androidcommerce.utils.SharedPreferenceUtil;

public class ProductDetailActivity extends BaseCustomerActivity {
    private AppDatabase db;
    private ImageView imgProduct;
    private TextView txtProductName;
    private TextView txtProductPrice;
    private RatingBar txtProductRating;
    private Button btnAddToCart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);

        imgProduct = findViewById(R.id.productDetailActivity_imgProduct);
        txtProductName = findViewById(R.id.productDetailActivity_txtProductName);
        txtProductPrice = findViewById(R.id.productDetailActivity_txtProductPrice);
        txtProductRating = findViewById(R.id.productDetailActivity_ratingBarProduct);
        btnAddToCart = findViewById(R.id.btnAddToCart);

        Intent intent = getIntent();
        final int productId = intent.getIntExtra("productId", 0);
        db = AppDatabase.getAppDatabase(this);
        final Product product = db.productDAO().getProductByProductId(productId);
        Category category = db.categoryDAO().getCategoryByCategoryId(product.getCategoryId());
        imgProduct.setImageURI(Uri.parse(product.getProductImagePath()));
        txtProductName.setText(product.getProductName());
        txtProductPrice.setText(new DecimalFormat("#,###.##").format(product.getProductPrice()) + "đ");
        txtProductRating.setRating((float) product.getAverageRating());


        btnAddToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(ProductDetailActivity.this, "Item added to cart", Toast.LENGTH_LONG).show();

                invalidateOptionsMenu();
                OrderItem orderItem = new OrderItem(-1, product.getProductId(), 1);

                SharedPreferenceUtil util = new SharedPreferenceUtil();
                ArrayList<OrderItem> orderItems = util.getCart(ProductDetailActivity.this, "cartItem");

                if (orderItems == null) {
                    orderItems = new ArrayList<>();
                    orderItems.add(orderItem);
                } else {
                    if (orderItems.contains(orderItem)) {
                        orderItem = getOrderItemFromList(orderItems, product.getProductId());
                        orderItem.setQuantity(orderItem.getQuantity() + 1);
                    } else {
                        orderItems.add(orderItem);
                    }
                }
                util.addToCart(ProductDetailActivity.this, orderItems, "cartItem");
            }
        });
    }

    private OrderItem getOrderItemFromList(List<OrderItem> orderItemList, int productId) {
        for (OrderItem orderItem : orderItemList) {
            if (orderItem.getProductId() == productId) {
                return orderItem;
            }
        }
        return null;
    }
}