package fu.prm391.project.androidcommerce.activity.admin;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import fu.prm391.project.androidcommerce.R;
import fu.prm391.project.androidcommerce.activity.admin.adapter.AdminUserAdapter;
import fu.prm391.project.androidcommerce.controller.listener.CustomCardViewListener;
import fu.prm391.project.androidcommerce.database.AppDatabase;
import fu.prm391.project.androidcommerce.database.entity.User;

public class AdminViewUserActivity extends BaseAdminActivity {
    private RecyclerView userListView;
    private List<User> users;
    private AppDatabase db;
    private AdminUserAdapter adapter;
    private Context context;
    private CustomCardViewListener listener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_view_user);
        context = this;
        userListView = findViewById(R.id.userListView);
        userListView.setHasFixedSize(true);
        db = AppDatabase.getAppDatabase(this);
        users = db.userDAO().getAll();
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayout.VERTICAL);
        userListView.setLayoutManager(llm);
        listener = new CustomCardViewListener() {
            @Override
            public void onItemClick(View view, int position) {
                final User user = users.get(position);
                new AlertDialog.Builder(context)
                        .setTitle("Delete User")
                        .setMessage("Do you really want to delete this user?")
                        .setIcon(android.R.drawable.ic_delete)
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                db.userDAO().delete(user);
                                users.remove(user);
                                Toast.makeText(context, "Deleted user successfully", Toast.LENGTH_LONG).show();
                                adapter.notifyDataSetChanged();
                            }})
                        .setNegativeButton(android.R.string.no, null).show();
            }
        };
        adapter = new AdminUserAdapter(users, AdminViewUserActivity.this, listener);
        adapter.notifyDataSetChanged();
        userListView.setAdapter(adapter);
    }
}
