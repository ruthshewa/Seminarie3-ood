package se.kth.iv1350.seminarie3.integration;
import java.util.ArrayList;
import java.util.List;
import se.kth.iv1350.seminarie3.model.ItemDTO;
import se.kth.iv1350.seminarie3.model.SaleDTO;

public class ExternalInventorySystem {

    private List<ItemDTO> items = new ArrayList<>();

    private double milkPrice = 10.0;
    private double proteinShakePrice = 26.0;
    private double sodaPrice = 14.0 ;
    private double appleJuicePrice = 20.0;

    private int milkVat = 2;
    private int proteinShakeVat = 6;
    private int sodaVat = 5;
    private int appleJuiceVat = 8;

    private String milkDescription = "Fresh and  natural";
    private String proteinShakeDescription = "Fuel";
    private String sodaDescription = " mmm, yummy";
    private String  appleJuiceDescription = "Tasty";

    public ExternalInventorySystem() {
        items.add(new ItemDTO("9876765443", "Milk", milkVat, milkPrice, "liter", milkDescription,0));
        items.add(new ItemDTO("0752535696", "Protein Shake",proteinShakeVat, proteinShakePrice, "mililiter", proteinShakeDescription,0));
        items.add(new ItemDTO("0737229360", "Soda", sodaVat, sodaPrice, "centiliter", sodaDescription,0));
        items.add(new ItemDTO("0733823065", "Apple Juice", appleJuiceVat, appleJuicePrice, "liter", appleJuiceDescription,0));    
    }
    
    public List<ItemDTO> getAllItems() {
        return items;
    }

    public ItemDTO findItemUsingId(String id){
        for(ItemDTO item : items){
            if(item.getId().equals(id)){
                return item;

            }
        }
        return null;
    }    


    public void updateSaleInInventorySystem (SaleDTO sale){

        System.out.println(" The inventory has been updated given the saleinformation!");


    }
}

