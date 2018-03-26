package fu.prm391.project.androidcommerce.activity.customer;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableString;
import android.text.style.RelativeSizeSpan;
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
import fu.prm391.project.androidcommerce.activity.customer.adapter.ItemDecoration.VerticalSpacesItemDecoration;
import fu.prm391.project.androidcommerce.activity.customer.adapter.ReviewAdapter;
import fu.prm391.project.androidcommerce.database.AppDatabase;
import fu.prm391.project.androidcommerce.database.entity.Category;
import fu.prm391.project.androidcommerce.database.entity.OrderItem;
import fu.prm391.project.androidcommerce.database.entity.Product;
import fu.prm391.project.androidcommerce.database.entity.Review;
import fu.prm391.project.androidcommerce.utils.SharedPreferenceUtil;

public class ProductDetailActivity extends BaseCustomerActivity {
    private AppDatabase db;
    private ImageView imgProduct;
    private TextView txtProductName;
    private TextView txtProductPrice;
    private RatingBar txtProductRating;
    private Button btnAddToCart;
    private TextView txtGiveReview;
    private RecyclerView recyclerView;
    private ReviewAdapter reviewAdapter;
    private TextView txtAverageRating;
    private RatingBar ratingBarAverage;
    private TextView txtTotalReview;
    private List<Review> reviewList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);

        imgProduct = findViewById(R.id.productDetailActivity_imgProduct);
        txtProductName = findViewById(R.id.productDetailActivity_txtProductName);
        txtProductPrice = findViewById(R.id.productDetailActivity_txtProductPrice);
        txtProductRating = findViewById(R.id.productDetailActivity_ratingBarProduct);
        btnAddToCart = findViewById(R.id.btnAddToCart);
        txtGiveReview = findViewById(R.id.productDetailActivity_txtGiveReview);
        recyclerView = findViewById(R.id.productDetailActivity_reviewList);

        txtAverageRating = findViewById(R.id.productDetailActivity_txtTotalPoint);
        txtTotalReview = findViewById(R.id.productDetailActivity_txtTotalReview);
        ratingBarAverage = findViewById(R.id.productDetailActivity_ratingBarTotal);

        Intent intent = getIntent();
        final int productId = intent.getIntExtra("productId", 0);
        db = AppDatabase.getAppDatabase(this);

        reviewList = db.reviewDAO().getAll();

        final Product product = db.productDAO().getProductByProductId(productId);
        Category category = db.categoryDAO().getCategoryByCategoryId(product.getCategoryId());
        imgProduct.setImageURI(Uri.parse(product.getProductImagePath()));
        txtProductName.setText(product.getProductName());
        txtProductPrice.setText(new DecimalFormat("#,###.##").format(product.getProductPrice()) + "đ");

        reviewAdapter = new ReviewAdapter(reviewList, db);
        recyclerView.setAdapter(reviewAdapter);

        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView.addItemDecoration(new VerticalSpacesItemDecoration(5));

        txtGiveReview.setOnClickListener(new onGiveReviewClickListener(this, product));

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

    @Override
    protected void onResume() {
        super.onResume();

        reviewList = db.reviewDAO().getAll();
        reviewAdapter = new ReviewAdapter(reviewList, db);
        reviewAdapter.notifyDataSetChanged();

        String s = String.format("%.2f", calculateAveragePoint(reviewList)) + " on 5";
        SpannableString ss1 = new SpannableString(s);
        ss1.setSpan(new RelativeSizeSpan(2f), 0, 1, 0); // set size

        txtProductRating.setRating(calculateAveragePoint(reviewList));
        ratingBarAverage.setRating(calculateAveragePoint(reviewList));
        txtTotalReview.setText(reviewList.size() + " Reviews");
        txtAverageRating.setText(ss1);
    }

    private OrderItem getOrderItemFromList(List<OrderItem> orderItemList, int productId) {
        for (OrderItem orderItem : orderItemList) {
            if (orderItem.getProductId() == productId) {
                return orderItem;
            }
        }
        return null;
    }

    private class onGiveReviewClickListener implements View.OnClickListener {
        private Context context;
        private Product product;

        public onGiveReviewClickListener(Context context, Product product) {
            this.context = context;
            this.product = product;
        }

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(context, CustomerReviewActivity.class);
            intent.putExtra("productId", product.getProductId());
            intent.putExtra("productName", product.getProductName());
            startActivity(intent);
        }
    }
}