package fu.prm391.project.androidcommerce.activity.admin.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import fu.prm391.project.androidcommerce.R;
import fu.prm391.project.androidcommerce.database.AppDatabase;
import fu.prm391.project.androidcommerce.database.entity.OrderItem;
import fu.prm391.project.androidcommerce.database.entity.Product;

/**
 * Created by Khổng Cảnh on 3/13/2018.
 */

public class AdminOrderAdapter extends RecyclerView.Adapter<AdminOrderItemViewHolder> {
    private List<OrderItem> listOrderItems;
    private AppDatabase db;
    private Context context;

    public AdminOrderAdapter(List<OrderItem> listOrderItems, Context context) {
        this.listOrderItems = listOrderItems;
        this.context = context;
    }

    @Override
    public AdminOrderItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.order_cardview, parent,false);
        return new AdminOrderItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(AdminOrderItemViewHolder holder, int position) {
        db = AppDatabase.getAppDatabase(context);
        OrderItem orderItem = listOrderItems.get(position);
        Product product = db.productDAO().getProductByProductId(orderItem.getProductId());
        Log.d("productId", orderItem.getOrderId() + "");
        holder.ivProductViewOrder.setImageURI(Uri.parse(product.getProductImagePath()));
        holder.tvProductNameViewOrder.setText(product.getProductName());
        holder.tvProductPriceViewOrder.setText(product.getProductPrice() + "00 VND");
        holder.tvProductDescriptionViewOrder.setText(product.getProductDescription());
        holder.tvOrderItemQuantityViewOrder.setText("" + orderItem.getQuantity());
    }

    @Override
    public int getItemCount() {
        return listOrderItems.size();
    }
}
