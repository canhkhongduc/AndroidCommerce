package fu.prm391.project.androidcommerce.activity.customer;

import android.content.Intent;
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

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import fu.prm391.project.androidcommerce.R;
import fu.prm391.project.androidcommerce.activity.customer.adapter.CategoryAdapter;
import fu.prm391.project.androidcommerce.database.AppDatabase;
import fu.prm391.project.androidcommerce.database.entity.Category;

public class CategoryActivity extends BaseCustomerActivity {
    private RecyclerView recyclerView;
    private AppDatabase db;
    private List<Category> categoryList;
    private DividerItemDecoration dividerItemDecoration;
    private CategoryAdapter adapter;
    private LinearLayoutManager layoutManager;
    private BottomNavigationView navigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        db = AppDatabase.getAppDatabase(this);

        recyclerView = findViewById(R.id.category_recycler_view);

        categoryList = db.categoryDAO().getAll();
        Collections.sort(categoryList, new Comparator<Category>() {
            @Override
            public int compare(Category o1, Category o2) {
                return o1.getCategoryName().compareTo(o2.getCategoryName());
            }
        });

        adapter = new CategoryAdapter(categoryList);
        recyclerView.setAdapter(adapter);

        layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        dividerItemDecoration = new DividerItemDecoration(
                recyclerView.getContext(),
                layoutManager.getOrientation()
        );

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(dividerItemDecoration);

        navigation = findViewById(R.id.cus_category_botnav);
        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_category:
                        break;
                    case R.id.action_home:
                        changeActivityBottomNav(CategoryActivity.this, CustomerMenuActivity.class);
                        break;
                    case R.id.action_profile:
                        changeActivityBottomNav(CategoryActivity.this, ProfileActivity.class);
                        break;
                }

                return true;
            }
        });

        Menu menu = navigation.getMenu();
        MenuItem menuItem = menu.getItem(1);
        menuItem.setChecked(true);
    }
}
