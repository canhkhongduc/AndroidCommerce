package fu.prm391.project.androidcommerce.database.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by Lam on 2/28/2018.
 */

@Entity
public class Category {
    @PrimaryKey(autoGenerate = true)
    private int categoryId;

    private String categoryName;
    private String categoryImagePath;
    private boolean deleted;

    public Category(String categoryName, String categoryImagePath, boolean deleted) {
        this.categoryName = categoryName;
        this.categoryImagePath = categoryImagePath;
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

    public String getCategoryImagePath() {
        return categoryImagePath;
    }

    public void setCategoryImagePath(String categoryImagePath) {
        this.categoryImagePath = categoryImagePath;
    }

    @Override
    public String toString() {
        return categoryName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Category category = (Category) o;

        return categoryName.equals(category.categoryName);
    }

    @Override
    public int hashCode() {
        return categoryName.hashCode();
    }
}
