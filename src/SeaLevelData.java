public class SeaLevelData {
    /*
    CSV Structure: Entity,Code,Day,sea_level_rise_average,sea_level_rise_cw2011,sea_level_rise_uhslcfd
     */

    private final Date date;
    private final Double seaLevelRiseAverage;

    /**
     * Create a new SeaLevelData object
     * @param data data array
     */
    public SeaLevelData(String[] data) {
        this.date = new Date(data[2]);
        this.seaLevelRiseAverage = Double.parseDouble(data[3]);
    }

    /**
     * Returns the date of the sea level rise average
     * @return date object
     */
    public Date getDate() {
        return this.date;
    }

    /**
     * Returns the sea level rise average
     * @return the sea level rise average
     */
    public Double getSeaLevelRiseAverage() {
        return this.seaLevelRiseAverage;
    }
}
