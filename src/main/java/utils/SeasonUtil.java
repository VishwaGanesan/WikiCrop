package utils;

import src.CropException;
import src.Season;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class SeasonUtil {
    public static  Object getSeasonByID(int seasonID) throws CropException
    {
        Season season = new Season();
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/wikicrop", "root", "Dontgiveup97!");
            String query = "Select * from season_table where season_id = ?";
            PreparedStatement stmt = con.prepareStatement(query);
            stmt.setInt(1,seasonID);
            ResultSet rs = stmt.executeQuery();
            while(rs.next())
            {
                season.setSeasonID(rs.getInt("season_id"));
                season.setStartMonth(CropUtil.getMonth(rs.getInt("starting_month")));
                season.setEndMonth(CropUtil.getMonth(rs.getInt("ending_month")));
                season.setSeasonName(rs.getString("season_name"));
            }
        }
        catch (Exception e)
        {
            CropException ce = new CropException();
            ce.setCode(1003);
            ce.setMsg("Exception occured while access season_table-"+e.toString());
            throw ce;
        }
        return season;
    }
    public static List<Object> getSeasons() throws Exception
    {
        List<Object> seasons = new LinkedList<Object>();
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/wikicrop", "root", "Dontgiveup97!");
            String query = "Select * from season_table";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while(rs.next())
            {
                Season s = new Season();
                s.setSeasonID(rs.getInt("season_id"));
                s.setStartMonth(CropUtil.getMonth(rs.getInt("starting_month")));
                s.setEndMonth(CropUtil.getMonth(rs.getInt("ending_month")));
                s.setSeasonName(rs.getString("season_name"));
                seasons.add(s);
            }
        }
        catch (Exception e)
        {
            CropException ce = new CropException();
            ce.setCode(1003);
            ce.setMsg("Exception occured while access season_table-"+e.toString());
            throw  ce;
        }
        return seasons;
    }
}
