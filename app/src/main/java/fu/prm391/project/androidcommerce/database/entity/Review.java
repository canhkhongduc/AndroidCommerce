package fu.prm391.project.androidcommerce.database.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;

import static android.arch.persistence.room.ForeignKey.CASCADE;

/**
 * Created by Lam on 2/28/2018.
 */

@Entity(indices = {@Index(value = {"userId", "productId"})},
        foreignKeys = {@ForeignKey(entity = User.class, parentColumns = "userId", childColumns = "userId", onDelete = CASCADE, onUpdate = CASCADE),
                @ForeignKey(entity = Product.class, parentColumns = "productId", childColumns = "productId", onDelete = CASCADE, onUpdate = CASCADE)})
public class Review {
    @PrimaryKey(autoGenerate = true)
    private int reviewId;

    private int userId;
    private int productId;
    private float rating;
    private String description;
    private boolean deleted;

    public Review() {
    }

    @Ignore
    public Review(int userId, int productId, float rating, String description, boolean deleted) {
        this.userId = userId;
        this.productId = productId;
        this.rating = rating;
        this.description = description;
        this.deleted = deleted;
    }

    public int getReviewId() {
        return reviewId;
    }

    public void setReviewId(int reviewId) {
        this.reviewId = reviewId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }
}
