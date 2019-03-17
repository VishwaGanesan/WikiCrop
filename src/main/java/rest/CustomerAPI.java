package rest;

import src.Customer;
import src.Response;
import utils.CustomerUtil;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/customer")
public class CustomerAPI {
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response editCustomer(Customer c) throws Exception
    {
        Response rs = new Response();
        if(CustomerUtil.isAccountExist(c.getMobileNo()))
        {
            rs.setCode(1003);
            rs.setMsg("Customer Already Exist");
            rs.setResponseObj(c);
        }
        rs.setResponseObj(CustomerUtil.createCustomer(c));

        return rs;
    }
}
