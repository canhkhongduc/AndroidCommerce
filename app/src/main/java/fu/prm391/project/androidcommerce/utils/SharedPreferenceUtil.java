package fu.prm391.project.androidcommerce.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

import fu.prm391.project.androidcommerce.database.entity.OrderItem;
import fu.prm391.project.androidcommerce.database.entity.User;

/**
 * Created by Khổng Cảnh on 3/5/2018.
 */

public class SharedPreferenceUtil {
    public SharedPreferenceUtil() {
    }

    public void addToCart(Context context, ArrayList<OrderItem> list, String key) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(list);
        editor.putString(key + getUser(context), json);
        editor.apply();
    }

    public ArrayList<OrderItem> getCart(Context context, String key) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        Gson gson = new Gson();
        String json = preferences.getString(key + getUser(context), null);
        Type type = new TypeToken<ArrayList<OrderItem>>() {
        }.getType();
        return gson.fromJson(json, type);
    }

    public void removePreference(Context context, String key) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();
        editor.remove(key);
        editor.apply();
    }

    public void removeCart(Context context, String key) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();
        editor.remove(key + getUser(context));
        editor.apply();
    }

    public void destroyPreference(Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();
        editor.clear();
        editor.commit();
    }

    public String getUser(Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        String userId = preferences.getString("userId", null);
        return userId;
    }
}
