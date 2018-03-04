package fu.prm391.project.androidcommerce.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import fu.prm391.project.androidcommerce.R;
import fu.prm391.project.androidcommerce.database.entity.Product;

/**
 * Created by Khổng Cảnh on 3/3/2018.
 */

public class ProductViewHolder extends RecyclerView.ViewHolder {
    public ImageView ivProduct;
    public TextView tvProductName;
    public TextView tvProductPrice;
    public TextView tvCategoryName;

    public ProductViewHolder(View itemView) {
        super(itemView);
        ivProduct = itemView.findViewById(R.id.ivProduct);
        tvProductName = itemView.findViewById(R.id.tvProductName);
        tvProductPrice = itemView.findViewById(R.id.tvProductPrice);
        tvCategoryName = itemView.findViewById(R.id.tvCategoryName);
    }
}
