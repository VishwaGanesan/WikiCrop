package NeuralNetwork;

import Crops.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.neuroph.core.NeuralNetwork;
import org.neuroph.core.data.DataSet;
import org.neuroph.core.data.DataSetRow;
import org.neuroph.nnet.MultiLayerPerceptron;
import org.neuroph.util.TransferFunctionType;
import src.PredictedYieldRate;
import src.Response;

import java.sql.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class CropNeuralNetworkImp {
        public static Response trainModel() throws Exception
        {
            String responseMsg;
            DataSet ds = new DataSet(6,1);
            DataSet ds1 = new DataSet(6,1);
            DataSet ds2 = new DataSet(6,1);
            DataSet ds3 = new DataSet(6,1);
            DataSet ds4 = new DataSet(6,1);
            DataSet ds5 = new DataSet(6,1);
            DataSet ds6 = new DataSet(6,1);
            try {
                Class.forName("com.mysql.jdbc.Driver");
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/wikicrop", "root", "Dontgiveup97!");
                String query  = "Select * from training_data";
               Statement stmt = con.createStatement();
               ResultSet rs = stmt.executeQuery(query);
               int count = 0;
               while(rs.next())
               {
                   Double nitrogen = rs.getDouble("soil_nitrogen");
                   Double phosphorus = rs.getDouble("soil_phosporous");
                   Double photosium = rs.getDouble("soil_photosium");
                   Double rainfall = rs.getDouble("rainfall");
                   Double temperature = rs.getDouble("temperature");
                   Double humidity = rs.getDouble("humidity");
                   Double yiedlrate = rs.getDouble("yieldrate");
                   if(count >= 0 && count <= 50) {
                       ds1.addRow(new DataSetRow(new double[]{nitrogen, phosphorus, photosium, rainfall, temperature, humidity}, new double[]{yiedlrate}));
                   }
                   else if(count >= 51 && count <= 100)
                   {
                       ds2.addRow(new DataSetRow(new double[]{nitrogen, phosphorus, photosium, rainfall, temperature, humidity}, new double[]{yiedlrate}));

                   }
                   else if(count >= 101 && count <= 150)
                   {
                       ds3.addRow(new DataSetRow(new double[]{nitrogen, phosphorus, photosium, rainfall, temperature, humidity}, new double[]{yiedlrate}));
                   }
                   else if(count >= 151 && count <= 200)
                   {
                       ds4.addRow(new DataSetRow(new double[]{nitrogen, phosphorus, photosium, rainfall, temperature, humidity}, new double[]{yiedlrate}));
                   }
                   else if(count >= 201 && count <= 250)
                   {
                       ds5.addRow(new DataSetRow(new double[]{nitrogen, phosphorus, photosium, rainfall, temperature, humidity}, new double[]{yiedlrate}));
                   }
                   else
                   {
                       ds6.addRow(new DataSetRow(new double[]{nitrogen, phosphorus, photosium, rainfall, temperature, humidity}, new double[]{yiedlrate}));
                   }
                   ds.addRow(new DataSetRow(new double[]{nitrogen, phosphorus, photosium, rainfall, temperature, humidity}, new double[]{yiedlrate}));
                   count += 1;
               }
               DataSet[] dsArr = new DataSet[]{ds,ds1,ds2,ds3,ds4,ds5,ds6};
               responseMsg = trainModel(dsArr);
            }
            catch (Exception e)
            {
                return Response.getResponse(e.toString());
            }
               return Response.getResponse(responseMsg);
        }
        public static String trainModel (DataSet[] dsArr) throws Exception
        {

            MultiLayerPerceptron myMlPerceptron = new MultiLayerPerceptron(TransferFunctionType.SIGMOID, 6,5, 1);
            for (DataSet ds:dsArr) {
                ds.shuffle();
            }
            myMlPerceptron.learn(dsArr[1]);
            System.out.println("batch 1 completed");

            myMlPerceptron.learn(dsArr[2]);
            System.out.println("batch 2 completed");

            myMlPerceptron.learn(dsArr[3]);
            System.out.println("batch 3  completed");

            myMlPerceptron.learn(dsArr[4]);
            System.out.println("batch 4 completed");

            myMlPerceptron.learn(dsArr[5]);
            System.out.println("batch 5 completed");

            myMlPerceptron.learn(dsArr[6]);
            System.out.println("batch 6 completed");


            DataSet ds = dsArr[0];
            System.out.println("Testing trained neural network");
            testNeuralNetwork(myMlPerceptron, ds);

            myMlPerceptron.save("myMlPerceptron.nnet");

            NeuralNetwork loadedMlPerceptron = NeuralNetwork.createFromFile("myMlPerceptron.nnet");

            testNeuralNetwork(loadedMlPerceptron, ds);
            return "Neural Network trained and tested Successfully";
        }


        public static void testNeuralNetwork(NeuralNetwork nnet, DataSet testSet) {

            for (DataSetRow dataRow : testSet.getRows()) {
                nnet.setInput(dataRow.getInput());
                nnet.calculate();
                double[] networkOutput = nnet.getOutput();
                System.out.print("Input: " + Arrays.toString(dataRow.getInput()));
                System.out.println(" Output: " +  networkOutput[0]);
            }
        }
        public static Object calculateYieldRate(CropNeuralNetwork cnn) throws Exception
        {
            PredictedYieldRate yieldRate = new PredictedYieldRate();
            Map<String,Double> yiedlRateMap = new HashMap<String,Double>();
            double rice = Rice.predictYieldRate(cnn);
            double ragi = Ragi.predictYieldRate(cnn);
            double maize = Maize.predictYieldRate(cnn);
            double sugarcane = Sugarcane.predictYieldRate(cnn);
            double groundnut = Groundnut.predictYieldRate(cnn);
            double gingelly = Gingelly.predictYieldRate(cnn);
            double cotton = Cotton.predictYieldRate(cnn);
            double cumbu = Cumbu.predictYieldRate(cnn);
            double cholam = Cholam.predictYieldRate(cnn);
            yiedlRateMap.put("Rice",rice);
            yiedlRateMap.put("Ragi",ragi);
            yiedlRateMap.put("Maize",maize);
            yiedlRateMap.put("Sugarcane",sugarcane);
            yiedlRateMap.put("Groundnut",groundnut);
            yiedlRateMap.put("Gingelly",gingelly);
            yiedlRateMap.put("Cotton",cotton);
            yiedlRateMap.put("Cumbu",cumbu);
            yiedlRateMap.put("Cholam",cholam);
            yieldRate.setYieldRate(yiedlRateMap);
            return yieldRate;
        }

}

