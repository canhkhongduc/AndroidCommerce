package fu.prm391.project.androidcommerce.dao;


import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import fu.prm391.project.androidcommerce.model.User;


public class DBHelper extends SQLiteOpenHelper {

    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE User (" +
                "userId integer primary key autoincrement," +
                "username text," +
                "password text," +
                "email text," +
                "phone text," +
                "address text," +
                "userType integer," +
                "isDeleted boolean)";
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if(oldVersion != newVersion) {
            String DROP_TABLE = "DROP TABLE User";
            db.execSQL(DROP_TABLE);
        } else {
            onCreate(db);
        }
    }

}
