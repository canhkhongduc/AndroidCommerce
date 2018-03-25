package fu.prm391.project.androidcommerce.activity.admin.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import fu.prm391.project.androidcommerce.R;
import fu.prm391.project.androidcommerce.controller.listener.CustomCardViewListener;

/**
 * Created by Khổng Cảnh on 3/24/2018.
 */

public class AdminUserViewHolder extends RecyclerView.ViewHolder {
    public ImageView ivAdminUserProfile;
    public TextView tvAdminUserId;
    public TextView tvAdminUsername;
    public TextView tvAdminUserEmail;
    public TextView tvAdminUserPhone;
    public TextView tvAdminUserAddress;
    public ImageButton ibDeleteUser;
    private CustomCardViewListener cListener;


    public AdminUserViewHolder(final View itemView, final CustomCardViewListener listener) {
        super(itemView);
        cListener = listener;
        ivAdminUserProfile = itemView.findViewById(R.id.ivAdminUserProfile);
        tvAdminUserId = itemView.findViewById(R.id.tvAdminUserId);
        tvAdminUsername = itemView.findViewById(R.id.tvAdminUserName);
        tvAdminUserPhone = itemView.findViewById(R.id.tvAdminPhone);
        tvAdminUserAddress = itemView.findViewById(R.id.tvAdminUserAddress);
        tvAdminUserEmail = itemView.findViewById(R.id.tvAdminUserEmail);
        ibDeleteUser = itemView.findViewById(R.id.ibDeleteUser);
        ibDeleteUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cListener.onItemClick(view, getAdapterPosition());
            }
        });
    }
}
