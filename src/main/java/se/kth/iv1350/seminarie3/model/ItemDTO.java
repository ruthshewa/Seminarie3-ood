package se.kth.iv1350.seminarie3.model;

public class ItemDTO {

    private String name;
    private String id;
    private double price;
    private String unitOfMeasure;
    private String description;
    private int vatRate;
    private int quantity;

    public ItemDTO (String id,String name, int vatRate, double price, String unitOfMeasure, String description,int quantity){
       this.id = id;
       this.name = name;
       this.price = price;
       this.unitOfMeasure = unitOfMeasure;
       this.description = description;
       this.vatRate = vatRate;
       this.quantity = quantity;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getUnitOfMeasure() {
        return unitOfMeasure;
    }

    public void setUnitOfMeasure(String unitOfMeasure) {
        this.unitOfMeasure = unitOfMeasure;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getVatRate() {
        return vatRate;
    }

    public void setVatRate(int vatRate) {
        this.vatRate = vatRate;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}

