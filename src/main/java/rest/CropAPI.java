package rest;

import NeuralNetwork.CropNeuralNetwork;
import src.CropVarietySelection;
import src.Response;
import src.Variety;
import utils.CropUtil;
import utils.VarietyUtil;

import javax.annotation.PostConstruct;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;


@Path("/crop")
public class CropAPI {
    @GET
    @Path("{cropID}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCropByID(@PathParam("cropID") Integer cropID)
    {
        Response rs = new Response();
        rs.setCode(cropID);
        return rs;
    }
    @GET
    @Path("{cropID}/varities")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCropVarities(@PathParam("cropID") int cropID)
    {
        Response rs = new Response();
        try {
            List<Object> varieties = VarietyUtil.getCropVarities(cropID);
            rs.setCode(1203);
            rs.setMsg("success");
            rs.setResponseObjs(varieties);
        }
        catch (Exception e)
        {
            rs.setMsg("Failure");
            rs.setResponseObj(e);
        }
        return rs;
    }
    @GET
    @Path("{cropID}/varities/{varietyID}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCropVarietyByID(@PathParam("cropID") int cropID,@PathParam("varietyID") int varietyID) throws Exception
    {
        Response rs = new Response();
        Object variety = VarietyUtil.getVarietyByID(varietyID);
        rs.setCode(1204);
        rs.setMsg("success");
        rs.setResponseObj(variety);
        return rs;
    }

    @POST
    @Path("/cvsm")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response cropVarietySelectionMethod(CropVarietySelection cvs) throws Exception
    {
        Response rs = new Response();
        List<Object> varieties = CropUtil.CropVarietySelectionMethod(cvs);
        rs.setCode(1001);
        rs.setMsg("success");
        rs.setResponseObjs(varieties);
        return rs;
    }
    @GET
    @Path("/test")
    @Produces(MediaType.APPLICATION_JSON)
    public CropVarietySelection test() throws Exception
    {
        return  new CropVarietySelection();
    }

}
