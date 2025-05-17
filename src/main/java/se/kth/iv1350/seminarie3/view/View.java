package se.kth.iv1350.seminarie3.view;
import se.kth.iv1350.seminarie3.controller.Controller;
import se.kth.iv1350.seminarie3.model.SaleDTO;
import se.kth.iv1350.seminarie3.model.Payment;
import se.kth.iv1350.seminarie3.model.ItemDTO;

public class View {
    private Controller controller;
    public View(Controller controller) {
        this.controller = controller;
    }
    

    public void printReceipt() {
        System.out.println("------------------ Begin receipt------------------");
        System.out.println("Time of Sale:"  + controller.getSaleDTO().getStartedAt());
        System.out.println();
    
        for (ItemDTO item : controller.getSaleDTO().getSaleList()){
            double lineTotal = item.getPrice() * item.getQuantity();
            System.out.println(item.getName() + " " + item.getQuantity() + "x" + " " + item.getPrice() +" " + lineTotal + "SEK");
        }
       
        System.out.println();
        System.out.println("Total including VAT:" + controller.getSaleDTO().getTotalPrice());
        System.out.println("VAT:" + controller.getSaleDTO().getTotalVat());
        System.out.println();
    
        System.out.println("Cash:" + controller.getPayment().getPaidAmount());
        System.out.println("Change:" + controller.getPayment().getChangeBack());
        System.out.println("------------------ End receipt-------------------");
    }

    private void addItemAndPrint(String identifier){
        boolean item = controller.addItem(identifier);
        if(!item){
            System.out.println( "Item does not exist");
            return;
        }
        
        System.out.println( "Item successfully added to the cart.");
        addItemAndPrint(identifier, 1); 
    }
   

    private void addItemAndPrint(String identifier,int quantity){

        SaleDTO sale = controller.getSaleDTO();
        ItemDTO recentleyAddedItem = sale.getSaleList().get(sale.getSaleList().size() - 1);

            System.out.println("Add "  + quantity+ " item" + (quantity > 1 ? "s" : "")+ " with item id " + recentleyAddedItem.getId() + ":");
            System.out.println("Item ID" + recentleyAddedItem.getId());
            System.out.println("Item name" + recentleyAddedItem.getName());
            System.out.println("Item price" + recentleyAddedItem.getPrice()+ "SEK");
            System.out.println("Item Vat" + recentleyAddedItem.getVatRate());
            System.out.println("Item description" + recentleyAddedItem.getDescription());

            System.out.println("Total cost" + sale.getRunningTotal() + "SEK");
            System.out.println("Total VAT" + sale.getTotalVat() + "SEK");
            System.out.println();

    }
    public void runSale (){

        controller.startSale();
        
        addItemAndPrint("9876765443");// Milk
        addItemAndPrint("0737229360");// Soda
        addItemAndPrint("9876765443");
        boolean item = controller.addMultipuleItems("0733823065", 5); //appelJuice
        System.out.println(item ? "Multiple items successfully added to the cart." : "Item does not exist");
        addItemAndPrint("0737229360");

        controller.endSale("12345");// MemberID

        controller.cashPayment(300.0);

        printReceipt();
    }


    
}
