package fu.prm391.project.androidcommerce.activity.customer;

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import fu.prm391.project.androidcommerce.R;
import fu.prm391.project.androidcommerce.activity.customer.adapter.ItemDecoration.HorizontalSpacesItemDecoration;
import fu.prm391.project.androidcommerce.activity.customer.adapter.PopularProductListAdapter;
import fu.prm391.project.androidcommerce.database.AppDatabase;
import fu.prm391.project.androidcommerce.database.entity.Category;
import fu.prm391.project.androidcommerce.database.entity.Product;
import fu.prm391.project.androidcommerce.utils.SharedPreferenceUtil;

import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;

import java.util.List;

public class CustomerMenuActivity extends BaseCustomerActivity {

    private CarouselView carouselView;
    private int[] promoImages = {R.drawable.promo1, R.drawable.promo2, R.drawable.promo3};
    private RecyclerView recyclerView;
    private DividerItemDecoration dividerItemDecoration;
    private RecyclerView.ItemDecoration itemDecoration;
    private List<Product> productList;
    private AppDatabase db;
    private LinearLayoutManager layoutManager;
    private FrameLayout framePhone;
    private FrameLayout frameCar;
    private FrameLayout frameComputer;
    private FrameLayout frameFood;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_menu);
        db = AppDatabase.getAppDatabase(this);
        productList = db.productDAO().getProductsByNumber(5);

        framePhone = findViewById(R.id.frame1);
        frameCar = findViewById(R.id.frame2);
        frameComputer = findViewById(R.id.frame3);
        frameFood = findViewById(R.id.frame4);

        framePhone.setOnClickListener(new onFrameClickListener(this, db.categoryDAO().getCategoryByCategoryName("Phone")));
        frameCar.setOnClickListener(new onFrameClickListener(this, db.categoryDAO().getCategoryByCategoryName("Car")));
        frameComputer.setOnClickListener(new onFrameClickListener(this, db.categoryDAO().getCategoryByCategoryName("Computer")));
        frameFood.setOnClickListener(new onFrameClickListener(this, db.categoryDAO().getCategoryByCategoryName("Food")));

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.cus_menu_botnav);
        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_category:
                        changeActivityBottomNav(CustomerMenuActivity.this, CategoryActivity.class);
                        break;
                    case R.id.action_home:
                        break;
                    case R.id.action_profile:
                        changeActivityBottomNav(CustomerMenuActivity.this, ProfileActivity.class);
                        break;
                }

                return true;
            }
        });

        Menu menu = navigation.getMenu();
        MenuItem menuItem = menu.getItem(0);
        menuItem.setChecked(true);

        carouselView = findViewById(R.id.carouselPromo);
        carouselView.setPageCount(promoImages.length);

        carouselView.setImageListener(imageListener);

        recyclerView = findViewById(R.id.popular_product_list);

        PopularProductListAdapter listAdapter = new PopularProductListAdapter(productList);
        recyclerView.setAdapter(listAdapter);

        layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);

        dividerItemDecoration = new DividerItemDecoration(
                recyclerView.getContext(),
                layoutManager.getOrientation()
        );

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(dividerItemDecoration);
        recyclerView.addItemDecoration(new HorizontalSpacesItemDecoration(30, false));
    }

    private ImageListener imageListener = new ImageListener() {
        @Override
        public void setImageForPosition(int position, ImageView imageView) {
            imageView.setImageResource(promoImages[position]);
        }
    };

    private class onFrameClickListener implements View.OnClickListener {
        private Context context;
        private Category category;

        public onFrameClickListener(Context context, Category category) {
            this.context = context;
            this.category = category;
        }

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(context, ProductListActivity.class);
            intent.putExtra("categoryId", category.getCategoryId());
            intent.putExtra("categoryName", category.getCategoryName());
            context.startActivity(intent);
        }
    }
}
