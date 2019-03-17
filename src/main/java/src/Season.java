package src;

public class Season {
    private int seasonID;
    private String seasonName;
    private String startMonth;
    private String endMonth;

    public int getSeasonID() {
        return seasonID;
    }

    public String getSeasonName() {
        return seasonName;
    }

    public String getStartMonth() {
        return startMonth;
    }

    public String getEndMonth() {
        return endMonth;
    }

    public void setSeasonID(int seasonID) {
        this.seasonID = seasonID;
    }

    public void setSeasonName(String seasonName) {
        this.seasonName = seasonName;
    }

    public void setStartMonth(String startMonth) {
        this.startMonth = startMonth;
    }

    public void setEndMonth(String endMonth) {
        this.endMonth = endMonth;
    }
}
