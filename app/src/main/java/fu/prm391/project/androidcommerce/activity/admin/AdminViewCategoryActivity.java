package fu.prm391.project.androidcommerce.activity.admin;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.BottomSheetDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import fu.prm391.project.androidcommerce.R;
import fu.prm391.project.androidcommerce.activity.admin.adapter.AdminCategoryAdapter;
import fu.prm391.project.androidcommerce.database.AppDatabase;
import fu.prm391.project.androidcommerce.database.entity.Category;

public class AdminViewCategoryActivity extends BaseAdminActivity {
    private RecyclerView categoryList;
    private List<Category> categories;
    private AppDatabase db;
    private Button btnAddCategory;
    private AdminCategoryAdapter adapter;
    private Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_view_category);
        context = this;
        db = AppDatabase.getAppDatabase(context);
        categories = db.categoryDAO().getAll();
        categoryList = findViewById(R.id.adminCategoryList);
        categoryList.setHasFixedSize(true);
        btnAddCategory = findViewById(R.id.btnAddCategory);
        categories = db.categoryDAO().getAll();
        adapter = new AdminCategoryAdapter(categories, context);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayout.VERTICAL);
        categoryList.setLayoutManager(llm);
        categoryList.setAdapter(adapter);
        btnAddCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AdminViewCategoryActivity.this, AdminAddCategoryActivity.class));
            }
        });
    }
}
