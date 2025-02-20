package lk.ijse._13_spring_boot.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Order {
    @Id
    private int id;
    private int customerId;
    private int itemId;
    private int qty;


    public Order() {
    }

    public Order(int id, int customerId, int itemId, int qty) {
        this.id = id;
        this.customerId = customerId;
        this.itemId = itemId;
        this.qty = qty;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }
}
