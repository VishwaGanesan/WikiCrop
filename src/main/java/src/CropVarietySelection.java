package src;

import NeuralNetwork.CropNeuralNetwork;

public class CropVarietySelection {
    private int soilType;
    private CropNeuralNetwork cnn;
    private String location;
    private int startingMonth;

    public void setLocation(String location) {
        this.location = location;
    }

    public void setStartingMonth(int startingMonth) {
        this.startingMonth = startingMonth;
    }

    public String getLocation() {
        return location;
    }

    public int getStartingMonth() {
        return startingMonth;
    }

    public void setSoilType(int soilType) {
        this.soilType = soilType;
    }

    public void setCnn(CropNeuralNetwork cnn) {
        this.cnn = cnn;
    }

    public int getSoilType() {
        return soilType;
    }

    public CropNeuralNetwork getCnn() {
        return cnn;
    }
}
