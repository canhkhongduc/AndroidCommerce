package fu.prm391.project.androidcommerce.activity.admin;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import fu.prm391.project.androidcommerce.R;
import fu.prm391.project.androidcommerce.database.AppDatabase;
import fu.prm391.project.androidcommerce.database.entity.Category;

public class AdminAddCategoryActivity extends BaseAdminActivity {
    private ImageView ivAddCategory;
    private EditText etAddCategoryName;
    private final int PICK_IMAGE = 120;
    private Uri selectedImage;
    private Button btnAddCategoryConfirm;
    private AppDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_add_category);
        ivAddCategory = findViewById(R.id.ivAddCategory);
        etAddCategoryName = findViewById(R.id.etCategoryName);
        btnAddCategoryConfirm = findViewById(R.id.btnAddCategoryConfirm);
        db = AppDatabase.getAppDatabase(this);
        ivAddCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_OPEN_DOCUMENT);
                intent.addCategory(Intent.CATEGORY_OPENABLE);
                startActivityForResult(Intent.createChooser(intent,"Select product image"), PICK_IMAGE);
            }
        });
        btnAddCategoryConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String categoryName = etAddCategoryName.getText().toString();
                Category category = new Category(categoryName, selectedImage.toString(), false);
                db.categoryDAO().insert(category);
                startActivity(new Intent(AdminAddCategoryActivity.this, AdminViewCategoryActivity.class));
            }
        });

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == PICK_IMAGE){
            if(data != null) {
                selectedImage = data.getData();
                ivAddCategory.setImageURI(selectedImage);
            }
        }
    }
}
