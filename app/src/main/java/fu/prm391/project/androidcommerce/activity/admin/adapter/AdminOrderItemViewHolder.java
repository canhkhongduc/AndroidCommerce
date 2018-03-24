package fu.prm391.project.androidcommerce.activity.admin.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import fu.prm391.project.androidcommerce.R;
import fu.prm391.project.androidcommerce.controller.listener.CustomCardViewListener;
import fu.prm391.project.androidcommerce.controller.listener.CustomCheckOutListener;

/**
 * Created by Khổng Cảnh on 3/13/2018.
 */

public class AdminOrderItemViewHolder extends RecyclerView.ViewHolder {
    public ImageView ivProductViewOrder;
    public TextView tvProductNameViewOrder;
    public TextView tvProductDescriptionViewOrder;
    public TextView tvProductPriceViewOrder;
    public TextView tvOrderItemQuantityViewOrder;
    public AdminOrderItemViewHolder(View itemView ) {
        super(itemView);
        ivProductViewOrder = itemView.findViewById(R.id.ivProductViewOrder);
        tvProductNameViewOrder = itemView.findViewById(R.id.tvProductNameViewOrder);
        tvProductDescriptionViewOrder = itemView.findViewById(R.id.tvDescriptionViewOrder);
        tvProductPriceViewOrder = itemView.findViewById(R.id.tvPriceViewOrder);
        tvOrderItemQuantityViewOrder = itemView.findViewById(R.id.tvQuantityViewOrder);
    }
}
