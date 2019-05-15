package LogParser;

import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import Property.MyProperty;
import DB.LogSender;

public class LogParser {
    public static void read() {
        String logFile = MyProperty.getLogFile();
        int fileLimit = 1;
        int timeRandom = 0;
        try(BufferedReader reader = new BufferedReader(new FileReader(logFile), 2048)) {
            //noinspection unused
            String ignoreHeader = reader.readLine();
            StringBuilder result = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {

                if (line.contains("\"")) {
                    String s = "";
                    Pattern p = Pattern.compile("\".*?(,).*?\"");
                    Matcher m = p.matcher(line);
                    while (m.find()) s = m.group().replace(",", "");
                    line = line.replaceAll("\".*?(,).*?\"", s);
                }
                String[] array = line.split(",");
                result.append(LogBuilder.build(array, timeRandom));
                timeRandom++;
                fileLimit++;
                if (fileLimit == 5000) {
                    LogSender.send(result, false);
                    result.setLength(0);
                    fileLimit = 1;
                }
                if (timeRandom == 999999) timeRandom = 0;
            }
            LogSender.send(result, true);
        }
        catch (IOException e) { e.printStackTrace(); }
    }
}