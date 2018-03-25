package fu.prm391.project.androidcommerce.activity.customer.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import fu.prm391.project.androidcommerce.R;
import fu.prm391.project.androidcommerce.controller.listener.CustomCardViewListener;
import fu.prm391.project.androidcommerce.database.AppDatabase;
import fu.prm391.project.androidcommerce.database.entity.Order;
import fu.prm391.project.androidcommerce.database.entity.OrderItem;
import fu.prm391.project.androidcommerce.utils.ArrayListUtil;

/**
 * Created by Khổng Cảnh on 3/11/2018.
 */

public class OrderAdapter extends RecyclerView.Adapter<OrderViewHolder> {
    private List<Order> listOrders;
    private AppDatabase db;
    private Context context;
    private CustomCardViewListener cListener;

    public OrderAdapter(List<Order> listOrders, Context context, CustomCardViewListener cListener) {
        this.listOrders = listOrders;
        this.context = context;
        this.cListener = cListener;
    }

    @Override
    public OrderViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.admin_order_cardview, parent,false);
        return new OrderViewHolder(itemView,cListener);
    }

    @Override
    public void onBindViewHolder(OrderViewHolder holder, int position) {
        ArrayListUtil util = new ArrayListUtil();
        db = AppDatabase.getAppDatabase(context);
        Order order = listOrders.get(position);
        List<OrderItem> orderItems = db.orderItemDAO().getOrderItemByOrderId(order.getOrderId());
        holder.tvOrderId.setText(""+ order.getOrderId());
        holder.tvProductNumber.setText(""+orderItems.size());
        holder.tvOrderPrice.setText(util.getTotalFromList(context, orderItems) + "00 VND");
        holder.tvOrderStatus.setText(order.isCompleted() ? "Completed" : "Pending");
        holder.tvOrderDate.setText("" + order.getOrderDate());
    }

    @Override
    public int getItemCount() {
        return listOrders.size();
    }
}
