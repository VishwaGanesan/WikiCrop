package utils;

import NeuralNetwork.CropNeuralNetwork;
import org.neuroph.core.NeuralNetwork;
import org.neuroph.core.data.DataSet;
import org.neuroph.core.data.DataSetRow;
import src.CropException;
import NeuralNetwork.CropNeuralNetworkImp;
import src.CropVarietySelection;

import java.lang.reflect.Method;
import java.util.*;

public class CropUtil {
    private static double maxRainFall = 1542.3;
    private static double maxNitrogen = 280;
    private static double maxPhosoporous = 22;
    private static double maxPhotosium = 280;
    private static double maxTemperature = 39;
    private static double maxHumidity = 80;

    private static double minRainFall = 655.1;
    private static double minNitrogen = 200;
    private static double minPhosoporous = 11;
    private static double minPhotosium = 118;
    private static double minTemperature = 12;
    private static double minHumidity = 41;

    public static String[] crops =  {"","Rice","Ragi","Maize","Sugarcane","Groundnut","Cotton","Gingelly","Cumbu","Cholam"};
    public static String[] districts ={"Ariyalur","Coimbatore","Cuddalore","Dharmapuri","Dindigul","Erode","Kancheepuram","Kanniyakumari","Karur","Krishnagiri","Madurai",
            "Nagapattinam","Namakkal","Perambalur","Pudukkottai","Ramanathapuram","Salem","Sivaganga","Thanjavur","The Nilgiris","Theni","Thiruvallur","Thiruvarur","Thoothukkudi","Tiruchirappalli","Tirunelveli","Tiruppur","Tiruvannamalai","Vellore","Villupuram","Virudhunagar","Chennai"};

    public static double toNormalize(double param,double max,double min)
    {
        double diff = max - min;
        double result = (param - min)/diff;
        return result;
    }
    public static double deNoramalize(double param,double max,double min) throws Exception
    {
        double diff = max - min;
        double result = param * diff;
        result += min;
        return  result;
    }
    public static DataSet constructDataset(CropNeuralNetwork cnn) throws Exception
    {
        DataSet ds = new DataSet(6,1);
        double nitrogen = toNormalize(cnn.getSoilNitogen(),maxNitrogen,minNitrogen);
        double phosphorous = toNormalize(cnn.getSoilPhosphorous(),maxPhosoporous,minPhosoporous);
        double photosium = toNormalize(cnn.getSoilPhotassium(),maxPhotosium,minPhotosium);
        double rainfall = toNormalize(cnn.getRainfall(),maxRainFall,minRainFall);
        double temperature = toNormalize(cnn.getTemperature(),maxTemperature,minTemperature);
        double humidity = toNormalize(cnn.getHumidity(),maxHumidity,minHumidity);
        ds.addRow(new DataSetRow(new double[]{nitrogen,phosphorous,photosium,rainfall,temperature,humidity},new double[]{0.0}));
        return ds;
    }
    public static int  predictYieldRate(CropNeuralNetwork cnn,Object crop) throws  Exception
    {
        Method getMax = crop.getClass().getMethod("getMaxYieldRate");
        Method getMin = crop.getClass().getMethod("getMinYieldRate");
        DataSet ds = CropUtil.constructDataset(cnn);
        NeuralNetwork nnet = NeuralNetwork.createFromFile("myMlPerceptron.nnet");
        DataSetRow dataRow = ds.getRowAt(0);
        nnet.setInput(dataRow.getInput());
        nnet.calculate();
        double[] networkOutput = nnet.getOutput();
        System.out.print("Input: " + Arrays.toString(dataRow.getInput()));
        return (int)deNoramalize(networkOutput[0],(double) getMax.invoke(crop),(double) getMin.invoke(crop));
    }
    public static List<Object> CropVarietySelectionMethod(CropVarietySelection cvs) throws  Exception
    {
       List<Object> varities = new LinkedList<Object>();
       Object yieldRate  = CropNeuralNetworkImp.calculateYieldRate(cvs.getCnn());
       int soilID = cvs.getSoilType();
       Method getYieldRate = yieldRate.getClass().getMethod("getYieldRate");
       Map<String,Double> yieldRateMap = toQuintal((Map<String,Double>) getYieldRate.invoke(yieldRate));
       List<String> suitableCrops = SoilUtil.SuitableCrops(soilID);
       Iterator<Map.Entry<String,Double>> itr = yieldRateMap.entrySet().iterator();
       String districtName = cvs.getLocation();
       int startingMonth = cvs.getStartingMonth();
       Double max = 0.0;
       int highYieldCropID = 0;
       while(itr.hasNext())
       {
            Map.Entry<String,Double> entry = itr.next();
           String cropName = entry.getKey();
           Double yieldInQuintal = suitableCrops.contains(cropName)?yieldRateMap.get(cropName):0.0;
           Double rate = getMSP(getID(cropName));
           Double amount = yieldInQuintal*rate;
           if(amount > max)
           {
               max = amount;
               highYieldCropID = getID(cropName);
           }
        }
      List<Integer> varietyIDs = VarietyUtil.getCropVariety(highYieldCropID,districtName,startingMonth);
       if(varietyIDs.get(0) != null) {
           varities.add(VarietyUtil.getVarietyByID(varietyIDs.get(0)));
       }
       if(varietyIDs.get(1) != null) {
           varities.add(VarietyUtil.getVarietyByID(varietyIDs.get(1)));
       }
      return varities;
    }

