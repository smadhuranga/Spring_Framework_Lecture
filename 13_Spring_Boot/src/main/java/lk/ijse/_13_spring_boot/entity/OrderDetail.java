package lk.ijse._13_spring_boot.entity;

public class OrderDetail {
    private int itemId;
    private int orderId;
    private int qty;

    public OrderDetail() {
    }

    public OrderDetail(int itemId, int orderId, int qty) {
        this.itemId = itemId;
        this.orderId = orderId;
        this.qty = qty;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }
}
