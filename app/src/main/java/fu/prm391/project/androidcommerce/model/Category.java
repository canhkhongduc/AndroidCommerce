package fu.prm391.project.androidcommerce.model;



public class Category {
    private int categoryId;
    private String categoryName;
    private boolean isDeleted;

    public Category() {
    }

    public Category(int categoryId, String categoryName, boolean isDeleted) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.isDeleted = isDeleted;
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
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }
}
