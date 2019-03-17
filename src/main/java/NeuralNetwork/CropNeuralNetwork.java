package NeuralNetwork;

public class CropNeuralNetwork {
    private Double soilNitogen;
    private Double soilPhosphorous;
    private Double soilPhotassium;
    private Double Rainfall;
    private Double Humidity;
    private Double Temperature;

    public Double getSoilNitogen() {
        return soilNitogen;
    }

    public Double getSoilPhosphorous() {
        return soilPhosphorous;
    }

    public Double getSoilPhotassium() {
        return soilPhotassium;
    }

    public Double getRainfall() {
        return Rainfall;
    }

    public Double getHumidity() {
        return Humidity;
    }

    public Double getTemperature() {
        return Temperature;
    }

    public void setSoilNitogen(Double soilNitogen) {
        this.soilNitogen = soilNitogen;
    }

    public void setSoilPhosphorous(Double soilPhosphorous) {
        this.soilPhosphorous = soilPhosphorous;
    }

    public void setSoilPhotassium(Double soilPhotassium) {
        this.soilPhotassium = soilPhotassium;
    }

    public void setRainfall(Double rainfall) {
        Rainfall = rainfall;
    }

    public void setHumidity(Double humidity) {
        Humidity = humidity;
    }

    public void setTemperature(Double temperature) {
        Temperature = temperature;
    }
}
