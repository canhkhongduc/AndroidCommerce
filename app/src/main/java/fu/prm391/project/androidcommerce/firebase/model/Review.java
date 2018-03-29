package fu.prm391.project.androidcommerce.firebase.model;

public class Review {
    private int reviewId;
    private int userId;
    private int productId;
    private float rating;
    private String description;
    private boolean deleted;

    public Review() {
    }

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
