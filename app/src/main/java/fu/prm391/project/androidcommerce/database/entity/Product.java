package fu.prm391.project.androidcommerce.database.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

import static android.arch.persistence.room.ForeignKey.CASCADE;

/**
 * Created by Lam on 2/28/2018.
 */

@Entity(foreignKeys = @ForeignKey(entity = Category.class, parentColumns = "categoryId", childColumns = "categoryId", onDelete = CASCADE, onUpdate = CASCADE))
public class Product {
    @PrimaryKey(autoGenerate = true)
    private int productId;

    private String productName;
    private int productImagePath;
    private int categoryId;
    private float productPrice;
    private String productDescription;
    private boolean deleted;
    private double averageRating;

    public Product(String productName, int productImagePath, int categoryId, float productPrice, String productDescription, boolean deleted, double averageRating) {
        this.productName = productName;
        this.productImagePath = productImagePath;
        this.categoryId = categoryId;
        this.productPrice = productPrice;
        this.productDescription = productDescription;
        this.deleted = deleted;
        this.averageRating = averageRating;
    }

    public double getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(double averageRating) {
        this.averageRating = averageRating;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getProductImagePath() {
        return productImagePath;
    }

    public void setProductImagePath(int productImagePath) {
        this.productImagePath = productImagePath;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public float getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(float productPrice) {
        this.productPrice = productPrice;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }
}
