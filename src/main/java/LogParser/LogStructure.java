package LogParser;

import java.util.HashMap;

class LogStructure {
    private static HashMap<String, String> logString = new HashMap<>();

    static void createMap(String[] array) {
        logString.put("timeStamp", array[0]);
        logString.put("elapsed", replaceInt(array[1]));
        logString.put("label", validLabel(replaceString(array[2])));
        logString.put("responseCode", replaceString(array[3]));
        logString.put("responseMessage", replaceString(array[4]));
        logString.put("success", array[7]);
        logString.put("failureMessage", replaceString(array[8]));
        logString.put("bytes", replaceInt(array[9]));
        logString.put("sentBytes", replaceInt(array[10]));
        logString.put("grpThreads", array[11]);
        logString.put("allThreads", array[12]);
        logString.put("URL", replaceString(array[13]));
        logString.put("Latency", replaceInt(array[14]));
        logString.put("IdleTime", replaceInt(array[15]));
        logString.put("Connect", replaceInt(array[16]));
        logString.put("failure", "0");
        createFailureMessage();
    }

    private static String replaceString(String s) {
        return s.replaceAll(" ", "\\\\ ").replaceAll("\"", "");
    }

    private static String replaceInt(String s) {
        if (s.equals("null") || s.isEmpty()) s = "0";
        return s;
    }

    private static String validLabel(String s) {
        if (s.length() == 0) return "Label\\\\ does`t\\\\ exist";
        else return s;
    }

    private static void createFailureMessage() {
        if (!getFailureMessage().equals("") || getSuccess().equals("false")) {
            setFailureMessage("responseMessage=\"" + getFailureMessage() + getResponseMessage() + "\",");
            setSuccess("false");
            setFailure("1");
            if (getResponseCode().equals("")) setResponseCode("520");
        } else {
            setSuccess("true");
            setFailureMessage("");
            if (getResponseCode().equals("")) setResponseCode("200");
        }
    }

    static String getTimeStamp() {
        return logString.get("timeStamp");
    }

    static String getElapsed() {
        return logString.get("elapsed");
    }

    static String getLabel() {
        return logString.get("label");
    }

    static String getResponseCode() {
        return logString.get("responseCode");
    }

    static String getResponseMessage() {
        return logString.get("responseMessage");
    }

    static String getSuccess() {
        return logString.get("success");
    }

    static String getFailureMessage() {
        return logString.get("failureMessage");
    }

    static String getBytes() {
        return logString.get("bytes");
    }

    static String getSentBytes() {
        return logString.get("sentBytes");
    }

    static String getGrpThreads() {
        return logString.get("grpThreads");
    }

    static String getAllThreads() {
        return logString.get("allThreads");
    }

    static String getURL() {
        return logString.get("URL");
    }

    static String getLatency() {
        return logString.get("Latency");
    }

    static String getIdleTime() {
        return logString.get("IdleTime");
    }

    static String getConnect() {
        return logString.get("Connect");
    }

    static String getFailure() {
        return logString.get("failure");
    }

    static void setFailureMessage(String editString) {
        logString.put("failureMessage", editString);
    }

    static void setSuccess(String editString) {
        logString.put("success", editString);
    }

    static void setFailure(String editString) {
        logString.put("failure", editString);
    }

    static void setResponseCode(String editString) {
        logString.put("responseCode", editString);
    }

}
