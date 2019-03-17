package utils;

import src.Soil;

import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

public class SoilUtil {
    public static Object getSoilByID(int soilID) throws Exception
    {
        Soil soil = new Soil();
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/wikicrop", "root", "Dontgiveup97!");
            String query  = "select * from soil_table where soil_id = ?";
            PreparedStatement stmt = con.prepareStatement(query);
            stmt.setInt(1,soilID);
            ResultSet rs = stmt.executeQuery();
            List<String> cropList = new LinkedList<String>();
            rs.next();
            cropList.add(rs.getInt("crop_1") != 0?CropUtil.getCrop(rs.getInt("crop_1")):null);
            cropList.add(rs.getInt("crop_2") != 0?CropUtil.getCrop(rs.getInt("crop_2")):null);
            cropList.add(rs.getInt("crop_3") != 0?CropUtil.getCrop(rs.getInt("crop_3")):null);
            cropList.add(rs.getInt("crop_4") != 0?CropUtil.getCrop(rs.getInt("crop_4")):null);
            cropList.add(rs.getInt("crop_5") != 0?CropUtil.getCrop(rs.getInt("crop_5")):null);
            cropList.add(rs.getInt("crop_6") != 0?CropUtil.getCrop(rs.getInt("crop_6")):null);
            soil.setSuitableCrops(cropList);
            soil.setSoilID(rs.getInt("soil_id"));
            soil.setSoilName(rs.getString("soil_name"));
        }
        catch (Exception e)
        {

        }
        return soil;
    }
    public static List<String> SuitableCrops(int soilID) throws Exception
    {
        Object soil = getSoilByID(soilID);
        Method getSuitableCrops = soil.getClass().getMethod("getSuitableCrops");
        List<String> suitableCrops = (List<String>) getSuitableCrops.invoke(soil);
        return suitableCrops;
    }
}
