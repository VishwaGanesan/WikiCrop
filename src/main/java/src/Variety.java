package src;

import java.util.List;

public class Variety {
    private int varietyID;
    private String varietyName;
    private String cropName;
    private int minDuration;
    private int maxDuration;
    private double irrigatedYieldRate;
    private double rainfedYieldRate;
    private SeedSource seedSource;

    public void setVarietyID(int varietyID) {
        this.varietyID = varietyID;
    }

    public void setVarietyName(String varietyName) {
        this.varietyName = varietyName;
    }

    public void setCropName(String cropName) {
        this.cropName = cropName;
    }

    public void setMinDuration(int minDuration) {
        this.minDuration = minDuration;
    }

    public void setMaxDuration(int maxDuration) {
        this.maxDuration = maxDuration;
    }

    public void setIrrigatedYieldRate(double irrigatedYieldRate) {
        this.irrigatedYieldRate = irrigatedYieldRate;
    }

    public void setRainfedYieldRate(double rainfedYieldRate) {
        this.rainfedYieldRate = rainfedYieldRate;
    }

    public void setSeedSource(SeedSource seedSource) {
        this.seedSource = seedSource;
    }

    public int getVarietyID() {
        return varietyID;
    }

    public String getVarietyName() {
        return varietyName;
    }

    public String getCropName() {
        return cropName;
    }

    public int getMinDuration() {
        return minDuration;
    }

    public int getMaxDuration() {
        return maxDuration;
    }

    public double getIrrigatedYieldRate() {
        return irrigatedYieldRate;
    }

    public double getRainfedYieldRate() {
        return rainfedYieldRate;
    }

    public SeedSource getSeedSource() {
        return seedSource;
    }
}
