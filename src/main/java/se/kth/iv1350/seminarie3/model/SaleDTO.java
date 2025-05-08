package se.kth.iv1350.seminarie3.model;
import java.time.LocalDateTime;
import java.util.List;
import java.util.ArrayList;
import se.kth.iv1350.seminarie3.model.ItemDTO;
import se.kth.iv1350.seminarie3.integration.ExternalInventorySystem;
import se.kth.iv1350.seminarie3.integration.DiscountDatabase;
import se.kth.iv1350.seminarie3.model.CustomerIdentification;

public class SaleDTO{
    private List<ItemDTO> list;
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
    
    public SaleDTO() {
        this.list = new ArrayList<>();
        this.runningTotal = 0;
        this.totalPrice = 0;
        this.startedAt = LocalDateTime.now();
        this.totalVat = 0;
        this.status = SaleDTO.SaleStatus.ONGOING;
        this.externalInventorySystem = new ExternalInventorySystem();
        this.discountDatabase = new DiscountDatabase();
    }

    public boolean addItemToCart(String targetId) {
        ItemDTO targetItem = externalInventorySystem.findItemUsingId(targetId);
        
        if (targetItem == null) {
           
            return false; 
        }
    
        if (itemAlreadyInSaleList(targetItem)) {
            increaseItemQuantity(targetItem);
        } else {
            addNewItemToCart(targetItem.getId());
        }
    
        return true;
    }

    

    
    private boolean itemAlreadyInSaleList(ItemDTO targetItem) {
        for (ItemDTO item : list) {
            if (item.getId().equals(targetItem.getId())) {
                return true;
            }
        }
        return false;
    }
    
    

    public void increaseItemQuantity(ItemDTO item) {
        for (ItemDTO good : list) {
            if (good.getId().equals(item.getId())) {
                good.setQuantity(good.getQuantity() + 1);
                return;
            }
        }
    }



    public boolean addMultipleItemsAtOnce(String targetId, int quantity){

        ItemDTO mulItemDTO = externalInventorySystem.findItemUsingId(targetId);
        
         if (mulItemDTO != null) {
            ItemDTO newItem = new ItemDTO(
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
        ItemDTO original = externalInventorySystem.findItemUsingId(targetId);
        if (original != null) {
            ItemDTO newItem = new ItemDTO(
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
        for (ItemDTO item : list) {
            total += item.getPrice() + (item.getPrice() * item.getVatRate() / 100.0);
        }
        return total;
    }

    public LocalDateTime getStartedAt(){
        return startedAt;
    }

    public List<ItemDTO> getSaleList(){
        return list;
    }

    public double calculateTotalPrice() {
        totalPrice = 0;
        totalVat = 0;

        for (ItemDTO item : list) {
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

        for(ItemDTO item : list){

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
    
    
    
}
 