# Sea Level Data Analysis Tool

This simple Java program is designed to analyze and extract valuable information from three different datasets: temperature anomalies, sea level rise, and CO2 concentrations. The program employs Red-Black Trees to efficiently store and manage this data. Each dataset is organized into a separate Red-Black Tree, resulting in three trees if using a combined key of temperature anomaly, sea level rise, and CO2 concentration, or six trees if using a key based on date. Example output can be found in [output.txt](output.txt)

# Data Sources
The program reads data from three separate files:

1. [Temperature Anomaly](./data/temperature_anomaly.csv): This file contains temperature anomaly data, representing deviations from the average global temperature. Only the worldwide temperature anomaly data is used for analysis.

2. [Sea Level Rise](./data/sea_level.csv): This file contains data related to sea level rise.

3. [CO2 Concentrations](./data/co2.csv): This file contains data on CO2 concentrations.

# Data Attribution
The data structures used in this program are either custom implementations or based on existing ones. Proper citations and references to the sources of these data structures are included in the code comments, ensuring compliance with intellectual property and licensing requirements.