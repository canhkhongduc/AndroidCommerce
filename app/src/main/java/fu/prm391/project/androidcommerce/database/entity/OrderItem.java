package fu.prm391.project.androidcommerce.database.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;

import static android.arch.persistence.room.ForeignKey.CASCADE;

/**
 * Created by Lam on 2/28/2018.
 */

@Entity(indices = {@Index(value = "productId")},
        primaryKeys = {"orderId", "productId"}
        , foreignKeys = {@ForeignKey(entity = Order.class, parentColumns = "orderId", childColumns = "orderId", onDelete = CASCADE, onUpdate = CASCADE),
        @ForeignKey(entity = Product.class, parentColumns = "productId", childColumns = "productId", onDelete = CASCADE, onUpdate = CASCADE)})
public class OrderItem {
    private int orderId;
    private int productId;
    private int quantity;

    public OrderItem(int orderId, int productId, int quantity) {
        this.orderId = orderId;
        this.productId = productId;
        this.quantity = quantity;
    }


    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object obj) {
        boolean isEqual = false;
        if (obj != null && obj instanceof OrderItem){
            isEqual = (this.productId == ((OrderItem)obj).productId);
        }
        return isEqual;
    }

    @Override
    public int hashCode() {
        return this.productId;
    }
}
