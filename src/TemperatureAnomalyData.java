public class TemperatureAnomalyData {
    /*
        CSV Structure: Entity,Code,Day,temperature_anomaly
     */
    private Date date;
    private Double tempAnomaly;
    private String region;

    public TemperatureAnomalyData(String[] data) {
        this.region = data[0];
        this.tempAnomaly = Double.parseDouble(data[3]);
        this.date = new Date(data[2]);

    }

    /**
     * The region of the temperature anomaly
     * @return the region
     */
    public String getRegion() {
        return this.region;
    }

    /**
     * The date of the temperature anomaly
     * @return the date
     */
    public Date getDate() {
        return this.date;
    }

    /**
     * The temperature anomaly of the region (given in fahrenheit)
     * Converts celsius value to fahrenheit
     * @return the temperature anomaly in fahrenheit
     */
    public Double getTempAnomalyInF() {
        return (this.tempAnomaly * 1.8);
    }
}
