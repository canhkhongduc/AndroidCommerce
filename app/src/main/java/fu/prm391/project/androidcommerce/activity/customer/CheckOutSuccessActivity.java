package fu.prm391.project.androidcommerce.activity.customer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import fu.prm391.project.androidcommerce.R;

public class CheckOutSuccessActivity extends BaseCustomerActivity {
    private Button btnCustomerViewOrderStatus, btnCustomerBackToShop;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_out_success);
        btnCustomerViewOrderStatus = findViewById(R.id.btnCustomerViewOrderStatus);
        btnCustomerBackToShop = findViewById(R.id.btnCustomerBackToShop);
        btnCustomerViewOrderStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CheckOutSuccessActivity.this, CustomerViewOrderActivity.class));
            }
        });
        btnCustomerBackToShop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CheckOutSuccessActivity.this, CustomerMenuActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        });
    }
}
