import RedBlackTrees.Co2Tree;
import RedBlackTrees.SeaLevelTree;
import RedBlackTrees.TemperatureAnomTree;

import java.io.*;
import java.util.Scanner;

public class Driver {
    public static void main(String[] args) throws IOException {
        // Create output file to write data too
        Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("output.txt"), "utf-8"));

        // Instantiate data structures
        Co2Tree<Date, Co2Data> co2DateTree = new Co2Tree<>();
        Co2Tree<Double, Co2Data> co2Tree = new Co2Tree<>();
        SeaLevelTree<Double, SeaLevelData> seaLevelTree = new SeaLevelTree<>();
        SeaLevelTree<Date, SeaLevelData> seaLevelDateTree = new SeaLevelTree<>();
        TemperatureAnomTree<Date, TemperatureAnomalyData> tempAnomDateTree = new TemperatureAnomTree<>();
        TemperatureAnomTree<Double, TemperatureAnomalyData> tempAnomTree = new TemperatureAnomTree<>();

        // Instantiate data attribute classes for storing and handling data
        Co2Data co2Data = null;
        SeaLevelData seaLevelData = null;
        TemperatureAnomalyData temperatureAnomalyData = null;

        // Instantiate data files
        File co2File = new File("data/co2.csv");
        File seaLevelFile = new File("data/sea_level.csv");
        File tempAnomFile = new File("data/temperature_anomaly.csv");

        // Read the data files for dates
        Scanner co2DateScn = new Scanner(co2File);
        Scanner seaLevelDateScn = new Scanner(seaLevelFile);
        Scanner temperatureAnomalyDateScn = new Scanner(tempAnomFile);

        // Read the data files for climate info
        Scanner co2InfoScn = new Scanner(co2File);
        Scanner seaLevelInfoScn = new Scanner(seaLevelFile);
        Scanner temperatureAnomaly2InfoScn = new Scanner(tempAnomFile);

        // Get rid of csv headers
        co2DateScn.nextLine();
        seaLevelDateScn.nextLine();
        temperatureAnomalyDateScn.nextLine();
        co2InfoScn.nextLine();
        seaLevelInfoScn.nextLine();
        temperatureAnomaly2InfoScn.nextLine();
        /**************************************/

        // Capture dates for tree
        while(temperatureAnomalyDateScn.hasNext()) {
            String[] lines = temperatureAnomalyDateScn.nextLine().split(","); // Read line by line
            if(lines[0].equals("World")) { // Ensure only World data is read and not Northern/Southern hemispheres
                temperatureAnomalyData = new TemperatureAnomalyData(lines); // Create a new TemperatureAnomalyData object with new line
                tempAnomDateTree.put(temperatureAnomalyData.getDate(), temperatureAnomalyData); // Put the date and the data into the first tree
            }
        }

        while(temperatureAnomaly2InfoScn.hasNext()) {
            String[] lines = temperatureAnomaly2InfoScn.nextLine().split(",");
            if(lines[0].equals("World")) { // En sure only World data is read
                temperatureAnomalyData = new TemperatureAnomalyData(lines); // Create a new TemperatureAnomalyData object with new line
                tempAnomTree.put(temperatureAnomalyData.getTempAnomalyInF(), temperatureAnomalyData); // Store the temp anomaly in F as the key of the tree for later comparison
            }
        }

        while(co2DateScn.hasNext()) {
            String[] lines = co2DateScn.nextLine().split(","); // Split CSV file to read each column
            co2Data = new Co2Data(lines); // Create new Co2Data object
            co2DateTree.put(co2Data.getDate(), co2Data); // Store the date in the tree as the key
        }

        while(co2InfoScn.hasNext()) {
            String[] lines = co2InfoScn.nextLine().split(",");
            co2Data = new Co2Data(lines);
            co2Tree.put(co2Data.getAverageConcentrations(), co2Data); // Store the average co2 concentration as the key
        }

        while(seaLevelDateScn.hasNext()) {
            String[] lines = seaLevelDateScn.nextLine().split(","); // Split CSV file to read each column
            seaLevelData = new SeaLevelData(lines); // Create a new SeaLevelData object
            seaLevelDateTree.put(seaLevelData.getDate(), seaLevelData); // Store the date of the sealevel as the key
        }

        while(seaLevelInfoScn.hasNext()) {
            String[] lines = seaLevelInfoScn.nextLine().split(","); // Split CSV file to read each column
            seaLevelData = new SeaLevelData(lines); // Create new SeaLevelData object
            seaLevelTree.put(seaLevelData.getSeaLevelRiseAverage(), seaLevelData); // Store the rise average as the key
        }

        // Variables to hold min and max values frim the trees
        TemperatureAnomalyData tempAnomMax = tempAnomTree.get(tempAnomTree.max());
        TemperatureAnomalyData tempAnomMin = tempAnomTree.get(tempAnomTree.min());
        Co2Data co2Max = co2Tree.get(co2Tree.max());
        Co2Data co2Min = co2Tree.get(co2Tree.min());
        SeaLevelData seaLevelMax = seaLevelTree.get(seaLevelTree.max());
        SeaLevelData seaLevelMin = seaLevelTree.get(seaLevelTree.min());

        if(tempAnomDateTree.get(tempAnomMin.getDate()) != null){
            TemperatureAnomalyData temp = tempAnomDateTree.get(tempAnomMin.getDate()); // Get the date of the lowest temp anomaly occurrence
            writer.write("Lowest Temperature anomaly (F): " + String.format("%.2f", temp.getTempAnomalyInF()) + " on " + temp.getDate().getFullDateString() + "\n"); // Write to file
        }

        writer.write("\n");
        if(tempAnomDateTree.get(tempAnomMax.getDate()) != null) {
            TemperatureAnomalyData tempTempAnom = tempAnomDateTree.get(tempAnomMax.getDate()); // Obtain the date of the maximum temp anomaly occurrence
            Co2Data tempCo2 = co2DateTree.get(tempAnomMax.getDate()); // Get the date of the co2 occurrence that matches the above date.
            writer.write("Highest Temperature anomaly (F): " + String.format("%.2f", tempTempAnom.getTempAnomalyInF()) + " on " + tempTempAnom.getDate().getFullDateString()+ "\n");
            writer.write("On that same date, the Average Co2 concentration was " + tempCo2.getAverageConcentrations()+ "\n");
        }

        writer.write("\n");
        if(seaLevelDateTree.get(seaLevelMin.getDate()) != null) {
            SeaLevelData seaLevelTempData = seaLevelDateTree.get(seaLevelMin.getDate()); // Get the date of the lowest sea level rise
            TemperatureAnomalyData temperatureAnomalyTempData = tempAnomDateTree.get(seaLevelMin.getDate()); // Get the temperature anomaly of the date above
            writer.write("Lowest Sea Level Rise: " + seaLevelTempData.getSeaLevelRiseAverage() + " on " + seaLevelTempData.getDate().getFullDateString()+ "\n");
            writer.write("On that same date, the temperature anomaly (F) was " + String.format("%.2f" ,temperatureAnomalyTempData.getTempAnomalyInF())+ "\n");
        }

        writer.write("\n");
        if(seaLevelDateTree.get(seaLevelMax.getDate()) != null) {
            SeaLevelData seaLevelTemp = seaLevelDateTree.get(seaLevelMax.getDate()); // Obtain the max seaLevelRise date
            TemperatureAnomalyData tempTempAnom = tempAnomDateTree.get(seaLevelMax.getDate()); // Obtain the tempurature anomaly of the maximum sea level rise
            Co2Data tempCo2 = co2DateTree.get(seaLevelMax.getDate()); // Obtain the co2 concentration for the max seaLevelRise date
            writer.write("Highest Sea Level Rise: " + seaLevelTemp.getSeaLevelRiseAverage() + " on " + seaLevelTemp.getDate().getFullDateString()+ "\n");
            writer.write("On that same date, the Temperature Anomaly (F) was " + String.format("%.2f", tempTempAnom.getTempAnomalyInF()) + "\n");
            writer.write("On that same date, the Average Co2 concentration was " + tempCo2.getAverageConcentrations()+ "\n");
        }

        writer.write("\n");
        if(co2DateTree.get(co2Min.getDate()) != null) {
            Co2Data tempCo2 = co2DateTree.get(co2Min.getDate()); // Get the minimum co2 date
            TemperatureAnomalyData tempTempAnom = tempAnomDateTree.get(co2Min.getDate()); // Get the temperature anomaly of the date of the lowest co2 concentration
            writer.write("Lowest Average Co2 concentration: " + tempCo2.getAverageConcentrations() + " on " + tempCo2.getDate().getFullDateString() + "\n");
            writer.write("On that same date, the Temperature Anomaly (F) was " + String.format("%.2f", tempTempAnom.getTempAnomalyInF())+ "\n");
        }

        writer.write("\n");
        if(co2DateTree.get(co2Max.getDate()) != null) {
            Co2Data tempCo2 = co2DateTree.get(co2Max.getDate()); // Get the date of the maximum co2 concentration
            TemperatureAnomalyData tempTempAnom = tempAnomDateTree.get(co2Max.getDate()); // Get the temperature anomaly on the date that the maximum co2 concentration occurred.
            writer.write("Highest Average Co2 concentration: " + tempCo2.getAverageConcentrations() + " on " + tempCo2.getDate().getFullDateString() + "\n");
            writer.write("On that same date, the Temperature Anomaly (F) was " + String.format("%.2f", tempTempAnom.getTempAnomalyInF()));
        }

        // Close writer and all scanners
        co2DateScn.close();
        seaLevelDateScn.close();
        temperatureAnomalyDateScn.close();
        co2InfoScn.close();
        seaLevelInfoScn.close();
        temperatureAnomaly2InfoScn.close();
        writer.close();

        // Confirm successful completion of program
        System.out.println("Wrote data successfully to output.txt");
    }
}