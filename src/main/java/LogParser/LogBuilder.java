package LogParser;

import Property.MyProperty;
import static LogParser.LogStructure.*;

class LogBuilder {
    static String build(String[] array, int endTime) {
        String measurement = MyProperty.getMeasurement();
        createMap(array);
        String timestamp0 = String.format("%06d", endTime);

        String result = measurement
                + ",label=" + getLabel()
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
}