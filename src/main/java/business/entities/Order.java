package business.entities;

import java.sql.Timestamp;

public class Order {
    private int orderId;
    private int userId;
    private double price;
    private String status;
    private int length;
    private int width;
    private Timestamp timestamp;

    public Order(int orderId, int userId, double price, String status, int length, int width, Timestamp timestamp) {
        this.orderId = orderId;
        this.userId = userId;
        this.price = price;
        this.status = status;
        this.length = length;
        this.width = width;
        this.timestamp = timestamp;
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }
}
