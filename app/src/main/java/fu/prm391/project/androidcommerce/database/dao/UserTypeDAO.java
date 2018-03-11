package fu.prm391.project.androidcommerce.database.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import fu.prm391.project.androidcommerce.database.entity.UserType;

/**
 * Created by Lam on 2/28/2018.
 */

@Dao
public interface UserTypeDAO {
    int TYPE_ADMIN = 1;
    int TYPE_USER = 2;

    @Query("SELECT * FROM UserType")
    public List<UserType> getAll();

    @Insert
    void insert(List<UserType> userTypeList);

    @Insert
    void insert(UserType userType);
}
