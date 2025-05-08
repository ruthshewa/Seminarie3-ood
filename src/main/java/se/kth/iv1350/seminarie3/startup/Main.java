package se.kth.iv1350.seminarie3.startup;
import se.kth.iv1350.seminarie3.controller.Controller;
import se.kth.iv1350.seminarie3.view.View;



public class Main {


    public static void main(String[] args) {
        Controller controller = new Controller();
        View view = new View(controller);

        controller.startSale();

        controller.addItem("9876765443");// Milk
        controller.addItem("0737229360");// Soda
        controller.addItem("9876765443");
        controller.addMultipuleItems("0733823065", 5); //appelJuice
       // controller.addItem("2");// test case to see if the invalid message is sent 
        controller.addItem("0737229360");

        controller.endSale("12345");// MemberID

        controller.cashPayment(300.0);

        view.printReceipt();
    }

   
}
    

