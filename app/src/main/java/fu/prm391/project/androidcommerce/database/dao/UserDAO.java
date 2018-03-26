package fu.prm391.project.androidcommerce.database.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import fu.prm391.project.androidcommerce.database.entity.User;

/**
 * Created by Lam on 2/28/2018.
 */

@Dao
public interface UserDAO {
    @Query("SELECT * FROM User")
    List<User> getAll();

    @Query("SELECT * FROM User WHERE username = :username")
    User getUserByUsername(String username);

    @Query("SELECT * FROM User WHERE userId = :userId")
    User getUserByUserId(int userId);

    @Query("SELECT COUNT(*) FROM User")
    int countUsers();

    @Query("SELECT * FROM USER WHERE username = :username AND password = :password")
    User userLogin(String username, String password);

    @Insert
    void insert(User... user);

    @Insert
    void insert(List<User> userList);

    @Delete
    void delete(User user);

    @Query("UPDATE User SET userType= :userType WHERE userId = :userId")
    public abstract int updateUserType(int userId, int userType);

    @Query("UPDATE User SET userImagePath= :userImagePath WHERE userId = :userId")
    public abstract int updateUserImagePath(int userId, String userImagePath);

}
