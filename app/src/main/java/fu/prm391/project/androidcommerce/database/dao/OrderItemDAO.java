package fu.prm391.project.androidcommerce.database.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;

import java.util.List;

import fu.prm391.project.androidcommerce.database.entity.OrderItem;

/**
 * Created by Lam on 2/28/2018.
 */

@Dao
public interface OrderItemDAO {
    @Insert
    void insert(OrderItem... orderItem);

    @Insert
    void insert(List<OrderItem> orderItems);

    @Delete
    void delete(OrderItem orderItem);
}
