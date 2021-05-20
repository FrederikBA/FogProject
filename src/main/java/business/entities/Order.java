package business.entities;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.DecimalFormat;

public class Order {
    private int orderId;
    private int userId;
    private Double price;
    private String status;
    private int length;
    private int width;
    private Timestamp timestamp;
    private User user;

    public Order(int orderId, int userId, Double price, String status, int length, int width, Timestamp timestamp) {
        this.orderId = orderId;
        this.userId = userId;
        this.price = price;
        this.status = status;
        this.length = length;
        this.width = width;
        this.timestamp = timestamp;
    }

    public Order(int orderId, int userId, Timestamp timestamp, Double price, String status) {
        this.orderId = orderId;
        this.userId = userId;
        this.timestamp = timestamp;
        this.price = price;
        this.status = status;
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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
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

    public void setOrderId(User user) {
        this.user = user;
    }
}
