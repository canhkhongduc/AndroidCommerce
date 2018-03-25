package fu.prm391.project.androidcommerce.database.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import fu.prm391.project.androidcommerce.database.entity.Order;

/**
 * Created by Lam on 2/28/2018.
 */

@Dao
public interface OrderDAO {
    @Query("SELECT * FROM `Order`")
    List<Order> getAll();

    @Query("SELECT * FROM `Order` WHERE userId = :userId")
    List<Order> getOrdersByUserId(int userId);

    @Query("SELECT * FROM `Order` WHERE isCompleted = :isCompleted")
    List<Order> getNewOrders(boolean isCompleted);

    @Query("SELECT * FROM `Order` WHERE orderId = :orderId")
    Order getOrderByOrderId(int orderId);

    @Query("SELECT * FROM `Order` WHERE userId = :userId AND orderId = (SELECT MAX(orderId) FROM `Order` WHERE userId = :userId)")
    Order getLastInsertedOrder(int userId);


    @Insert
    void insert(Order... order);

    @Insert
    void insert(List<Order> orders);

    @Delete
    void delete(Order order);

    @Query("UPDATE `Order` SET isCompleted= :isCompleted WHERE orderId = :orderId")
    public abstract int updateOrderStatus(int orderId, boolean isCompleted);
}
