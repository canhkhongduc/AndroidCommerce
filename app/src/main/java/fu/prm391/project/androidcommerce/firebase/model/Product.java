package fu.prm391.project.androidcommerce.firebase.model;

public class Product {
    private int productId;
    private String productName;
    private String productImagePath;
    private int categoryId;
    private float productPrice;
    private String productDescription;
    private int stock;
    private boolean deleted;
    private double averageRating;

    public Product(String productName, String productImagePath, int categoryId, float productPrice, String productDescription, int stock, boolean deleted, double averageRating) {
        this.productName = productName;
        this.productImagePath = productImagePath;
        this.categoryId = categoryId;
        this.productPrice = productPrice;
        this.productDescription = productDescription;
        this.stock = stock;
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

    public String getProductImagePath() {
        return productImagePath;
    }

    public void setProductImagePath(String productImagePath) {
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

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
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
