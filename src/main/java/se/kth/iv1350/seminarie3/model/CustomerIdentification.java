package se.kth.iv1350.seminarie3.model;

public class CustomerIdentification {

    private String memberId;
    private String email;
    private String phoneNumber;
    private int discountPercentage;

    public CustomerIdentification(String memberId, String email, String phoneNumber, int discountPercentage ) {
        this.memberId = memberId;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.discountPercentage = discountPercentage;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getDiscountPercentage() {
        return  discountPercentage;
    }

    

}
