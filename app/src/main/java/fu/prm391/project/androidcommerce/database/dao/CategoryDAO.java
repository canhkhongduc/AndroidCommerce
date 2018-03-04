package fu.prm391.project.androidcommerce.database.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import fu.prm391.project.androidcommerce.database.entity.Category;
import fu.prm391.project.androidcommerce.database.entity.User;

/**
 * Created by Lam on 2/28/2018.
 */

@Dao
public interface CategoryDAO {
    @Query("SELECT * FROM Category")
    List<Category> getAll();

    @Query("SELECT * FROM Category WHERE categoryId = :categoryId")
    Category getCategoryByCategoryId(int categoryId);


    @Insert
    void insert(Category... category);
}
