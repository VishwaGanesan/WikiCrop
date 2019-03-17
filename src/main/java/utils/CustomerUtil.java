package utils;

import src.Customer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class CustomerUtil {
    public static Object createCustomer(Customer c) throws Exception {
        String customerName = c.getCustomerName();
        String mobileno = c.getMobileNo();
        Boolean isFarmer = c.isFarmer();
        Boolean isConsumer = c.isConsumer();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/wikicrop", "root", "Dontgiveup97!");
            String query = "Insert into customer_table(customer_name,mobile_no,is_farmer,is_customer,session_id) values(?,?,?,?,?)";
            PreparedStatement stmt = con.prepareStatement(query);
            stmt.setString(1, customerName);
            stmt.setString(2, mobileno);
            stmt.setBoolean(3, isFarmer);
            stmt.setBoolean(4, isConsumer);
            stmt.setString(5,"test");
            stmt.executeUpdate();

        } catch (Exception e) {

        }
        return getCustomer(mobileno);
    }
    public static Object getCustomer(String mobile) throws Exception {
        Customer customer = new Customer();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/wikicrop", "root", "Dontgiveup97!");
            String query = "select * from customer_table where mobile_no = ?";
            PreparedStatement stmt = con.prepareStatement(query);
            stmt.setString(1, mobile);
            ResultSet rs = stmt.executeQuery();
            while(rs.next())
            {
                customer.setCustomerID(rs.getInt("customer_id"));
                customer.setCustomerName(rs.getString("customer_name"));
                customer.setMobileNo(rs.getString("mobile_no"));
                customer.setFarmer(rs.getBoolean("is_farmer"));
                customer.setConsumer(rs.getBoolean("is_consumer"));
            }

        } catch (Exception e) {

        }
        return  customer;
    }
    public static boolean isAccountExist(String mobile) throws Exception {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/wikicrop", "root", "Dontgiveup97!");
            String query = "Select * from customer_table where mobile_no = ?";
            PreparedStatement stmt = con.prepareStatement(query);
            stmt.setString(1, mobile);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (Exception e) {

        }
        return false;
    }
}
