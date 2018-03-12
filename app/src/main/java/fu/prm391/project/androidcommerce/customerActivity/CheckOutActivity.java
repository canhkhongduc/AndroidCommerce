package fu.prm391.project.androidcommerce.customerActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.design.widget.BottomSheetDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import fu.prm391.project.androidcommerce.R;
import fu.prm391.project.androidcommerce.controller.listener.CustomCheckOutListener;
import fu.prm391.project.androidcommerce.database.AppDatabase;
import fu.prm391.project.androidcommerce.database.entity.Order;
import fu.prm391.project.androidcommerce.database.entity.OrderItem;
import fu.prm391.project.androidcommerce.database.entity.PaymentType;
import fu.prm391.project.androidcommerce.database.entity.Product;
import fu.prm391.project.androidcommerce.database.entity.User;
import fu.prm391.project.androidcommerce.utils.ArrayListUtil;
import fu.prm391.project.androidcommerce.utils.OrderItemAdapter;
import fu.prm391.project.androidcommerce.utils.SharedPreferenceUtil;

public class CheckOutActivity extends AppCompatActivity {
    private RecyclerView checkoutCardView;
    private List<OrderItem> orderItems;
    private AppDatabase db;
    private OrderItemAdapter adapter;
    private TextView tvProductNameEditCart;
    private TextView tvProductPriceEditCart;
    private TextView tvQuantityEditCart;
    private ImageView ivAddQuantity, ivMinusQuantity, ivDone;
    private Button btnCheckOut;
    private Button totalPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_out);
        btnCheckOut = findViewById(R.id.btnCheckOut);
        totalPrice = findViewById(R.id.totalPrice);

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
        final ArrayListUtil listUtil = new ArrayListUtil();
        double total = listUtil.getTotalFromList(this, orderItems);
        totalPrice.setText(total + "00 VND");

        SharedPreferences preference = PreferenceManager.getDefaultSharedPreferences(this);
        final int userId = preference.getInt("userId", -1);
        final User user = db.userDAO().getUserByUserId(userId);
        PaymentType p1 = new PaymentType("cash");
        db.paymentTypeDAO().insert(p1);
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
                        totalPrice.setText(listUtil.getTotalFromList(CheckOutActivity.this, orderItems) + "00 VND");
                        adapter.notifyDataSetChanged();
                    }
                });

            }
            public void onItemRemove(View view, int position){
                orderItems.remove(position);
                totalPrice.setText(listUtil.getTotalFromList(CheckOutActivity.this, orderItems) + "00 VND");
                adapter.notifyDataSetChanged();
            }
        };
        adapter = new OrderItemAdapter(orderItems, CheckOutActivity.this, listener);
        checkoutCardView.setAdapter(adapter);
        btnCheckOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Date currentTime = Calendar.getInstance().getTime();
                Order order = new Order(userId, 1, listUtil.getTotalFromList(CheckOutActivity.this, orderItems),
                        currentTime, null, null, true);
                db.orderDAO().insert(order);
                Order order1 = db.orderDAO().getLastInsertedOrder(userId);
                for (OrderItem orderItem: orderItems) {
                    orderItem.setOrderId(order1.getOrderId());
                    db.orderItemDAO().insert(orderItem);
                }
                Toast.makeText(CheckOutActivity.this, "Ordered Successfully", Toast.LENGTH_LONG).show();
                startActivity(new Intent(CheckOutActivity.this, CustomerHomeActivity.class));
            }
        });

    }
}
