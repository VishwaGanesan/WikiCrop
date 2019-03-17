package Authentication;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import src.Response;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LoginServlet extends HttpServlet {
    public void doGet(HttpServletRequest req, HttpServletResponse res)
    {
        Response response = new Response();
        try
        {
            res.setContentType("application/json");
            res.setCharacterEncoding("UTF-8");
            String mobileNumber = req.getParameter("mobile_no");
            String otp = req.getParameter("otp");
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/wikicrop", "root", "Dontgiveup97!");
            String query = "Select * from customer_table where mobile_no = ?";
            PreparedStatement stmt = con.prepareStatement(query);
            stmt.setString(1,mobileNumber);
            ResultSet rs = stmt.executeQuery();
            if(!rs.next())
            {
                res.setStatus(400);
                response.setCode(400);
                response.setMsg("Customer doesn't Exist");
            }
            else
            {
                JsonNode otpObj = OtpGeneration.validateOtp(otp,rs.getString("session_id"));
                String status = otpObj.get("Status").textValue();
                if(status.equals("Success"))
                {
                    String sessionID = otpObj.get("Details").textValue();
                    HttpSession session = req.getSession();
                    session.setAttribute("mobile", mobileNumber);
                    session.setAttribute("sessionID",sessionID);
                    res.setStatus(200);
                    response.setCode(200);
                    response.setMsg("Customer login successfully");
                }
                else
                {
                    res.setStatus(400);
                    response.setCode(400);
                    response.setMsg(otpObj.get("Details").textValue());
                }
            }
        }
        catch (Exception e)
        {
            res.setStatus(400);
            response.setCode(400);
           response.setMsg("Exception occured while perform login:"+e.toString());
        }
        try {
            ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
            String json = ow.writeValueAsString(response);
            res.getWriter().write(json);
        }
        catch (Exception e)
        {
            res.setHeader("Error","IOException");
        }
    }
}

