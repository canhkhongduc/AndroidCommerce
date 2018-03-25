package fu.prm391.project.androidcommerce.activity.customer.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.List;

import fu.prm391.project.androidcommerce.R;
import fu.prm391.project.androidcommerce.activity.customer.ProductListActivity;
import fu.prm391.project.androidcommerce.database.entity.Category;

/**
 * Created by Lam on 3/21/2018.
 */

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {
    private static List<Category> categoryList;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView txtCategory;

        public ViewHolder(View itemView) {
            super(itemView);

            txtCategory = itemView.findViewById(R.id.txtCategory);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Context context = v.getContext();
                    int itemPosition = getAdapterPosition();
                    Category category = categoryList.get(itemPosition);

                    Intent intent = new Intent(context, ProductListActivity.class);
                    intent.putExtra("categoryId", category.getCategoryId());
                    intent.putExtra("categoryName", category.getCategoryName());
                    context.startActivity(intent);
                }
            });
        }
    }

    public CategoryAdapter(List<Category> CategoryList) {
        this.categoryList = CategoryList;
    }

    @Override
    public CategoryAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_category, parent, false);
        CategoryAdapter.ViewHolder vh = new CategoryAdapter.ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(CategoryAdapter.ViewHolder holder, int position) {
        holder.txtCategory.setText(categoryList.get(position).getCategoryName());
    }

    @Override
    public int getItemCount() {
        return categoryList.size();
    }
}
