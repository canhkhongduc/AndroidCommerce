package fu.prm391.project.androidcommerce.database.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
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

    @Query("SELECT COUNT(*) FROM Review")
    int countReviews();

    @Query("SELECT COUNT(*) FROM Review WHERE userId = :userId")
    int countReviewsByUserId(int userId);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertReplace(Review... reviews);

    @Query("SELECT * FROM Review")
    List<Review> getAll();

    @Query("SELECT * FROM Review WHERE productId = :productId")
    List<Review> getAllReviewByProductId(int productId);

    @Query("SELECT * FROM Review WHERE userId = :userId AND productId = :productId")
    Review getReviewByUserIdAndProductId(int userId, int productId);

    @Query("SELECT * FROM Review WHERE userId = :userId")
    List<Review> getReviewsByUserId(int userId);
}
