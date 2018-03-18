package fu.prm391.project.androidcommerce.utils.admin;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import fu.prm391.project.androidcommerce.R;
import fu.prm391.project.androidcommerce.controller.listener.CustomCardViewListener;

/**
 * Created by Khổng Cảnh on 3/14/2018.
 */

public class AdminProductViewHolder extends RecyclerView.ViewHolder {
    public ImageView ivAdminViewProduct;
    public TextView tvAdminViewProductName;
    public TextView tvAdminViewProductDescription;
    public TextView tvAdminViewProductPrice;
    public Button btnEditProduct;
    private CustomCardViewListener cListener;

    public AdminProductViewHolder(View itemView, final CustomCardViewListener listener) {
        super(itemView);
        cListener = listener;
        ivAdminViewProduct = itemView.findViewById(R.id.ivAdminProduct);
        tvAdminViewProductName = itemView.findViewById(R.id.tvAdminProductName);
        tvAdminViewProductPrice = itemView.findViewById(R.id.tvAdminProductPrice);
        tvAdminViewProductDescription = itemView.findViewById(R.id.tvAdminProductDescription);
        btnEditProduct = itemView.findViewById(R.id.btnEditProduct);
        btnEditProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cListener.onItemClick(view,getAdapterPosition());
            }
        });
    }
}
