package lk.ijse._13_spring_boot.dto;

public class OrderDetailDTO {
    private int itemId;
    private int quantity;
    private int orderId;

    public OrderDetailDTO() {
    }

    public OrderDetailDTO(int itemId, int quantity, int orderId) {
        this.itemId = itemId;
        this.quantity = quantity;
        this.orderId = orderId;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }
}