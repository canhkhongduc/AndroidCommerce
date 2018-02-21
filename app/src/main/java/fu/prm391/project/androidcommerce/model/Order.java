package fu.prm391.project.androidcommerce.model;


import java.util.Date;

public class Order {
    private int orderId;
    private int userId;
    private int paymentId;
    private Date orderDate;
    private Date shipDate;
    private Date paymentDate;
    private boolean isCheckedOut;

    public Order() {
    }

    public Order(int orderId, int userId, int paymentId, Date orderDate, Date shipDate, Date paymentDate, boolean isCheckedOut) {
        this.orderId = orderId;
        this.userId = userId;
        this.paymentId = paymentId;
        this.orderDate = orderDate;
        this.shipDate = shipDate;
        this.paymentDate = paymentDate;
        this.isCheckedOut = isCheckedOut;
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

    public int getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(int paymentId) {
        this.paymentId = paymentId;
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
        return isCheckedOut;
    }

    public void setCheckedOut(boolean checkedOut) {
        isCheckedOut = checkedOut;
    }
}
