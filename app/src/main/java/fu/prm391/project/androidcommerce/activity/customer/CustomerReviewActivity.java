package fu.prm391.project.androidcommerce.activity.customer;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import fu.prm391.project.androidcommerce.R;
import fu.prm391.project.androidcommerce.database.AppDatabase;
import fu.prm391.project.androidcommerce.database.entity.Review;
import fu.prm391.project.androidcommerce.utils.SharedPreferenceUtil;

public class CustomerReviewActivity extends BaseCustomerActivity {
    private TextView txtProductName;
    private Intent intent;
    private Button btnGiveReview;
    private EditText edtGiveReview;
    private RatingBar ratingBar;
    private AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_review);

        db = AppDatabase.getAppDatabase(this);

        txtProductName = findViewById(R.id.activityReview_txtProductName);
        btnGiveReview = findViewById(R.id.activityReview_btnGiveReview);
        edtGiveReview = findViewById(R.id.activityReview_edtGiveReview);
        ratingBar = findViewById(R.id.activityReview_ratingBar);

        intent = getIntent();
        txtProductName.setText("Product: " + intent.getStringExtra("productName"));

        btnGiveReview.setOnClickListener(new onBtnGiveReviewListener(this));
    }

    private class onBtnGiveReviewListener implements View.OnClickListener {
        private Context context;

        public onBtnGiveReviewListener(Context context) {
            this.context = context;
        }

        @Override
        public void onClick(View v) {
            float point = ratingBar.getRating();

            if (point == 0) {
                Toast.makeText(context, "Please give rating point", Toast.LENGTH_LONG).show();
            } else {
                String reviewDescription = edtGiveReview.getText().toString();

                SharedPreferenceUtil util = new SharedPreferenceUtil();

                Review review = new Review(util.getUser(context),
                        intent.getIntExtra("productId", -1), point, reviewDescription, false);

                db.reviewDAO().insert(review);
                finish();
            }
        }
    }
}
