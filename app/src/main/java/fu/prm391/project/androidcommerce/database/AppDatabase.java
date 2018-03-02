package fu.prm391.project.androidcommerce.database;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.support.annotation.NonNull;

import fu.prm391.project.androidcommerce.database.dao.CategoryDAO;
import fu.prm391.project.androidcommerce.database.dao.OrderDAO;
import fu.prm391.project.androidcommerce.database.dao.OrderItemDAO;
import fu.prm391.project.androidcommerce.database.dao.PaymentTypeDAO;
import fu.prm391.project.androidcommerce.database.dao.ProductDAO;
import fu.prm391.project.androidcommerce.database.dao.ReviewDAO;
import fu.prm391.project.androidcommerce.database.dao.UserDAO;
import fu.prm391.project.androidcommerce.database.dao.UserTypeDAO;
import fu.prm391.project.androidcommerce.database.entity.Category;
import fu.prm391.project.androidcommerce.database.entity.Order;
import fu.prm391.project.androidcommerce.database.entity.OrderItem;
import fu.prm391.project.androidcommerce.database.entity.PaymentType;
import fu.prm391.project.androidcommerce.database.entity.Product;
import fu.prm391.project.androidcommerce.database.entity.Review;
import fu.prm391.project.androidcommerce.database.entity.User;
import fu.prm391.project.androidcommerce.database.entity.UserType;

/**
 * Created by Lam on 2/28/2018.
 */

@Database(entities = {User.class, UserType.class, Review.class, Product.class, PaymentType.class, OrderItem.class, Order.class, Category.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase INSTANCE;

    public abstract UserDAO userDAO();

    public abstract CategoryDAO categoryDAO();

    public abstract OrderDAO orderDAO();

    public abstract OrderItemDAO orderItemDAO();

    public abstract PaymentTypeDAO paymentTypeDAO();

    public abstract ProductDAO productDAO();

    public abstract ReviewDAO reviewDAO();

    public abstract UserTypeDAO userTypeDAO();

    public static AppDatabase getAppDatabase(Context context) {
        if (INSTANCE == null) {
            RoomDatabase.Callback dbCallback = new RoomDatabase.Callback(){
                @Override
                public void onCreate(@NonNull SupportSQLiteDatabase db) {
                    super.onCreate(db);

                    db.execSQL("INSERT INTO UserType VALUES(1, 'Admin')");
                    db.execSQL("INSERT INTO UserType VALUES(2, 'User')");
                }
            };

            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "commerce-db")
                    .addCallback(dbCallback)
                    .build();
        }

        return INSTANCE;
    }
}
