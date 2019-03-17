package src;

public class Otp {
    private String mobileNo;
    private String msg;
    private String details;

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getMsg() {
        return msg;
    }

    public String getDetails() {
        return details;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getMobileNo() {
        return mobileNo;
    }
}
