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
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.List;

import fu.prm391.project.androidcommerce.R;
import fu.prm391.project.androidcommerce.database.AppDatabase;
import fu.prm391.project.androidcommerce.database.entity.Category;
import fu.prm391.project.androidcommerce.database.entity.Product;

public class AdminAddProductActivity extends BaseAdminActivity {
    private EditText etAddProductName, etAddProductDescription, etAddProductPrice;
    private RadioGroup rgAddCategory;
    private ImageView ivAddProductImage;
    private AppDatabase db;
    private final int PICK_IMAGE = 100;
    private Button btnAddProductConfirm;
    private Uri selectedImage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_add_product);
        etAddProductName = findViewById(R.id.etAddProductName);
        etAddProductDescription = findViewById(R.id.etAddProductDescription);
        etAddProductPrice = findViewById(R.id.etAddPrice);
        rgAddCategory = findViewById(R.id.rgCategoryGroup);
        ivAddProductImage = findViewById(R.id.ivAddProductImage);
        btnAddProductConfirm = findViewById(R.id.btnAddProductConfirm);
        db = AppDatabase.getAppDatabase(this);
        List<Category> categories = db.categoryDAO().getAll();
        for (Category cat: categories) {
            RadioButton rb = new RadioButton(this);
            rb.setText(cat.getCategoryName());
            rb.setId(cat.getCategoryId());
            rgAddCategory.addView(rb);
        }
        ivAddProductImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent,"Select product image"), PICK_IMAGE);
            }
        });
        btnAddProductConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String productName = etAddProductName.getText().toString();
                String productDescription = etAddProductDescription.getText().toString();
                String productPrice = etAddProductPrice.getText().toString();
                int category = rgAddCategory.getCheckedRadioButtonId();
                Log.d("checked cat", "" + category);
                Product product = new Product(productName,selectedImage.toString(),category, Integer.parseInt(productPrice),
                        productDescription, false, 4.5);
                db.productDAO().insert(product);
                startActivity(new Intent(AdminAddProductActivity.this, AdminViewProductActivity.class));
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == PICK_IMAGE){
            if(data != null) {
                selectedImage = data.getData();
                ivAddProductImage.setImageURI(selectedImage);
            }
        }
    }
}
