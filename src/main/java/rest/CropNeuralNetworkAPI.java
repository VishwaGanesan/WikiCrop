package rest;

import NeuralNetwork.CropNeuralNetwork;
import NeuralNetwork.CropNeuralNetworkImp;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import src.Response;

@Path("/network")
public class CropNeuralNetworkAPI {
    @GET
    @Path("/train")
    @Produces(MediaType.APPLICATION_JSON)
    public static Response trainModel() throws  Exception
    {
        return CropNeuralNetworkImp.trainModel();
    }

    @POST
    @Path("/calculate")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public static Response calculateYieldRate(CropNeuralNetwork cnn) throws Exception
    {
        Object resObj = CropNeuralNetworkImp.calculateYieldRate(cnn);
        if(resObj != null)
        {
            return  Response.getResponse(0,"success",resObj);
        }
        return Response.getResponse(-1,"failure",resObj);
    }

}
