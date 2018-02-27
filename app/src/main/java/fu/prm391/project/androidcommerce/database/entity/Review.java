package fu.prm391.project.androidcommerce.database.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

import static android.arch.persistence.room.ForeignKey.CASCADE;

/**
 * Created by Lam on 2/28/2018.
 */

@Entity(tableName = "Review", foreignKeys = {@ForeignKey(entity = User.class, parentColumns = "userId", childColumns = "userId", onDelete = CASCADE, onUpdate = CASCADE),
        @ForeignKey(entity = Product.class, parentColumns = "productId", childColumns = "productId", onDelete = CASCADE, onUpdate = CASCADE)})
public class Review {
    @PrimaryKey(autoGenerate = true)
    private int reviewId;

    @ColumnInfo(name = "userId")
    private int userId;

    @ColumnInfo(name = "productId")
    private int productId;

    @ColumnInfo(name = "rating")
    private int rating;

    @ColumnInfo(name = "description")
    private String description;

    @ColumnInfo(name = "deleted")
    private boolean deleted;
}
