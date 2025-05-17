package se.kth.iv1350.seminarie3.controller;
import se.kth.iv1350.seminarie3.model.Payment;
import se.kth.iv1350.seminarie3.model.Sale;
import se.kth.iv1350.seminarie3.integration.ExternalAccountingSystem;
import se.kth.iv1350.seminarie3.integration.ExternalInventorySystem;
import se.kth.iv1350.seminarie3.model.Register;
import se.kth.iv1350.seminarie3.model.SaleDTO;

public class Controller{
    private Sale sale;
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
        sale = new Sale();
    }

    public boolean addItem(String itemIdentifier){
        return sale.addItemToCart(itemIdentifier);
    }

    public boolean addMultipuleItems(String itemId,int itemQuantity){
        return sale.addMultipleItemsAtOnce(itemId,itemQuantity);
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

    public SaleDTO getSaleDTO() {
        return sale.toDTO();
    }

    public Payment getPayment (){
        return payment;
    }
}

