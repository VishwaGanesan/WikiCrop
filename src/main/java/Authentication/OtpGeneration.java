package Authentication;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.TextNode;
import src.Login;
import src.Otp;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class OtpGeneration {
    public static Otp getOtp(String mobile) throws  Exception {
        Otp otp = new Otp();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/wikicrop", "root", "Dontgiveup97!");
            String query = "select * from customer_table where mobile_no = ?";
            PreparedStatement stmt = con.prepareStatement(query);
            stmt.setString(1,mobile);
            ResultSet rs = stmt.executeQuery();
            if(!rs.next())
            {
                otp.setMsg("Failure");
                otp.setDetails("Customer doesn't have an account!");
                otp.setMobileNo(mobile);
                return otp;
            }
            String urlStr = "http://2factor.in/API/V1/d82f364f-44c8-11e9-8806-0200cd936041/SMS/" + mobile + "/AUTOGEN";
            URL url = new URL(urlStr);
            HttpURLConnection urlCon = (HttpURLConnection) url.openConnection();
            urlCon.setRequestMethod("GET");
            urlCon.setConnectTimeout(5000);
            urlCon.setReadTimeout(5000);
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(urlCon.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            ObjectMapper mapper = new ObjectMapper();
            JsonNode otpObj = mapper.readTree(response.toString());
            String status = otpObj.get("Status").textValue();
            if (true || status.equals("Success"))
            {
                String sessionID = otpObj.get("Details").textValue();
                query = "update  customer_table set session_id = ? where mobile_no = ?";
                Connection con1 = DriverManager.getConnection("jdbc:mysql://localhost:3306/wikicrop", "root", "Dontgiveup97!");
                PreparedStatement stmt1 = con1.prepareStatement(query);
                stmt1.setString(1,sessionID);
                stmt1.setString(2,mobile);
                stmt1.executeUpdate();
                otp.setMobileNo(mobile);
                otp.setMsg("success");
                otp.setDetails("otp generated successfully");
            }
            else
            {
                otp.setMsg("Failure");
                otp.setDetails(otpObj.get("Details").textValue());
            }

        }
        catch (Exception e)
        {
                throw e;
        }
            return otp;
    }
    public static JsonNode validateOtp(String otp,String sessionID) throws Exception
    {
        String urlStr = "http://2factor.in/API/V1/d82f364f-44c8-11e9-8806-0200cd936042/SMS/VERIFY/" + sessionID +"/"+otp;
        URL url = new URL(urlStr);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        con.setConnectTimeout(5000);
        con.setReadTimeout(5000);
        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        ObjectMapper mapper = new ObjectMapper();
        JsonNode otpObj = mapper.readTree(response.toString());
        return otpObj;
    }
    public static  void main(String args[]) throws Exception
    {
        getOtp("8531947247");
    }
}
