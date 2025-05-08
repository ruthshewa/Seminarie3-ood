package se.kth.iv1350.seminarie3.integration;

import java.util.ArrayList;
import java.util.List;
import se.kth.iv1350.seminarie3.model.CustomerIdentification;

public class DiscountDatabase {

    private List<CustomerIdentification> discountBasedOnCustomerId = new ArrayList<>();

    private String ruthMemberId = "14122024";
    private String simonMemberId = "26042025";

    private String ruthEmail = "ruthnunu3@gmail.com";
    private String simonEmail = "simontekle@gmail.com";

    private String ruthPhoneNumber = "0760523065";
    private String simonPhoneNumber = "0706652344";


    public DiscountDatabase(){

        discountBasedOnCustomerId.add(new CustomerIdentification(ruthMemberId, ruthEmail, ruthPhoneNumber,6));
        discountBasedOnCustomerId.add(new CustomerIdentification(simonMemberId, simonEmail, simonPhoneNumber,4));


    }

    public int findDiscountUsingId(String id){
        for(CustomerIdentification customer :  discountBasedOnCustomerId){
            if(customer.getMemberId().equals(id)){
                return customer.getDiscountPercentage();

            }
        }
        return 0;
    }  
    
}
