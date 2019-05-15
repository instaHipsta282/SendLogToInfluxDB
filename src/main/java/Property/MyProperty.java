package Property;

import java.io.*;
import java.util.Properties;

public class MyProperty {
    private static final String confFileName = "config.properties";
    private static String host;
    private static String logFile;
    private static String measurement;

    public static void initProperty() {
        Properties property = new Properties();
        String line;
        String result = "";
        try(BufferedReader reader = new BufferedReader(new FileReader(confFileName))) {
            while ((line = reader.readLine()) != null) result += line + "\n";
            result = result.replaceAll("\\\\", "/");
        }
        catch (IOException e) { e.printStackTrace(); }
        try(FileInputStream inputStream = new FileInputStream(confFileName);
        BufferedWriter writer = new BufferedWriter(new FileWriter(confFileName, false))) {
            writer.write(result);
            writer.flush();
            property.load(inputStream);
        }
        catch (IOException e) { e.printStackTrace(); }

        host = property.getProperty("host");
        logFile = property.getProperty("logFile");
        measurement = property.getProperty("measurement");
    }
    public static String getHost() {
        return host;
    }
    public static String getLogFile() {
        return logFile;
    }
    public static String getMeasurement() {
        return measurement;
    }
}