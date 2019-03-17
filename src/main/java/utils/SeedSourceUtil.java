package utils;

import src.Address;
import src.SeedSource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class SeedSourceUtil {

    public static SeedSource getSeedSourceByID(int ssID) throws  Exception
    {
        SeedSource ss = new SeedSource();
        Address ad = new Address();
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/wikicrop", "root", "Dontgiveup97!");
            String query = "select * from seed_source_table where address_id = ?";
            PreparedStatement stmt = con.prepareStatement(query);
            stmt.setInt(1,ssID);
            ResultSet rs = stmt.executeQuery();
            while(rs.next())
            {
                ad.setAddressLine1(rs.getString("address_line_1"));
                ad.setAddressLine2(rs.getString("address_line_2"));
                ad.setTaluk(rs.getString("taluk"));
                ad.setDistrict(rs.getString("district"));
                ad.setPincode(rs.getInt("pincode"));
                ss.setAddress(ad);
            }

        }
        catch (Exception e)
        {
            throw e;
        }
        return ss;
    }
}
