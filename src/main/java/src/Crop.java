package src;

import java.util.List;
import java.util.Map;

public class Crop {
    private int cropID;
    private String cropName;
    private int soilID;
    private double maxYieldRate;
    private double minYieldRate;
    private List<String> location;
    private List<Season> seasons;
    private List<Variety> varieties;
    private Map<String, Map<Season,List<Variety>>> seasonVarietyList;

    public int getCropID() {
        return cropID;
    }

    public String getCropName() {
        return cropName;
    }

    public int getSoilID() {
        return soilID;
    }

    public double getMaxYieldRate() {
        return maxYieldRate;
    }

    public double getMinYieldRate() {
        return minYieldRate;
    }

    public List<String> getLocation() {
        return location;
    }

    public List<Season> getSeasons() {
        return seasons;
    }

    public List<Variety> getVarieties() {
        return varieties;
    }

    public Map<String, Map<Season, List<Variety>>> getSeasonVarietyList() {
        return seasonVarietyList;
    }

    public void setCropID(int cropID) {
        this.cropID = cropID;
    }

    public void setCropName(String cropName) {
        this.cropName = cropName;
    }

    public void setSoilID(int soilID) {
        this.soilID = soilID;
    }

    public void setMaxYieldRate(double maxYieldRate) {
        this.maxYieldRate = maxYieldRate;
    }

    public void setMinYieldRate(double minYieldRate) {
        this.minYieldRate = minYieldRate;
    }

    public void setLocation(List<String> location) {
        this.location = location;
    }

    public void setSeasons(List<Season> seasons) {
        this.seasons = seasons;
    }

    public void setVarieties(List<Variety> varieties) {
        this.varieties = varieties;
    }

    public void setSeasonVarietyList(Map<String, Map<Season, List<Variety>>> seasonVarietyList) {
        this.seasonVarietyList = seasonVarietyList;
    }
}
