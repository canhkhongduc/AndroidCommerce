package fu.prm391.project.androidcommerce.activity.customer;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import fu.prm391.project.androidcommerce.R;
import fu.prm391.project.androidcommerce.database.AppDatabase;
import fu.prm391.project.androidcommerce.database.entity.Order;
import fu.prm391.project.androidcommerce.database.entity.User;
import fu.prm391.project.androidcommerce.utils.SharedPreferenceUtil;

public class CustomerViewProfileActivity extends BaseCustomerActivity {
    private AppDatabase db;
    private SharedPreferenceUtil util;
    private ImageView ivProfileAvatar;
    private TextView tvProfileId, tvProfileEmail, tvProfileAddress, tvProfilePhone, tvOrderNumber;
    private Button btnBackToShop;
    private final int PICK_IMAGE = 200;
    private Uri selectedImage;
    private TextView txtUsername;
    private TextView txtReview;
    User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_view_profile);
        db = AppDatabase.getAppDatabase(this);
        util = new SharedPreferenceUtil();
//        int userId = util.getUser(this);
//        user = db.userDAO().getUserByUserId(userId);
//        List<Order> orders = db.orderDAO().getOrdersByUserId(userId);
        tvOrderNumber = findViewById(R.id.tvProfileOrderNumber);
//        tvOrderNumber.setText("" + orders.size());
        ivProfileAvatar = findViewById(R.id.ivCustomerAvatar);
        tvProfileId = findViewById(R.id.tvProfileId);
        tvProfileEmail = findViewById(R.id.tvProfileEmail);
        tvProfileAddress = findViewById(R.id.tvProfileAddress);
        tvProfilePhone = findViewById(R.id.tvProfilePhone);
        txtUsername = findViewById(R.id.activityCustomerProfile_username);
        txtReview = findViewById(R.id.tvProfileReviewNumber);

        if (user.getUserImagePath() != "" && user.getUserImagePath() != null) {
          ivProfileAvatar.setImageURI(Uri.parse(user.getUserImagePath()));
        }
//        tvProfileId.setText(""+ userId);
        tvProfileEmail.setText(user.getEmail());
        tvProfilePhone.setText(user.getPhone());
        tvProfileAddress.setText(user.getAddress());
        btnBackToShop = findViewById(R.id.btnBackToShop);
//        txtUsername.setText(db.userDAO().getUserByUserId(util.getUser(this)).getUsername());
//        txtReview.setText(String.valueOf(db.reviewDAO().countReviewsByUserId(util.getUser(this))));

        ivProfileAvatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_OPEN_DOCUMENT);
                intent.addCategory(Intent.CATEGORY_OPENABLE);
                startActivityForResult(Intent.createChooser(intent,"Select your profile picture"), PICK_IMAGE);
            }
        });
        btnBackToShop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CustomerViewProfileActivity.this, CustomerMenuActivity.class));
            }
        });
        tvOrderNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CustomerViewProfileActivity.this, CustomerViewOrderActivity.class));
            }
        });
        txtReview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CustomerViewProfileActivity.this, UserReviewViewActivity.class));
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == PICK_IMAGE){
            if(data != null) {
                selectedImage = data.getData();
                ivProfileAvatar.setImageURI(selectedImage);
                db.userDAO().updateUserImagePath(user.getUserId(), selectedImage.toString());
            }
        }
    }
}
