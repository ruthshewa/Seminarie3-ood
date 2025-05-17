package se.kth.iv1350.seminarie3.model;
import java.time.LocalDateTime;
import java.util.List;
import java.util.ArrayList;
import se.kth.iv1350.seminarie3.model.Item;
import se.kth.iv1350.seminarie3.integration.ExternalInventorySystem;
import se.kth.iv1350.seminarie3.integration.DiscountDatabase;
import se.kth.iv1350.seminarie3.model.CustomerIdentification;
import se.kth.iv1350.seminarie3.model.SaleDTO;
import se.kth.iv1350.seminarie3.model.ItemDTO;


public class Sale{
    private List<Item> list;
    private double runningTotal;
    private double totalPrice;
    private LocalDateTime startedAt;
    private double totalVat;
    private SaleStatus status;
    private ExternalInventorySystem externalInventorySystem;
    private DiscountDatabase discountDatabase;
    public enum SaleStatus {
        ONGOING,
        COMPLETED,
        CANCELLED,
        REFUNDED
    }
    
    public Sale() {
        this.list = new ArrayList<>();
        this.runningTotal = 0;
        this.totalPrice = 0;
        this.startedAt = LocalDateTime.now();
        this.totalVat = 0;
        this.status = Sale.SaleStatus.ONGOING;
        this.externalInventorySystem = new ExternalInventorySystem();
        this.discountDatabase = new DiscountDatabase();
    }

    public void addItemToCart(String targetId) throws InvalidIdentifierException {
        Item targetItem = externalInventorySystem.findItemUsingId(targetId);
        
        if (targetItem == null) {
            throw new InvalidIdentifierException("The following itemID is invalid" + targetId);
        }
    
        if (itemAlreadyInSaleList(targetItem)) {
            increaseItemQuantity(targetItem);
        } else {
            addNewItemToCart(targetItem.getId());
        }
    
       
    }

    

    
    private boolean itemAlreadyInSaleList(Item targetItem) {
        for (Item item : list) {
            if (item.getId().equals(targetItem.getId())) {
                return true;
            }
        }
        return false;
    }
    
    

    public void increaseItemQuantity(Item item) {
        for (Item good : list) {
            if (good.getId().equals(item.getId())) {
                good.setQuantity(good.getQuantity() + 1);
                return;
            }
        }
    }



    public boolean addMultipleItemsAtOnce(String targetId, int quantity){

        Item mulItemDTO = externalInventorySystem.findItemUsingId(targetId);
        
         if (mulItemDTO != null) {
            Item newItem = new Item(
                mulItemDTO.getId(),
                mulItemDTO.getName(),
                mulItemDTO.getVatRate(),
                mulItemDTO.getPrice(),
                mulItemDTO.getUnitOfMeasure(),
                mulItemDTO.getDescription(),
                quantity

            );
            list.add(newItem);
            return true;
           
        }
        return false;

    }
    

    private void addNewItemToCart(String targetId) {
        Item original = externalInventorySystem.findItemUsingId(targetId);
        if (original != null) {
            Item newItem = new Item(
                original.getId(),
                original.getName(),
                original.getVatRate(),
                original.getPrice(),
                original.getUnitOfMeasure(),
                original.getDescription(),
                1

            );
            list.add(newItem);
           
        }
    }
    

    

    public double getRunningTotal() {
        double total = 0;
        for (Item item : list) {
            total += item.getPrice() + (item.getPrice() * item.getVatRate() / 100.0);
        }
        return total;
    }

    public LocalDateTime getStartedAt(){
        return startedAt;
    }

    public List<Item> getSaleList(){
        return list;
    }

    public double calculateTotalPrice() {
        totalPrice = 0;
        totalVat = 0;

        for (Item item : list) {
            double vatAmount = item.getPrice() * item.getQuantity()  *(item.getVatRate() / 100.0);
            totalVat += vatAmount;
            totalPrice += (item.getPrice()  * item.getQuantity()) + vatAmount ;
        }
        return totalPrice; 
    }

    public double getTotalVat(){
        return totalVat;
    }

    public void endSale() {
        this.status = SaleStatus.COMPLETED;
        calculateTotalPrice();
    }

    public double applyDiscountPercentageGivenCustomerId(String memberId){

        int discount = discountDatabase.findDiscountUsingId(memberId);

        if(discount > 0){
            double discountInKronor = totalPrice * (discount/100.0);
            totalPrice -= discountInKronor;

        }
        return totalPrice;
    }

    public double DiscountGivenTheSaleList(){
        double discount = 0;

        for(Item item : list){

            if(item.getName().equals("Soda") && item.getQuantity() >= 3 ){
                discount = 25.0;
            }
        }

        return discount;
    }

    public void applyDiscountGivenTheSaleList(){

        double discount = DiscountGivenTheSaleList();
        totalPrice = totalPrice - discount;
    }


    public double DiscountGivenTheTotalCost(){
        double discount = 0;

        if(totalPrice >= 100){
            discount = 50.0;
        }
        return discount;
    }

    public void applyDiscountGivenTheTotalCost(){

        double discount = DiscountGivenTheTotalCost();
        totalPrice = totalPrice - discount;

    } 

    public SaleDTO toDTO() {
        List<ItemDTO> itemDTOs = new ArrayList<>();
        for (Item item : list) {
            itemDTOs.add(item.toDTO());
        }
        return new SaleDTO(
            itemDTOs,   
            calculateTotalPrice(),
            this.totalVat,
            getRunningTotal(),
            this.startedAt
        );
    }

}
 