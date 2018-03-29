package fu.prm391.project.androidcommerce.activity.admin;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.LinearLayout;

import java.util.List;

import fu.prm391.project.androidcommerce.R;
import fu.prm391.project.androidcommerce.activity.customer.BaseCustomerActivity;
import fu.prm391.project.androidcommerce.activity.customer.adapter.ReviewAdapter;
import fu.prm391.project.androidcommerce.database.AppDatabase;
import fu.prm391.project.androidcommerce.database.entity.Review;

public class AdminViewReviewActivity extends BaseAdminActivity {
    private RecyclerView reviewCardView;
    private List<Review> reviews;
    private AppDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_view_review);
        reviewCardView = findViewById(R.id.reviewCardView);
        reviewCardView.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayout.VERTICAL);
        reviewCardView.setLayoutManager(llm);
        db = AppDatabase.getAppDatabase(this);
        reviews = db.reviewDAO().getAll();
        ReviewAdapter adapter = new ReviewAdapter(reviews, db);
        adapter.setGetUserReview(true);
        reviewCardView.setAdapter(adapter);
    }
}
