package se.kth.iv1350.seminarie3.model;

public class Register {

    private double balance;

    public Register (){
        this.balance = 0;
    
    }

    public void amountPaidAddedToRegister(double paidAmount){

        balance += paidAmount;

    }
    
}

