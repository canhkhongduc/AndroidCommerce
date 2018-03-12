package fu.prm391.project.androidcommerce.database.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import fu.prm391.project.androidcommerce.database.entity.OrderItem;

/**
 * Created by Lam on 2/28/2018.
 */

@Dao
public interface OrderItemDAO {
    @Query("SELECT * FROM OrderItem")
    List<OrderItem> getAll();

    @Query("SELECT * FROM OrderItem WHERE orderId = :orderId")
    List<OrderItem> getOrderItemByOrderId(int orderId);

    @Insert
    void insert(OrderItem... orderItem);

    @Insert
    void insert(List<OrderItem> orderItems);

    @Delete
    void delete(OrderItem orderItem);
}
