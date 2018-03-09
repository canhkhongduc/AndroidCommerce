package fu.prm391.project.androidcommerce.utils;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import fu.prm391.project.androidcommerce.R;
import fu.prm391.project.androidcommerce.controller.listener.CustomCheckOutListener;
import fu.prm391.project.androidcommerce.database.AppDatabase;
import fu.prm391.project.androidcommerce.database.entity.OrderItem;
import fu.prm391.project.androidcommerce.database.entity.Product;

/**
 * Created by Khổng Cảnh on 3/6/2018.
 */

public class OrderItemAdapter extends RecyclerView.Adapter<OrderItemViewHolder> {
    private List<OrderItem> listOrderItems;
    private AppDatabase db;
    private Context context;
    private CustomCheckOutListener cListener;

    public OrderItemAdapter(List<OrderItem> listOrderItems, Context context, CustomCheckOutListener cListener) {
        this.listOrderItems = listOrderItems;
        this.context = context;
        this.cListener = cListener;
    }

    @Override
    public OrderItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.check_out_cardview, parent,false);
        return new OrderItemViewHolder(itemView,cListener);
    }

    @Override
    public void onBindViewHolder(OrderItemViewHolder holder, int position) {
        db = AppDatabase.getAppDatabase(context);
        OrderItem orderItem = listOrderItems.get(position);
        Product product = db.productDAO().getProductByProductId(orderItem.getProductId());
        holder.ivProductCheckOut.setImageResource(product.getProductImagePath());
        holder.tvProductNameCheckOut.setText(product.getProductName());
        holder.tvProductPriceCheckOut.setText(product.getProductPrice() + "00 VND");
        holder.tvProductDescriptionCheckOut.setText(product.getProductDescription());
        holder.tvOrderItemQuantityCheckOut.setText("" + orderItem.getQuantity());
    }

    @Override
    public int getItemCount() {
        return listOrderItems.size();
    }
}
