package fu.prm391.project.androidcommerce.customerActivity;

import android.support.design.widget.BottomSheetDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import fu.prm391.project.androidcommerce.R;
import fu.prm391.project.androidcommerce.controller.listener.CustomCheckOutListener;
import fu.prm391.project.androidcommerce.database.AppDatabase;
import fu.prm391.project.androidcommerce.database.entity.OrderItem;
import fu.prm391.project.androidcommerce.database.entity.Product;
import fu.prm391.project.androidcommerce.utils.OrderItemAdapter;
import fu.prm391.project.androidcommerce.utils.SharedPreferenceUtil;

public class CheckOutActivity extends AppCompatActivity {
    private RecyclerView checkoutCardView;
    private List<OrderItem> orderItems;
    private AppDatabase db;
    OrderItemAdapter adapter;
    private TextView tvProductNameEditCart;
    private TextView tvProductPriceEditCart;
    private TextView tvQuantityEditCart;
    private ImageView ivAddQuantity, ivMinusQuantity, ivDone;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_out);
        final BottomSheetDialog editDialog = new BottomSheetDialog(this);
        final View bottomSheetView = getLayoutInflater().inflate(R.layout.bottom_sheet, null);
        editDialog.setContentView(bottomSheetView);
        tvProductNameEditCart = bottomSheetView.findViewById(R.id.tvProductNameEditCart);
        tvProductPriceEditCart = bottomSheetView.findViewById(R.id.tvPriceEditCart);
        tvQuantityEditCart = bottomSheetView.findViewById(R.id.tvQuantityEditCart);
        ivAddQuantity = bottomSheetView.findViewById(R.id.ivAddQuantity);
        ivMinusQuantity = bottomSheetView.findViewById(R.id.ivMinusQuantity);
        ivDone = bottomSheetView.findViewById(R.id.ivDone);

        checkoutCardView = findViewById(R.id.checkOutItemList);
        checkoutCardView.setHasFixedSize(true);
        db = AppDatabase.getAppDatabase(this);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayout.VERTICAL);
        checkoutCardView.setLayoutManager(llm);

        SharedPreferenceUtil util = new SharedPreferenceUtil();
        orderItems = util.getCart(this,"cartItem");
        CustomCheckOutListener listener = new CustomCheckOutListener() {
            @Override
            public void onItemEdit(View view, final int position) {
                final OrderItem orderItem = orderItems.get(position);
                Product product = db.productDAO().getProductByProductId(orderItem.getProductId());
                tvProductNameEditCart.setText(product.getProductName());
                tvProductPriceEditCart.setText("" + product.getProductPrice());
                tvQuantityEditCart.setText("" + orderItem.getQuantity());
                ivAddQuantity.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        tvQuantityEditCart.setText("" + (Integer.parseInt(tvQuantityEditCart.getText().toString())+1));
                    }
                });
                ivMinusQuantity.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (Integer.parseInt(tvQuantityEditCart.getText().toString()) > 0) {
                            tvQuantityEditCart.setText("" + (Integer.parseInt(tvQuantityEditCart.getText().toString()) - 1));
                        }
                    }
                });
                editDialog.show();

                ivDone.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        orderItem.setQuantity(Integer.parseInt(tvQuantityEditCart.getText().toString()));
                        orderItems.remove(position);
                        if (Integer.parseInt(tvQuantityEditCart.getText().toString()) != 0) {
                            orderItems.add(position, orderItem);
                        }
                        editDialog.dismiss();
                        adapter.notifyDataSetChanged();
                    }
                });

            }
            public void onItemRemove(View view, int position){
                orderItems.remove(position);
                adapter.notifyDataSetChanged();
            }
        };
        adapter = new OrderItemAdapter(orderItems, CheckOutActivity.this, listener);
        checkoutCardView.setAdapter(adapter);

    }
}
