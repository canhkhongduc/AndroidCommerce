package fu.prm391.project.androidcommerce.activity.customer.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import fu.prm391.project.androidcommerce.R;
import fu.prm391.project.androidcommerce.controller.listener.CustomCardViewListener;

/**
 * Created by Khổng Cảnh on 3/11/2018.
 */

public class OrderViewHolder extends RecyclerView.ViewHolder{
    public TextView tvOrderId;
    public TextView tvProductNumber;
    public TextView tvOrderPrice;
    public TextView tvOrderStatus;
    public TextView tvOrderDate;
    public Button btnViewOrder;

    private CustomCardViewListener clistener;

    public OrderViewHolder(View itemView, final CustomCardViewListener listener) {
        super(itemView);
        clistener = listener;
        tvOrderId = itemView.findViewById(R.id.tvAdminOrderId);
        tvProductNumber = itemView.findViewById(R.id.tvProductNumber);
        tvOrderPrice = itemView.findViewById(R.id.tvOrderPrice);
        tvOrderStatus = itemView.findViewById(R.id.tvOrderStatus);
        tvOrderDate = itemView.findViewById(R.id.tvOrderDate);
        btnViewOrder = itemView.findViewById(R.id.btnViewOrder);
        btnViewOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clistener.onItemClick(view,getAdapterPosition());
            }
        });
    }
}
