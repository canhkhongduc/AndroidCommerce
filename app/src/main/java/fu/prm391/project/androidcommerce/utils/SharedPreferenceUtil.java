package fu.prm391.project.androidcommerce.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Array;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import fu.prm391.project.androidcommerce.database.entity.OrderItem;
import fu.prm391.project.androidcommerce.database.entity.User;

/**
 * Created by Khổng Cảnh on 3/5/2018.
 */

public class SharedPreferenceUtil {
    public SharedPreferenceUtil() {
    }
    public void addToCart(Context context, ArrayList<OrderItem> list, String key){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(list);
        editor.putString(key, json);
        editor.apply();
    }

    public ArrayList<OrderItem> getCart(Context context, String key) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        Gson gson = new Gson();
        String json =  preferences.getString(key, null);
        Type type = new TypeToken<ArrayList<OrderItem>>(){}.getType();
        return gson.fromJson(json, type);
    }
    public void removePreference(Context context, String key){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();
        editor.remove(key);
        editor.apply();
    }
    public void destroyPreference(Context context){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();
        editor.clear();
        editor.commit();
    }
    public int getUser(Context context){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        int userId =  preferences.getInt("userId", 0);
        return userId;
    }
}
