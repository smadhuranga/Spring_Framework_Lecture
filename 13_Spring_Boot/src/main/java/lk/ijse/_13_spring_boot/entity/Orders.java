package lk.ijse._13_spring_boot.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Orders {
    @Id
    private int id;
    private String date;
    private int customerId;
    private double total;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<OrderDetail> orderDetails;

    public Orders() {
    }

    public Orders(int id, String date, int customerId, double total, List<OrderDetail> orderDetails) {
        this.id = id;
        this.date = date;
        this.customerId = customerId;
        this.total = total;
        this.orderDetails = orderDetails;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public List<OrderDetail> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(List<OrderDetail> orderDetails) {
        this.orderDetails = orderDetails;
    }
}