package fu.prm391.project.androidcommerce.database.dao;

import android.arch.persistence.room.Dao;

/**
 * Created by Lam on 2/28/2018.
 */

@Dao
public interface UserTypeDAO {
    int TYPE_ADMIN = 1;
    int TYPE_USER = 2;
}
