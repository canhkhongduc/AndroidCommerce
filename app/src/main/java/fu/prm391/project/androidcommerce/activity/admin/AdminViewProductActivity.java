package fu.prm391.project.androidcommerce.activity.admin;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import java.util.List;

import fu.prm391.project.androidcommerce.R;
import fu.prm391.project.androidcommerce.controller.listener.CustomCardViewListener;
import fu.prm391.project.androidcommerce.database.AppDatabase;
import fu.prm391.project.androidcommerce.database.entity.Product;
import fu.prm391.project.androidcommerce.activity.admin.adapter.AdminProductAdapter;

public class AdminViewProductActivity extends BaseAdminActivity {
    private RecyclerView adminViewProductList;
    private AppDatabase db;
    private List<Product> products;
    private AdminProductAdapter adapter;
    private Button btnAddProduct;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_view_product);
        btnAddProduct = findViewById(R.id.btnAddProduct);
        btnAddProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AdminViewProductActivity.this, AdminAddProductActivity.class));
            }
        });
        db = AppDatabase.getAppDatabase(this);
        Intent intent = getIntent();
        final int categoryId = intent.getIntExtra("catId",0);
        if (categoryId != 0){
            products = db.productDAO().getProductsByCategoryId(categoryId);
        } else {
            products = db.productDAO().getAll();
        }
        adminViewProductList = findViewById(R.id.adminViewProductList);
        adminViewProductList.setHasFixedSize(true);

        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayout.VERTICAL);
        adminViewProductList.setLayoutManager(llm);
        CustomCardViewListener listener = new CustomCardViewListener() {
            @Override
            public void onItemClick(View view, int position) {

            }
        };
        adapter = new AdminProductAdapter(products, this, listener);
        adminViewProductList.setAdapter(adapter);
    }
}
