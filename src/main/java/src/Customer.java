package src;

public class Customer {
    private int customerID;
    private String customerName;
    private String mobileNo;
    private boolean isFarmer;
    private boolean isConsumer;

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public void setCustomerName(String customer_name) {
        this.customerName = customer_name;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public void setFarmer(boolean farmer) {
        isFarmer = farmer;
    }

    public void setConsumer(boolean consumer) {
        isConsumer = consumer;
    }

    public int getCustomerID() {
        return customerID;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public boolean isFarmer() {
        return isFarmer;
    }

    public boolean isConsumer() {
        return isConsumer;
    }
}
