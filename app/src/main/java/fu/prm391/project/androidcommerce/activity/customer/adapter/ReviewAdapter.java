package fu.prm391.project.androidcommerce.activity.customer.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.List;

import fu.prm391.project.androidcommerce.R;
import fu.prm391.project.androidcommerce.database.AppDatabase;
import fu.prm391.project.androidcommerce.database.entity.Review;

/**
 * Created by Lam on 3/17/2018.
 */

public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.ViewHolder> {
    private static List<Review> reviewList;
    private AppDatabase db;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView txtUser;
        public TextView txtContent;
        public RatingBar ratingBar;

        public ViewHolder(View itemView) {
            super(itemView);

            txtUser = itemView.findViewById(R.id.reviewAdapter_txtUser);
            txtContent = itemView.findViewById(R.id.reviewAdapter_txtContent);
            ratingBar = itemView.findViewById(R.id.reviewAdapter_ratingBar);
        }
    }

    public ReviewAdapter(List<Review> reviewList, AppDatabase db) {
        this.reviewList = reviewList;
        this.db = db;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_review, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.txtUser.setText(db.userDAO().getUserByUserId(reviewList.get(position).getUserId()).getUsername());
        holder.ratingBar.setRating((reviewList.get(position).getRating()));
        holder.txtContent.setText(reviewList.get(position).getDescription());
    }

    @Override
    public int getItemCount() {
        return reviewList.size();
    }
}
