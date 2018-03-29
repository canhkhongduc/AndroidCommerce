package fu.prm391.project.androidcommerce.firebase.model;

import java.util.Date;

public class Order {
    private int orderId;
    private int userId;
    private int paymentTypeId;
    private double paymentAmount;
    private Date orderDate;
    private Date shipDate;
    private Date paymentDate;
    private boolean checkedOut;
    private boolean isCompleted;

    public Order(int userId, int paymentTypeId, double paymentAmount, Date orderDate, Date shipDate, Date paymentDate, boolean checkedOut, boolean isCompleted) {
        this.userId = userId;
        this.paymentTypeId = paymentTypeId;
        this.paymentAmount = paymentAmount;
        this.orderDate = orderDate;
        this.shipDate = shipDate;
        this.paymentDate = paymentDate;
        this.checkedOut = checkedOut;
        this.isCompleted = isCompleted;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getPaymentTypeId() {
        return paymentTypeId;
    }

    public void setPaymentTypeId(int paymentTypeId) {
        this.paymentTypeId = paymentTypeId;
    }

    public double getPaymentAmount() {
        return paymentAmount;
    }

    public void setPaymentAmount(double paymentAmount) {
        this.paymentAmount = paymentAmount;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public Date getShipDate() {
        return shipDate;
    }

    public void setShipDate(Date shipDate) {
        this.shipDate = shipDate;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    public boolean isCheckedOut() {
        return checkedOut;
    }

    public void setCheckedOut(boolean checkedOut) {
        this.checkedOut = checkedOut;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }
}
