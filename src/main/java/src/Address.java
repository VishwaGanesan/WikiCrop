package src;

public class Address {
    private String street;
    private String addressLine1;
    private String addressLine2;
    private String taluk;
    private String district;
    private int pincode;

    public String getStreet() {
        return street;
    }

    public String getAddressLine1() {
        return addressLine1;
    }

    public String getAddressLine2() {
        return addressLine2;
    }

    public String getTaluk() {
        return taluk;
    }

    public String getDistrict() {
        return district;
    }

    public int getPincode() {
        return pincode;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }

    public void setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
    }

    public void setTaluk(String taluk) {
        this.taluk = taluk;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public void setPincode(int pincode) {
        this.pincode = pincode;
    }


}
