package utils;

import src.CropException;
import src.Variety;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class VarietyUtil {
    public static Object getVarietyByID(int varietyID) throws CropException {
        Variety variety = new Variety();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/wikicrop", "root", "Dontgiveup97!");
            String query = "Select * from variety_table where variety_id = ?";
            PreparedStatement stmt = con.prepareStatement(query);
            stmt.setInt(1, varietyID);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                variety.setVarietyID(rs.getInt("variety_id"));
                variety.setVarietyName(rs.getString("variety_name"));
                variety.setCropName(CropUtil.getCrop(rs.getInt("crop_id")));
                variety.setMaxDuration(rs.getInt("max_duration"));
                variety.setMinDuration(rs.getInt("min_duration"));
                variety.setIrrigatedYieldRate(rs.getDouble("rainfed_yield_rate"));
                variety.setRainfedYieldRate(rs.getDouble("irrigated_yield_rate"));
                variety.setSeedSource(SeedSourceUtil.getSeedSourceByID(rs.getInt("seed_source_id")));
            }
        } catch (Exception e) {
            CropException ce = new CropException();
            ce.setCode(1003);
            ce.setMsg("Exception occured while access season_table-" + e.toString());
            throw ce;
        }
        return variety;
    }
    public static List<Object> getVarities() throws CropException {
        List<Object> varieties = new LinkedList<Object>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/wikicrop", "root", "Dontgiveup97!");
            String query = "Select * from variety_table";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                Variety variety = new Variety();
                variety.setVarietyID(rs.getInt("variety_id"));
                variety.setVarietyName(rs.getString("variety_name"));
                variety.setCropName(CropUtil.getCrop(rs.getInt("crop_id")));
                variety.setMaxDuration(rs.getInt("max_duration"));
                variety.setMinDuration(rs.getInt("min_duration"));
                variety.setIrrigatedYieldRate(rs.getDouble("rainfed_yield_rate"));
                variety.setRainfedYieldRate(rs.getDouble("irrigated_yield_rate"));
                variety.setSeedSource(SeedSourceUtil.getSeedSourceByID(rs.getInt("seed_source_id")));
                varieties.add(variety);
            }
        } catch (Exception e) {
            CropException ce = new CropException();
            ce.setCode(1003);
            ce.setMsg("Exception occured while access season_table-" + e.toString());
            throw ce;
        }
        return varieties;
    }
    public static List<Object> getCropVarities(int cropID) throws CropException {
        List<Object> varieties = new LinkedList<Object>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/wikicrop", "root", "Dontgiveup97!");
            String query = "Select * from variety_table where crop_id = ?";
            PreparedStatement stmt = con.prepareStatement(query);
            stmt.setInt(1,cropID);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Variety variety = new Variety();
                variety.setVarietyID(rs.getInt("variety_id"));
                variety.setVarietyName(rs.getString("variety_name"));
                variety.setCropName(CropUtil.getCrop(rs.getInt("crop_id")));
                variety.setMaxDuration(rs.getInt("max_duration"));
                variety.setMinDuration(rs.getInt("min_duration"));
                variety.setIrrigatedYieldRate(rs.getDouble("rainfed_yield_rate"));
                variety.setRainfedYieldRate(rs.getDouble("irrigated_yield_rate"));
                variety.setSeedSource(SeedSourceUtil.getSeedSourceByID(rs.getInt("seed_source_id")));
                varieties.add(variety);
            }
        } catch (Exception e) {
            CropException ce = new CropException();
            ce.setCode(1003);
            ce.setMsg("Exception occured while access season_table-" + e.toString());
            throw ce;
        }
        return varieties;
    }
    public static List<Integer> getCropVarietyList(int cropID,int districtID,int month) throws Exception
    {
        List<Integer> varietyIDList = new LinkedList<Integer>();
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/wikicrop", "root", "Dontgiveup97!");
            String query = "select var_1,var_2,var_3,var_4,var_5,var_6,var_7,var_8,var_9,var_10,var_11,var_12,var_13,var_14,var_15,var_16 from crop_season_variety_table as csv inner join season_table as st on st.season_id = csv.season_id where  csv.crop_id = ? and st.starting_month > ? and csv.district_id = ?";
            PreparedStatement stmt = con.prepareStatement(query);
            stmt.setInt(1,cropID);
            stmt.setInt(2,month);
            stmt.setInt(3,districtID);
            ResultSet rs = stmt.executeQuery();
            while(rs.next())
            {
                if(isValid(varietyIDList,rs.getInt("var_1"))) varietyIDList.add(rs.getInt("var_1"));
                if(isValid(varietyIDList,rs.getInt("var_2"))) varietyIDList.add(rs.getInt("var_2"));
                if(isValid(varietyIDList,rs.getInt("var_3"))) varietyIDList.add(rs.getInt("var_3"));
                if(isValid(varietyIDList,rs.getInt("var_4"))) varietyIDList.add(rs.getInt("var_4"));
                if(isValid(varietyIDList,rs.getInt("var_5"))) varietyIDList.add(rs.getInt("var_5"));
                if(isValid(varietyIDList,rs.getInt("var_6"))) varietyIDList.add(rs.getInt("var_6"));
                if(isValid(varietyIDList,rs.getInt("var_7"))) varietyIDList.add(rs.getInt("var_7"));
                if(isValid(varietyIDList,rs.getInt("var_8"))) varietyIDList.add(rs.getInt("var_8"));
                if(isValid(varietyIDList,rs.getInt("var_9"))) varietyIDList.add(rs.getInt("var_9"));
                if(isValid(varietyIDList,rs.getInt("var_10"))) varietyIDList.add(rs.getInt("var_10"));
                if(isValid(varietyIDList,rs.getInt("var_11"))) varietyIDList.add(rs.getInt("var_11"));
                if(isValid(varietyIDList,rs.getInt("var_12"))) varietyIDList.add(rs.getInt("var_12"));
                if(isValid(varietyIDList,rs.getInt("var_13"))) varietyIDList.add(rs.getInt("var_13"));
                if(isValid(varietyIDList,rs.getInt("var_14"))) varietyIDList.add(rs.getInt("var_14"));
                if(isValid(varietyIDList,rs.getInt("var_15"))) varietyIDList.add(rs.getInt("var_15"));
                if(isValid(varietyIDList,rs.getInt("var_16"))) varietyIDList.add(rs.getInt("var_16"));
            }
        }
        catch (Exception e)
        {
            throw e;
        }
        return varietyIDList;
    }
    public  static  List<Integer> getCropVariety(int cropID,String districtName,int month) throws Exception
    {
        List<Integer> varietyIDList = getCropVarietyList(cropID,CropUtil.getDistrictID(districtName),month);
        List<Integer> varietyIDs = new LinkedList<Integer>();
        Integer rainfedVarietyID = null;
        Integer irrigatedVarietyID = null;
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/wikicrop", "root", "Dontgiveup97!");
            double maxRainfedYield = 0.0;
            double maxIrrigateYield = 0.0;
            for(Integer varietyID:varietyIDList)
            {
                String query = "select * from variety_table where variety_id = ?";
                PreparedStatement stmt = con.prepareStatement(query);
                stmt.setInt(1,varietyID);
                ResultSet rs = stmt.executeQuery();
                while (rs.next())
                {
                      Double irrigatedYield = rs.getDouble("irrigated_yield_rate");
                      Double rainfedYield = rs.getDouble("rainfed_yield_rate");
                      if(irrigatedYield > maxIrrigateYield)
                      {
                          irrigatedVarietyID = rs.getInt("variety_id");
                          maxIrrigateYield = irrigatedYield;
                      }
                      if(rainfedYield > maxRainfedYield)
                      {
                          rainfedVarietyID = rs.getInt("variety_id");
                          maxRainfedYield = rainfedYield;
                      }
                }
            }
        }
        catch (Exception e)
        {

        }
        varietyIDs.add(rainfedVarietyID);
        varietyIDs.add(irrigatedVarietyID);
        return varietyIDs;
    }
    public static boolean isNotZero(int id)
    {
        if(id != 0)
        {
            return  true;
        }
        return  false;
    }
    public static boolean isNotExist(List<Integer> IDList,Integer ID)
    {
        if(!IDList.contains(ID))
        {
            return true;
        }
        return false;
    }
    public static boolean isValid(List<Integer> IDList,Integer ID)
    {
        if(isNotExist(IDList,ID) && isNotZero(ID))
        {
            return true;
        }
        return false;
    }
    public static void main(String args[]) throws Exception
    {
        getCropVarities(1);
    }
}

