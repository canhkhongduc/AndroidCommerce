package fu.prm391.project.androidcommerce.database.dao;

import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import fu.prm391.project.androidcommerce.database.entity.User;

/**
 * Created by Lam on 2/28/2018.
 */

public interface UserDAO {
    @Query("SELECT * FROM User")
    List<User> getAll();

    @Query("SELECT COUNT(*) FROM User")
    int countUsers();

    @Insert
    void insert(User... user);

    @Insert
    void insert(List<User> userList);

    @Delete
    void delete(User user);
}
