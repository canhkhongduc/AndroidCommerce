package fu.prm391.project.androidcommerce.activity.admin.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import fu.prm391.project.androidcommerce.R;
import fu.prm391.project.androidcommerce.controller.listener.CustomCardViewListener;
import fu.prm391.project.androidcommerce.database.AppDatabase;
import fu.prm391.project.androidcommerce.database.entity.Product;
import fu.prm391.project.androidcommerce.utils.ArrayListUtil;

/**
 * Created by Khổng Cảnh on 3/14/2018.
 */

public class AdminProductAdapter extends RecyclerView.Adapter<AdminProductViewHolder> {
    private List<Product> listProducts;
    private AppDatabase db;
    private Context context;
    private CustomCardViewListener cListener;

    public AdminProductAdapter(List<Product> listProducts, Context context, CustomCardViewListener listener) {
        this.listProducts = listProducts;
        this.context = context;
        this.cListener = listener;
    }

    @Override
    public AdminProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.admin_product_cardview, parent,false);
        return new AdminProductViewHolder(itemView,cListener);
    }

    @Override
    public void onBindViewHolder(AdminProductViewHolder holder, int position) {
        ArrayListUtil util = new ArrayListUtil();
        db = AppDatabase.getAppDatabase(context);
        Product product = listProducts.get(position);
        holder.ivAdminViewProduct.setImageURI(Uri.parse(product.getProductImagePath()));
        holder.tvAdminViewProductName.setText(product.getProductName());
        holder.tvAdminViewProductPrice.setText("" + product.getProductPrice());
        holder.tvAdminViewProductDescription.setText(product.getProductDescription());
    }

    @Override
    public int getItemCount() {
        return listProducts.size();
    }
}
