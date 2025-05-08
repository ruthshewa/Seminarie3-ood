package se.kth.iv1350.seminarie3.controller;
import se.kth.iv1350.seminarie3.model.Payment;
import se.kth.iv1350.seminarie3.model.SaleDTO;
import se.kth.iv1350.seminarie3.integration.ExternalAccountingSystem;
import se.kth.iv1350.seminarie3.integration.ExternalInventorySystem;
import se.kth.iv1350.seminarie3.model.Register;

public class Controller{
    private SaleDTO sale;
    private ExternalAccountingSystem accountingSystem;
    private ExternalInventorySystem inventorySystem;
    private Payment payment;
    private Register register;

    public Controller (){
        this.accountingSystem = new ExternalAccountingSystem();
        this.inventorySystem = new ExternalInventorySystem();
        this.register = new Register();
    }

    public void startSale(){
        sale = new SaleDTO();
    }

    public void addItem(String itemIdentifier){
        if(sale.addItemToCart(itemIdentifier)){
            System.out.println("Item successfully added to cart.");
        }
        else {
            System.out.println("Item does not exist therefore it is invalid");
        }
       
    }

    public void addMultipuleItems(String itemId,int itemQuantity){
        if(sale.addMultipleItemsAtOnce(itemId,itemQuantity)){
            System.out.println("Multipule items successfully added to cart.");
        }
        else {
            System.out.println("Item does not exist therefore it is invalid");
        }
    }


    public double getRunningTotal() {
        return sale.getRunningTotal();
    }


    public void endSale(String memberId) {
        sale.endSale();
        sale.applyDiscountPercentageGivenCustomerId(memberId);
        sale.DiscountGivenTheTotalCost();
        sale.DiscountGivenTheSaleList();
        accountingSystem.recordSaleInAccountingSystem(sale);
        inventorySystem.updateSaleInInventorySystem(sale);
    }

    public void cashPayment(double paidAmount){
        double totPrice = sale.calculateTotalPrice();
        payment = new Payment(paidAmount, totPrice);
        register.amountPaidAddedToRegister(payment.getPaidAmount());
        
    }

    public SaleDTO getSale(){
        return sale;
    }

    public Payment getPayment (){
        return payment;
    }
}

