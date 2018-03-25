package fu.prm391.project.androidcommerce.activity.customer;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import java.util.List;

import fu.prm391.project.androidcommerce.R;
import fu.prm391.project.androidcommerce.activity.customer.adapter.ProductAdapter;
import fu.prm391.project.androidcommerce.activity.customer.adapter.ItemDecoration.HorizontalSpacesItemDecoration;
import fu.prm391.project.androidcommerce.activity.customer.adapter.ItemDecoration.VerticalSpacesItemDecoration;
import fu.prm391.project.androidcommerce.database.AppDatabase;
import fu.prm391.project.androidcommerce.database.entity.Product;

public class ProductListActivity extends BaseCustomerActivity {

    private RecyclerView recyclerView;
    private DividerItemDecoration dividerItemDecoration;
    private RecyclerView.ItemDecoration itemDecoration;
    private TextView txtCategory;
    private List<Product> productList;
    private AppDatabase db;
    private GridLayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list);

        recyclerView = findViewById(R.id.productActivity_productList);
        txtCategory = findViewById(R.id.productActivity_txtCategory);

        int categoryId = getIntent().getIntExtra("categoryId", -1);
        String categoryName = getIntent().getStringExtra("categoryName");

        txtCategory.setText("Category: " + categoryName);

        db = AppDatabase.getAppDatabase(this);
        productList = db.productDAO().getProductsByCategory(categoryId);

        ProductAdapter productAdapter = new ProductAdapter(productList);

        recyclerView.setAdapter(productAdapter);

        layoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(layoutManager);

        dividerItemDecoration = new DividerItemDecoration(
                recyclerView.getContext(),
                layoutManager.getOrientation()
        );

        recyclerView.addItemDecoration(new HorizontalSpacesItemDecoration(30, true));
        recyclerView.addItemDecoration(new VerticalSpacesItemDecoration(20));
    }
}
