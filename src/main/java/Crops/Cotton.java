package Crops;

import NeuralNetwork.CropNeuralNetwork;
import src.Crop;
import utils.CropUtil;

public class Cotton extends Crop {
    private static double maxYieldRate = 4600;
    private static double minYieldRate = 0;

    @Override
    public  double getMaxYieldRate() {
        return maxYieldRate;
    }

    @Override
    public  double getMinYieldRate() {
        return minYieldRate;
    }
    public static int  predictYieldRate(CropNeuralNetwork cnn) throws  Exception
    {
        return CropUtil.predictYieldRate(cnn,new Cotton());
    }


}
