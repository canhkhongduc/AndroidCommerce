package fu.prm391.project.androidcommerce.database.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import fu.prm391.project.androidcommerce.database.entity.Review;

/**
 * Created by Lam on 2/28/2018.
 */

@Dao
public interface ReviewDAO {
    @Insert
    void insert(Review... reviews);

    @Query("SELECT * FROM Review")
    List<Review> getAll();

    @Query("SELECT * FROM Review WHERE productId = :productId")
    List<Review> getAllReviewByProductId(int productId);
}
