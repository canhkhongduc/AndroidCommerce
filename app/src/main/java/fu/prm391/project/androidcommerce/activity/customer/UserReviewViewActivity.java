package fu.prm391.project.androidcommerce.activity.customer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.List;

import fu.prm391.project.androidcommerce.R;
import fu.prm391.project.androidcommerce.activity.customer.adapter.ItemDecoration.VerticalSpacesItemDecoration;
import fu.prm391.project.androidcommerce.activity.customer.adapter.ReviewAdapter;
import fu.prm391.project.androidcommerce.database.AppDatabase;
import fu.prm391.project.androidcommerce.database.entity.Review;
import fu.prm391.project.androidcommerce.utils.SharedPreferenceUtil;

public class UserReviewViewActivity extends BaseCustomerActivity {

    private RecyclerView recyclerView;
    private AppDatabase db;
    private SharedPreferenceUtil util;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_review_view);

        recyclerView = findViewById(R.id.activityViewReview_recyclerView);
        db = AppDatabase.getAppDatabase(this);

        util = new SharedPreferenceUtil();

//        List<Review> reviewList = db.reviewDAO().getReviewsByUserId(util.getUser(this));

//        ReviewAdapter reviewAdapter = new ReviewAdapter(reviewList, db);
//        reviewAdapter.setGetUserReview(true);

//        recyclerView.setAdapter(reviewAdapter);

        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView.addItemDecoration(new VerticalSpacesItemDecoration(15));
    }
}
