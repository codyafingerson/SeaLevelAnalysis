public class Date implements Comparable<Date> {
    private final String date;

    /**
     * Create a new Date object
     * @param date the date string
     */
    public Date(String date) {
        this.date = date;
    }

    /**
     * Format the string and get the proper month.
     * @return a date string in format the format of January 1 1900
     */
    public String getFullDateString() {
        String finalDate = "";
        String[] date = this.date.split("-");
        if(date[0].startsWith("18")){
            finalDate = determineMonth(date[1]) + " " + date[2] +  ", " + date[0];
        } else {
            date = this.date.split("/");
            finalDate = determineMonth(date[0]) + " " + date[1] + ", " + date[2];
        }
        return finalDate;
    }

    // Determine the month based on the month number
    private String determineMonth(String x) {
        String finalMonth = "";
        String[] months = {"", "January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
        int monthNum = Integer.parseInt(x);
        for (String s : months) {
            if (s.equals(months[monthNum])) {
                finalMonth = months[monthNum];
                break;
            }
        }
        return finalMonth;
    }

    /**
     * Compare date values
     * @param compare the object to be compared.
     * @return 1 if object given is greater, -1 if object given is lesser, & 0 if object given is equal
     */
    public int compareTo(Date compare) {
        if(this.date.compareTo(compare.date) > 0) {
            return 1;
        }
        if(this.date.compareTo(compare.date) < 0) {
            return -1;
        }
        return 0;
    }
}

