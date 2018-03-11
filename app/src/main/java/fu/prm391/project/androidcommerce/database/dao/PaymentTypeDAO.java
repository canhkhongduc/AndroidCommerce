package fu.prm391.project.androidcommerce.database.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;

import fu.prm391.project.androidcommerce.database.entity.PaymentType;

/**
 * Created by Lam on 2/28/2018.
 */

@Dao
public interface PaymentTypeDAO {
    @Insert
    void insert(PaymentType... paymentType);
}
