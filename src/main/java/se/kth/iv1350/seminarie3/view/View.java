package se.kth.iv1350.seminarie3.view;
import se.kth.iv1350.seminarie3.controller.Controller;
import se.kth.iv1350.seminarie3.model.SaleDTO;
import se.kth.iv1350.seminarie3.model.ItemDTO;
import se.kth.iv1350.seminarie3.model.Payment;

public class View {
    private Controller controller;
    public View(Controller controller) {
        this.controller = controller;
    }
    

    public void printReceipt() {
        System.out.println("------------------ Begin receipt------------------");
        System.out.println("Time of Sale:"  + controller.getSale().getStartedAt());
        System.out.println();
    
        for (ItemDTO item : controller.getSale().getSaleList()){
            double lineTotal = item.getPrice() * item.getQuantity();
            System.out.println(item.getName() + " " + item.getQuantity() + "x" + " " + item.getPrice() +" " + lineTotal + "SEK");
        }
       
        System.out.println();
        System.out.println("Total including VAT:" + controller.getSale().calculateTotalPrice());
        System.out.println("VAT:" + controller.getSale().getTotalVat());
        System.out.println();
    
        System.out.println("Cash:" + controller.getPayment().getPaidAmount());
        System.out.println("Change:" + controller.getPayment().getChangeBack());
        System.out.println("------------------ End receipt-------------------");
    }
}
