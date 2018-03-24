package fu.prm391.project.androidcommerce.activity.admin.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import fu.prm391.project.androidcommerce.R;

/**
 * Created by Khổng Cảnh on 3/24/2018.
 */

public class AdminCategoryViewHolder extends RecyclerView.ViewHolder {
    public ImageView ivCategory;
    public TextView tvCategoryName;
    public TextView tvCategoryProductNumber;
    public AdminCategoryViewHolder(View itemView ) {
        super(itemView);
        ivCategory = itemView.findViewById(R.id.ivAdminCategory);
        tvCategoryName = itemView.findViewById(R.id.tvAdminCategoryName);
        tvCategoryProductNumber = itemView.findViewById(R.id.tvCategoryProductNumber);
    }
}
