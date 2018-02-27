package fu.prm391.project.androidcommerce.database.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by Lam on 2/28/2018.
 */

@Entity(tableName = "Category")
public class Category {
    @PrimaryKey(autoGenerate = true)
    private int categoryId;

    @ColumnInfo(name = "categoryName")
    private String categoryName;

    @ColumnInfo(name = "deleted")
    private boolean deleted;

    public Category(String categoryName, boolean deleted) {
        this.categoryName = categoryName;
        this.deleted = deleted;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }
}
