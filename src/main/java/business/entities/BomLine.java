package business.entities;

public class BomLine {
    private int materialId;
    private int quantity;
    private int length;
    private String description;
    private double price;

    public BomLine(int materialId, int quantity, int length, String description, double price) {
        this.materialId = materialId;
        this.quantity = quantity;
        this.length = length;
        this.description = description;
        this.price = price;
    }

    public int getMaterialId() {
        return materialId;
    }

    public void setMaterialId(int materialId) {
        this.materialId = materialId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
