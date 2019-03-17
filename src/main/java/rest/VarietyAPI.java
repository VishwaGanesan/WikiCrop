package rest;

import src.Response;
import utils.VarietyUtil;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/variety")
public class VarietyAPI {
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getVarieties()
    {
        Response rs = new Response();
        try {
            List<Object> varities = VarietyUtil.getVarities();
            rs.setCode(1302);
            rs.setResponseObjs(varities);
            return rs;
        }
        catch (Exception e)
        {
            rs.setCode(5001);
            rs.setResponseObj(e);
        }
        return rs;
    }
    @GET
    @Path("{variety_id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getVarietyByID(@PathParam("variety_id") int varietyID)throws Exception
    {
        Object variety = VarietyUtil.getVarietyByID(varietyID);
        Response rs = new Response();
        rs.setCode(1303);
        rs.setMsg("Success");
        rs.setResponseObj(variety);
        return rs;
    }


}
