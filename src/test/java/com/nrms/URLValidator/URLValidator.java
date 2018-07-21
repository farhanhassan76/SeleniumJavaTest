package com.nrms.URLValidator;

import java.net.HttpURLConnection;
import java.net.URL;

//checkng page connection

public class URLValidator {
	
	public boolean checkIfURLExists(String targetUrl) {
        HttpURLConnection httpUrlConn;
        try {
            httpUrlConn = (HttpURLConnection) new URL(targetUrl).openConnection();
            httpUrlConn.setRequestMethod("GET");
            httpUrlConn.setConnectTimeout(30000);
            httpUrlConn.setReadTimeout(30000);
            return (httpUrlConn.getResponseCode() == HttpURLConnection.HTTP_OK);
        } catch (Exception e) {
            System.out.println("Error: in acessing  "+targetUrl + e.getMessage());
            return false;
        }
    }

}
