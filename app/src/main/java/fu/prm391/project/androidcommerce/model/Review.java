package fu.prm391.project.androidcommerce.model;

/**
 * Created by Khổng Cảnh on 2/21/2018.
 */

public class Review {
    private int reviewId;
    private int userId;
    private int productId;
    private int rate;
    private String description;
    private boolean isDeleted;

    public Review() {
    }

    public Review(int reviewId, int userId, int productId, int rate, String description, boolean isDeleted) {
        this.reviewId = reviewId;
        this.userId = userId;
        this.productId = productId;
        this.rate = rate;
        this.description = description;
        this.isDeleted = isDeleted;
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

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }
}