    public static String getMonth(int i) throws CropException
    {
        String[] months={"January","Feburary","March","April","May","June","July","August","September","October","November","December"};
        if(i < 1 || i > 12)
        {
            CropException ce = new CropException();
            ce.setCode(1001);
            ce.setMsg("Invalid month Range");
            throw ce;
        }
        return months[i -1];
    }
    public static String getCrop(int i) throws CropException
    {
        if(i < 0 || i > crops.length)
        {
            CropException ce = new CropException();
            ce.setCode(1002);
            ce.setMsg("Invalid crop id");
            throw ce;
        }
        return crops[i];
    }
    public static int getID(String crop) throws Exception
    {
        return Arrays.asList(crops).indexOf(crop);
    }

    public static  String getDistrict(int districtID) throws  CropException
    {
        districtID -= 20100;
        if(districtID < 0 || districtID > 31)
        {
            CropException ce = new CropException();
            ce.setCode(1003);
            ce.setMsg("Invalid District ID");
            throw ce;
        }
        return districts[districtID];
    }
    public static int getDistrictID(String districtName) throws Exception
    {
        return Arrays.asList(districts).indexOf(districtName)+20100;
    }
    public  static Double getMSP(int cropID ) throws  Exception
    {
        Double[] msp = {0.0,1770.0,2897.0,1700.0,2750.0,1000.0,5150.0,6249.0,3062.0,2173.0};
        if(cropID < 1 || cropID > 9)
        {
            CropException ce = new CropException();
            ce.setCode(2011);
            ce.setMsg("Invalid crop ID");
            throw ce;
        }
        return msp[cropID];
    }

    public static Map<String,Double> toQuintal(Map<String,Double> yieldRate) throws Exception
    {
        Iterator<Map.Entry<String,Double>> itr = yieldRate.entrySet().iterator();
        while(itr.hasNext())
        {
            Map.Entry<String,Double> entry = itr.next();
            String key = entry.getKey();
            Double value = entry.getValue()*0.01;
            yieldRate.put(key,value);
        }
        return yieldRate;
    }
    public static void main(String args[]) throws Exception
    {
        CropVarietySelection cvs = new CropVarietySelection();
        CropNeuralNetwork cnn = new CropNeuralNetwork();
        cnn.setHumidity(45.0);
        cnn.setSoilNitogen(200.0);
        cnn.setRainfall(43.0);
        cnn.setSoilPhosphorous(20.0);
        cnn.setSoilPhotassium(20.1);
        cnn.setTemperature(35.0);
        cvs.setCnn(cnn);
        cvs.setLocation("Ariyalur");
        cvs.setSoilType(1901);
        cvs.setStartingMonth(7);
        CropVarietySelectionMethod(cvs);
    }
}
