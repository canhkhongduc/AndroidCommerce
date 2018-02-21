package fu.prm391.project.androidcommerce.model;



public class Product {
    private int productId;
    private String productName;
    private String productImage;
    private int categoryId;
    private float productPrice;
    private String productDescription;
    private boolean isDeleted;

    public Product() {
    }

    public Product(int productId, String productName, String productImage, int categoryId, float productPrice, String productDescription, boolean isDeleted) {
        this.productId = productId;
        this.productName = productName;
        this.productImage = productImage;
        this.categoryId = categoryId;
        this.productPrice = productPrice;
        this.productDescription = productDescription;
        this.isDeleted = isDeleted;
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

    public String getProductImage() {
        return productImage;
    }

    public void setProductImage(String productImage) {
        this.productImage = productImage;
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
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }
}
