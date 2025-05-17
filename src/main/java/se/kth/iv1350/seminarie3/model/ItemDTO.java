package se.kth.iv1350.seminarie3.model; 


public class ItemDTO {
    private final String id;
    private final String name;
    private final int vatRate;
    private final double price;
    private final String unitOfMeasure;
    private final String description;
    private int quantity;

    public ItemDTO(String id, String name, int vatRate, double price, String unitOfMeasure, String description, int quantity) {
        this.id = id;
        this.name = name;
        this.vatRate = vatRate;
        this.price = price;
        this.unitOfMeasure = unitOfMeasure;
        this.description = description;
        this.quantity = quantity;
    }
    
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getVatRate() {
        return vatRate;
    }

    public double getPrice() {
        return price;
    }

    public String getUnitOfMeasure() {
        return unitOfMeasure;
    }

    public String getDescription() {
        return description;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
