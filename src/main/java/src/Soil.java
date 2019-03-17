package src;

import java.util.List;

public class Soil {
        private int soilID;
        private String soilName;
        private List<String> suitableCrops;

    public void setSoilID(int soilID) {
        this.soilID = soilID;
    }

    public void setSoilName(String soilName) {
        this.soilName = soilName;
    }

    public void setSuitableCrops(List<String> suitableCrops) {
        this.suitableCrops = suitableCrops;
    }

    public int getSoilID() {
        return soilID;
    }

    public String getSoilName() {
        return soilName;
    }

    public List<String> getSuitableCrops() {
        return suitableCrops;
    }
}
