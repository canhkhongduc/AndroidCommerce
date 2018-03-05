package fu.prm391.project.androidcommerce.utils;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import fu.prm391.project.androidcommerce.R;
import fu.prm391.project.androidcommerce.controller.listener.CustomItemClickListener;
import fu.prm391.project.androidcommerce.database.AppDatabase;
import fu.prm391.project.androidcommerce.database.entity.Product;

/**
 * Created by Khổng Cảnh on 3/3/2018.
 */

public class ProductAdapter extends RecyclerView.Adapter<ProductViewHolder> {


    private List<Product> listProducts;
    private AppDatabase db;
    private Context context;
    private final CustomItemClickListener listener;


    public ProductAdapter(List<Product> listProducts, Context context, CustomItemClickListener listener){
        this.listProducts = listProducts;
        this.context = context;
        this.listener = listener;
    }
    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview, parent,false);
        final ProductViewHolder pvh = new ProductViewHolder(itemView);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onItemClick(view,pvh.getLayoutPosition());
            }
        });
        return pvh;
    }

    @Override
    public void onBindViewHolder(ProductViewHolder holder, int position) {
        db = AppDatabase.getAppDatabase(context);
        Product product = listProducts.get(position);
        holder.ivProduct.setImageResource(product.getProductImagePath());
        holder.tvProductName.setText(product.getProductName());
        holder.tvProductPrice.setText(product.getProductPrice() + "00 VND");
        holder.tvCategoryName.setText(db.categoryDAO().getCategoryByCategoryId(product.getCategoryId()).getCategoryName());
    }

    @Override
    public int getItemCount() {
        return listProducts.size();
    }


}
