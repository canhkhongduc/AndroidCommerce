package fu.prm391.project.androidcommerce.activity.customer.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.List;

import fu.prm391.project.androidcommerce.R;
import fu.prm391.project.androidcommerce.activity.customer.CustomerHomeActivity;
import fu.prm391.project.androidcommerce.activity.customer.ProductDetailActivity;
import fu.prm391.project.androidcommerce.database.entity.Product;

/**
 * Created by Lam on 3/17/2018.
 */

public class PopularProductListAdapter extends RecyclerView.Adapter<PopularProductListAdapter.ViewHolder> {
    private static List<Product> productList;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView txtProductName;
        public TextView txtProductPrice;
        public ImageView imgProduct;

        public ViewHolder(View itemView) {
            super(itemView);

            txtProductName = itemView.findViewById(R.id.txtProductName);
            txtProductPrice = itemView.findViewById(R.id.txtProductPrice);
            imgProduct = itemView.findViewById(R.id.imgProduct);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Context context = v.getContext();

                    Intent intent = new Intent(context, ProductDetailActivity.class);
                    int itemPosition = getAdapterPosition();
                    Product product = productList.get(itemPosition);
                    intent.putExtra("productId", product.getProductId());
                    context.startActivity(intent);
                }
            });
        }
    }

    public PopularProductListAdapter(List<Product> productList) {
        this.productList = productList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.popular_products_adapter, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.txtProductName.setText(productList.get(position).getProductName());
        holder.txtProductPrice.setText(new DecimalFormat("#,###.##").format(productList.get(position).getProductPrice()) + "đ");
        holder.imgProduct.setImageURI(Uri.parse(productList.get(position).getProductImagePath()));

        Log.d("test", productList.get(position).getProductImagePath());
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }
}
