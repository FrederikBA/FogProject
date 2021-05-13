package business.entities;

public class Material {
    private int materialId;
    private String description;
    private String unit;
    private double pricePerUnit;
    private String type;

    public Material(int materialId, String description, String unit, double pricePerUnit, String type) {
        this.materialId = materialId;
        this.description = description;
        this.unit = unit;
        this.pricePerUnit = pricePerUnit;
        this.type = type;
    }

    public int getMaterialId() {
        return materialId;
    }

    public void setMaterialId(int materialId) {
        this.materialId = materialId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public double getPricePerUnit() {
        return pricePerUnit;
    }

    public void setPricePerUnit(double pricePerUnit) {
        this.pricePerUnit = pricePerUnit;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return description;
    }
}