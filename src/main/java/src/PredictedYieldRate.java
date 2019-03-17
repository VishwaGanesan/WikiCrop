package src;

import java.util.HashMap;
import java.util.Map;

public class PredictedYieldRate {
    Map<String,Double> yieldRate = new HashMap<String,Double>();

    public void setYieldRate(Map<String, Double> yieldRate) {
        this.yieldRate = yieldRate;
    }

    public Map<String, Double> getYieldRate() {
        return yieldRate;
    }
}
