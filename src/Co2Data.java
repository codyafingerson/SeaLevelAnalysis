public class Co2Data {
    /*
      CSV Structure: Entity,Code,Day,average_co2_concentrations,trend_co2_concentrations
     */

    private final Date date; // Store the date
    private final Double averageConcentrations; // Store the average co2 concentration

    public Co2Data(String[] data) {
        this.date = new Date(data[2]);
        this.averageConcentrations = Double.parseDouble(data[3]);
    }

    /**
     * Get the current date of the Co2 concentration
     * @return the date
     */
    public Date getDate() {
        return this.date;
    }

    /**
     * Get the average concentration
     * @return the average concentration as a double
     */
    public Double getAverageConcentrations() {
        return this.averageConcentrations;
    }
}
