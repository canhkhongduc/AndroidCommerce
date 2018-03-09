package fu.prm391.project.androidcommerce.database.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import fu.prm391.project.androidcommerce.database.entity.Product;

/**
 * Created by Lam on 2/28/2018.
 */

@Dao
public interface ProductDAO {
    @Query("SELECT * FROM Product")
    List<Product> getAll();

    @Query("SELECT * FROM Product WHERE productId = :productId")
    Product getProductByProductId(int productId);

    @Insert
    void insert(Product... product);
}