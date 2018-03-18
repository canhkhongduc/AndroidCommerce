package fu.prm391.project.androidcommerce.activity.admin;

import android.content.Context;
import android.support.design.widget.BottomSheetDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import fu.prm391.project.androidcommerce.R;
import fu.prm391.project.androidcommerce.database.AppDatabase;
import fu.prm391.project.androidcommerce.database.entity.Category;

public class AdminViewCategoryActivity extends BaseAdminActivity {
    private ListView lvCategories;
    private List<Category> categories;
    private AppDatabase db;
    private Button btnAddCategory;
    private EditText etAddCategory;
    private Button btnAddCategoryConfirm;
    private ArrayAdapter<Category> adapter;
    private Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_view_category);
        context = this;
        db = AppDatabase.getAppDatabase(context);
        categories = db.categoryDAO().getAll();
        lvCategories = findViewById(R.id.listCategories);
        btnAddCategory = findViewById(R.id.btnAddCategory);

        final BottomSheetDialog addCategoryDialog = new BottomSheetDialog(context);
        final View bottomSheetView = getLayoutInflater().inflate(R.layout.bottom_sheet_add_category, null);
        addCategoryDialog.setContentView(bottomSheetView);
        etAddCategory = bottomSheetView.findViewById(R.id.etAddCategory);
        btnAddCategoryConfirm = bottomSheetView.findViewById(R.id.btnAddCategoryConfirm);
        btnAddCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addCategoryDialog.show();
                btnAddCategoryConfirm.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String catName = etAddCategory.getText().toString();
                        Category category = new Category(catName, false);
                        if(db.categoryDAO().getAll().contains(category)){
                            addCategoryDialog.dismiss();
                            Toast.makeText(context, "Category existed", Toast.LENGTH_LONG).show();
                        } else {
                            db.categoryDAO().insert(category);
                            adapter = new ArrayAdapter<Category>(context, android.R.layout.simple_list_item_1, db.categoryDAO().getAll());
                            lvCategories.setAdapter(adapter);
                            adapter.notifyDataSetChanged();
                            addCategoryDialog.dismiss();
                        }
                    }
                });
            }
        });
        adapter = new ArrayAdapter<Category>(context, android.R.layout.simple_list_item_1, categories);
        lvCategories.setAdapter(adapter);
    }
}
