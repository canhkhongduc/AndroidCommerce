package fu.prm391.project.androidcommerce.firebase.model;

public class PaymentType {
    private int paymentTypeId;

    private String paymentTypeName;

    public PaymentType(String paymentTypeName) {
        this.paymentTypeName = paymentTypeName;
    }

    public int getPaymentTypeId() {
        return paymentTypeId;
    }

    public void setPaymentTypeId(int paymentTypeId) {
        this.paymentTypeId = paymentTypeId;
    }

    public String getPaymentTypeName() {
        return paymentTypeName;
    }

    public void setPaymentTypeName(String paymentTypeName) {
        this.paymentTypeName = paymentTypeName;
    }
}
