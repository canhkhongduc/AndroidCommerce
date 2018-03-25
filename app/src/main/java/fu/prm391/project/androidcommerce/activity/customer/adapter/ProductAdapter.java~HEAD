package fu.prm391.project.androidcommerce.activity.customer.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.List;

import fu.prm391.project.androidcommerce.R;
import fu.prm391.project.androidcommerce.activity.customer.ProductDetailActivity;
import fu.prm391.project.androidcommerce.database.entity.Product;

/**
 * Created by Lam on 3/17/2018.
 */

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {
    private static List<Product> productList;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView txtProductName;
        public TextView txtProductPrice;
        public ImageView imgProduct;
        public RatingBar ratingBar;

        public ViewHolder(View itemView) {
            super(itemView);

            txtProductName = itemView.findViewById(R.id.productAdapter_txtProductName);
            txtProductPrice = itemView.findViewById(R.id.productAdapter_txtProductPrice);
            imgProduct = itemView.findViewById(R.id.productAdapter_imgProduct);
            ratingBar = itemView.findViewById(R.id.productAdapter_ratingBarProduct);

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

    public ProductAdapter(List<Product> productList) {
        this.productList = productList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_product, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.txtProductName.setText(productList.get(position).getProductName());
        holder.txtProductPrice.setText(new DecimalFormat("#,###.##").format(productList.get(position).getProductPrice()) + "đ");
        holder.imgProduct.setImageURI(Uri.parse(productList.get(position).getProductImagePath()));
        holder.ratingBar.setRating((float)(productList.get(position).getAverageRating()));
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }
}
