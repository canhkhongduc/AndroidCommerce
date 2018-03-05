package fu.prm391.project.androidcommerce.customerActivity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import fu.prm391.project.androidcommerce.R;
import fu.prm391.project.androidcommerce.adapter.ProductAdapter;
import fu.prm391.project.androidcommerce.adminActivity.AdminHomeActivity;
import fu.prm391.project.androidcommerce.controller.listener.CustomItemClickListener;
import fu.prm391.project.androidcommerce.database.AppDatabase;
import fu.prm391.project.androidcommerce.database.entity.Product;

public class CustomerHomeActivity extends AppCompatActivity {
    private RecyclerView cardList;
    private List<Product> listProducts;
    private AppDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_home);
        cardList = (RecyclerView) findViewById(R.id.cardList);
        cardList.setHasFixedSize(true);
        db = AppDatabase.getAppDatabase(this);
        GridLayoutManager glm = new GridLayoutManager(this, 2);
        cardList.setLayoutManager(glm);
        listProducts = new ArrayList<Product>();
        listProducts = db.productDAO().getAll();
        ProductAdapter adapter = new ProductAdapter(listProducts, CustomerHomeActivity.this, new CustomItemClickListener() {
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


}
