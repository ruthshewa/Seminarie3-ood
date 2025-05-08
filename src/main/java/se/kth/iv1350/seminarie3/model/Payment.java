package se.kth.iv1350.seminarie3.model;

public class Payment {
    private double paidAmount;
    private double changeBack;

    public Payment(double paidAmount, double totPrice){
        this.paidAmount = paidAmount;
        this.changeBack = calculateChangeBack(totPrice);
    }

    private double calculateChangeBack(double totPrice){
        return paidAmount - totPrice;
    }

    public double getPaidAmount() {
        return paidAmount;
    }

    public double getChangeBack() {
        return changeBack;
    }
    
}

