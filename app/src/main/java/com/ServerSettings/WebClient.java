package com.ServerSettings;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;


public class WebClient {
    private static URL url;
    private static URLConnection getConnection;
    private static String response;
    private static InputStream inputStream;
    private static int ch = 0;
    private static String msg = "";

    public static String GetContent(String urlStr) {
        System.out.println("urlStr = " + urlStr);
        try {
            url = new URL(urlStr);
            getConnection = url.openConnection();
            inputStream = getConnection.getInputStream();
            while ((ch = inputStream.read()) != -1) {
                msg += (char) ch;
            }
            response = msg.trim();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;

    }
}
