package fu.prm391.project.androidcommerce.utils;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import fu.prm391.project.androidcommerce.R;
import fu.prm391.project.androidcommerce.controller.listener.CustomCheckOutListener;

/**
 * Created by Khổng Cảnh on 3/6/2018.
 */

public class OrderItemViewHolder extends RecyclerView.ViewHolder {
    public ImageView ivProductCheckOut;
    public TextView tvProductNameCheckOut;
    public TextView tvProductDescriptionCheckOut;
    public TextView tvProductPriceCheckOut;
    public TextView tvOrderItemQuantityCheckOut;

    public Button btnRemoveItem;
    public Button btnEditItem;

    public CustomCheckOutListener cListener;

    public OrderItemViewHolder(View itemView, CustomCheckOutListener listener) {
        super(itemView);
        cListener = listener;
        ivProductCheckOut = itemView.findViewById(R.id.ivProductCheckOut);
        tvProductNameCheckOut = itemView.findViewById(R.id.tvProductNameCheckOut);
        tvProductDescriptionCheckOut = itemView.findViewById(R.id.tvDescriptionCheckOut);
        tvProductPriceCheckOut = itemView.findViewById(R.id.tvPriceCheckOut);
        tvOrderItemQuantityCheckOut = itemView.findViewById(R.id.tvQuantityCheckOut);
        btnRemoveItem = itemView.findViewById(R.id.btnRemoveCart);
        btnEditItem = itemView.findViewById(R.id.btnEditCart);
        btnRemoveItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cListener.onItemRemove(view, getAdapterPosition());
            }
        });
        btnEditItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cListener.onItemEdit(view, getAdapterPosition());
            }
        });
    }
}
