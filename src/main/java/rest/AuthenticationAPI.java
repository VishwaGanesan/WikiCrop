package rest;

import Authentication.OtpGeneration;
import src.Customer;
import src.Otp;
import src.Response;
import utils.CustomerUtil;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/auth")
public class AuthenticationAPI {
    @POST
    @Path("/generateOtp")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response generateOtp(Otp otp) throws Exception
    {
        Object otpObj = OtpGeneration.getOtp(otp.getMobileNo());
        Response rs = new Response();
        rs.setResponseObj(otpObj);
        return rs;
    }
    @POST
    @Path("/signup")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response Signup(Customer customer) throws  Exception
    {
        Response rs = new Response();
        if(CustomerUtil.isAccountExist(customer.getMobileNo()))
        {
            rs.setCode(1003);
            rs.setMsg("Customer Already Exist");
            rs.setResponseObj(customer);
        }
        rs.setCode(200);
        rs.setMsg("Customer Created Successfully");
        rs.setResponseObj(CustomerUtil.createCustomer(customer));
        return rs;
    }

}
