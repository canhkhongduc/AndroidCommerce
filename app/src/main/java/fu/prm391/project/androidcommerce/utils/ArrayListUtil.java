package fu.prm391.project.androidcommerce.utils;

import android.content.Context;

import java.util.List;

import fu.prm391.project.androidcommerce.database.AppDatabase;
import fu.prm391.project.androidcommerce.database.entity.OrderItem;
import fu.prm391.project.androidcommerce.database.entity.Product;

/**
 * Created by Khổng Cảnh on 3/9/2018.
 */

public class ArrayListUtil {
    private AppDatabase db;
    public double getTotalFromList(Context context, List<OrderItem> orderItems){
        double total = 0;
        for (OrderItem orderItem: orderItems) {
            db = AppDatabase.getAppDatabase(context);
            Product product = db.productDAO().getProductByProductId(orderItem.getProductId());
            total += product.getProductPrice() * orderItem.getQuantity();
        }
        return total;
    }
}
