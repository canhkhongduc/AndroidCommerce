package fu.prm391.project.androidcommerce.database.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;

import static android.arch.persistence.room.ForeignKey.CASCADE;

/**
 * Created by Lam on 2/28/2018.
 */

@Entity(tableName = "OrderItem", primaryKeys = {"orderId", "productId"}
        , foreignKeys = {@ForeignKey(entity = Order.class, parentColumns = "orderId", childColumns = "orderId", onDelete = CASCADE, onUpdate = CASCADE),
        @ForeignKey(entity = Product.class, parentColumns = "productId", childColumns = "productId", onDelete = CASCADE, onUpdate = CASCADE)})
public class OrderItem {
    @ColumnInfo(name = "orderId")
    private int orderId;

    @ColumnInfo(name = "productId")
    private int productId;

    @ColumnInfo(name = "quantity")
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
}
