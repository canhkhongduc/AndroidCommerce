package fu.prm391.project.androidcommerce.activity.customer;

import android.graphics.Canvas;
import android.graphics.Rect;
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
import android.widget.ImageView;

import fu.prm391.project.androidcommerce.R;
import fu.prm391.project.androidcommerce.activity.customer.adapter.PopularProductListAdapter;
import fu.prm391.project.androidcommerce.database.AppDatabase;
import fu.prm391.project.androidcommerce.database.entity.Product;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_menu);

        db = AppDatabase.getAppDatabase(this);
        productList = db.productDAO().getAll();

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.cus_menu_botnav);
//        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        Menu menu = navigation.getMenu();
        MenuItem menuItem = menu.getItem(0);
        menuItem.setChecked(true);

        carouselView = findViewById(R.id.carouselPromo);
        carouselView.setPageCount(promoImages.length);

        carouselView.setImageListener(imageListener);

        recyclerView = findViewById(R.id.popular_product_list);

        PopularProductListAdapter listAdapter = new PopularProductListAdapter(productList);
        recyclerView.setAdapter(listAdapter);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);

        dividerItemDecoration = new DividerItemDecoration(
                recyclerView.getContext(),
                layoutManager.getOrientation()
        );

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(dividerItemDecoration);
        recyclerView.addItemDecoration(new SpacesItemDecoration(30));
    }

    private ImageListener imageListener = new ImageListener() {
        @Override
        public void setImageForPosition(int position, ImageView imageView) {
            imageView.setImageResource(promoImages[position]);
        }
    };

    private class SpacesItemDecoration extends RecyclerView.ItemDecoration {
        private int space;

        public SpacesItemDecoration(int space) {
            this.space = space;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            if (parent.getChildAdapterPosition(view) != 0) {
                outRect.left = space;
            }
        }
    }
}
