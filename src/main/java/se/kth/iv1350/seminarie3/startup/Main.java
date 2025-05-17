package se.kth.iv1350.seminarie3.startup;
import se.kth.iv1350.seminarie3.controller.Controller;
import se.kth.iv1350.seminarie3.view.View;



public class Main {


    public static void main(String[] args) {
        Controller controller = new Controller();
        View view = new View(controller);
        view.runSale();
    }

        

   
}
    

