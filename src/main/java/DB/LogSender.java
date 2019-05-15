package DB;

import Property.MyProperty;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;


public class LogSender {
    private static HttpURLConnection con;
    private static boolean isEnd = false;

    public static void send(StringBuilder res, boolean isEnd) {
        LogSender.isEnd = isEnd;
        String url = MyProperty.getHost();
        try {
            byte[] postData = res.toString().getBytes();
            URL myURL = new URL(url);
            con = (HttpURLConnection) myURL.openConnection();
            con.setDoOutput(true);
            con.setRequestMethod("POST");
            con.setRequestProperty("User-Agent", "Java client");
            con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

            try (DataOutputStream wr = new DataOutputStream(con.getOutputStream())) {
                wr.write(postData);
            }

            if (con.getResponseCode() == 400) { printBadResponse(con); }
            else { printGoodResponse(con); }
        }
        catch (IOException e) { e.printStackTrace(); }
        finally { con.disconnect(); }
    }
    private static void printGoodResponse(HttpURLConnection con) {
        StringBuilder content = new StringBuilder();
        try(BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append(System.lineSeparator());
            }
            if (isEnd) {
                System.out.println(content.toString());
            }
        }
        catch (IOException e) { e.printStackTrace(); }
    }
    private static void printBadResponse(HttpURLConnection con) {
        StringBuilder content = new StringBuilder();
        try(BufferedReader reader = new BufferedReader(new InputStreamReader(con.getErrorStream()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append(System.lineSeparator());
            }
            System.out.println(content.toString());
        }
        catch (IOException e) { e.printStackTrace(); }
    }
}