package fu.prm391.project.androidcommerce.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import fu.prm391.project.androidcommerce.database.dao.UserDAO;
import fu.prm391.project.androidcommerce.database.entity.User;

/**
 * Created by Lam on 2/28/2018.
 */

@Database(entities = {User.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase INSTANCE;

    public abstract UserDAO userDAO();

    public static AppDatabase getAppDatabase(Context context) {
        if(INSTANCE == null){
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "commerce-db")
                    .allowMainThreadQueries()
                    .build();
        }

        return INSTANCE;
    }
}
