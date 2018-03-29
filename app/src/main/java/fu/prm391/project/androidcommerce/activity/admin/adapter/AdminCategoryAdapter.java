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
import fu.prm391.project.androidcommerce.controller.listener.CustomCardViewListener;
import fu.prm391.project.androidcommerce.database.AppDatabase;
import fu.prm391.project.androidcommerce.database.entity.Category;
import fu.prm391.project.androidcommerce.database.entity.OrderItem;
import fu.prm391.project.androidcommerce.database.entity.Product;

/**
 * Created by Khổng Cảnh on 3/24/2018.
 */

public class AdminCategoryAdapter extends RecyclerView.Adapter<AdminCategoryViewHolder> {
    private List<Category> categories;
    private List<Product> products;
    private AppDatabase db;
    private Context context;
    CustomCardViewListener listener;

    public AdminCategoryAdapter(List<Category> categories, Context context, CustomCardViewListener listener) {
        this.categories = categories;
        this.context = context;
        this.listener = listener;
    }
    @Override
    public AdminCategoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.admin_category_cardview, parent,false);
        final AdminCategoryViewHolder pvh = new AdminCategoryViewHolder(itemView);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onItemClick(view,pvh.getLayoutPosition());
            }
        });
        return pvh;
    }

    @Override
    public void onBindViewHolder(AdminCategoryViewHolder holder, int position) {
        db = AppDatabase.getAppDatabase(context);
        Category category = categories.get(position);
        products = db.productDAO().getProductsByCategoryId(category.getCategoryId());
        holder.ivCategory.setImageURI(Uri.parse(category.getCategoryImagePath()));
        holder.tvCategoryName.setText(category.getCategoryName());
        holder.tvCategoryProductNumber.setText(products.size() + " products");
    }

    @Override
    public int getItemCount() {
        return categories.size();
    }
}
