package LogParser;

import Property.MyProperty;
import static LogParser.LogStructure.*;

class LogBuilder {
    private static final String INFLUX_TAG_SPECIAL_CHARACTERS_REGEXP = "(,|=| )";

    static String build(String[] array, int endTime) {

        String measurement = MyProperty.getMeasurement();
        createMap(array);
        String timestamp0 = String.format("%06d", endTime);

        String result = measurement
                + ",label=" + escapeSpecialCharacterForTag(getLabel())
                + ",responseCode=" + getResponseCode()
                + ",success=" + getSuccess()
                + " " + getFailureMessage()
                + "responseTime=" + getElapsed()
                + ",failure=" + getFailure()
                + ",bytes=" + getBytes()
                + ",sentBytes=" + getSentBytes()
                + ",grpThreads=" + getGrpThreads()
                + ",allThreads=" + getAllThreads()
                + ",URL=\"" + getURL()
                + "\",latency=" + getLatency()
                + ",idleTime=" + getIdleTime()
                + ",connect=" + getConnect()
                + " " + getTimeStamp()
                + timestamp0
                + "\n";

        return result;
    }

    private static String escapeSpecialCharacterForTag(String tag) {
        return tag.replaceAll(INFLUX_TAG_SPECIAL_CHARACTERS_REGEXP, "\\\\$1");
    }
}