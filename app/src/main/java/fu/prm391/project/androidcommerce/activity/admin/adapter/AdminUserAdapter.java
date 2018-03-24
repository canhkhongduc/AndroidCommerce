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
import fu.prm391.project.androidcommerce.database.entity.User;
import fu.prm391.project.androidcommerce.utils.ArrayListUtil;

/**
 * Created by Khổng Cảnh on 3/24/2018.
 */

public class AdminUserAdapter extends RecyclerView.Adapter<AdminUserViewHolder> {
    private List<User> users;
    private AppDatabase db;
    private Context context;
    private CustomCardViewListener cListener;

    public AdminUserAdapter(List<User> users, Context context, CustomCardViewListener cListener) {
        this.users = users;
        this.context = context;
        this.cListener = cListener;
    }

    @Override
    public AdminUserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.admin_user_cardview, parent,false);
        return new AdminUserViewHolder(itemView,cListener);
    }

    @Override
    public void onBindViewHolder(AdminUserViewHolder holder, int position) {
        User user = users.get(position);
        if(user.getUserImagePath() != null && user.getUserImagePath()!= "")
            holder.ivAdminUserProfile.setImageURI(Uri.parse(user.getUserImagePath()));
        holder.tvAdminUserId.setText(""+ user.getUserId());
        holder.tvAdminUsername.setText(user.getUsername());
        holder.tvAdminUserEmail.setText(user.getEmail());
        holder.tvAdminUserAddress.setText(user.getAddress());
        holder.tvAdminUserPhone.setText(user.getPhone());
    }

    @Override
    public int getItemCount() {
        return users.size();
    }
}
