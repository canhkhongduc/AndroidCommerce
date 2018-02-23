package fu.prm391.project.androidcommerce.dao;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import fu.prm391.project.androidcommerce.model.User;

/**
 * Created by Khổng Cảnh on 2/22/2018.
 */

public class MyHelper extends ContentProvider {
    DBHelper helper;
    SQLiteDatabase db;

    public MyHelper(Context context) {
        helper = new DBHelper(context, "androidcommerce.db", null, 1);
    }
    public void insertUser(User user){
        ContentValues values = new ContentValues();
        values.put("username", user.getUserName());
        values.put("password", user.getUserPassword());
        values.put("email", user.getUserEmail());
        values.put("phone", user.getUserEmail());
        values.put("address", user.getUserEmail());
        values.put("userType", 1);
        values.put("isDeleted", false);
        insert(null, values);
    }
    public User getUserByUsername(String username){
        User user = new User();
        Cursor c = query(Uri.parse("User"),null,"username = ?", new String[]{username}, null);
        if (c.moveToFirst() && c.getCount() > 0) {
            user.setUserName(username);
            user.setUserId(c.getInt(c.getColumnIndex("userId")));
            user.setUserPassword(c.getString(c.getColumnIndex("password")));
            user.setUserEmail(c.getString(c.getColumnIndex("email")));
            user.setUserPhone(c.getString(c.getColumnIndex("phone")));
            user.setUserAddress(c.getString(c.getColumnIndex("address")));
            user.setDeleted(c.getInt(c.getColumnIndex("isDeleted")) > 0);
            user.setUserTypeId(c.getInt(c.getColumnIndex("userType")));
            c.close();
            return user;
        } else return null;
    }

    @Override
    public boolean onCreate() {
        return false;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] strings, @Nullable String s, @Nullable String[] strings1, @Nullable String s1) {
        db = helper.getReadableDatabase();
        return db.query("" + uri, strings, s, strings1,null,null, s1);
        //s1 = orderby;s=where
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues contentValues) {
        db = helper.getWritableDatabase();
        db.insert("User",null,contentValues);
        return uri;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String s, @Nullable String[] strings) {
        db = helper.getWritableDatabase();
        return db.delete("User",s,strings);
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues contentValues, @Nullable String s, @Nullable String[] strings) {
        db = helper.getWritableDatabase();
        return db.update("User",contentValues,s,strings);
    }
}
