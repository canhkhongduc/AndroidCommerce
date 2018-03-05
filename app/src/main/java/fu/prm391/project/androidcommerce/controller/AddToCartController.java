package fu.prm391.project.androidcommerce.controller;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

import java.lang.reflect.Array;
import java.util.List;

import fu.prm391.project.androidcommerce.database.entity.OrderItem;

/**
 * Created by Khổng Cảnh on 3/5/2018.
 */

public class AddToCartController {
    public static final String PREFERENCE_NAME = "PRODUCT";
    public static final String ORDER_ITEM = "ORDER_ITEM";
    public AddToCartController() {
        super();
    }
    public void addToCart(Context context, OrderItem orderItem){
        List<OrderItem> orderItems = getCart(context);
    }

    private List<OrderItem> getCart(Context context) {
        SharedPreferences preferences = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
        List<OrderItem> orderItems;
        if(preferences.contains(ORDER_ITEM)){
            String jsonOrderItem = preferences.getString(ORDER_ITEM, null);
            Gson gson = new Gson();

        }
        return null;
    }
}
