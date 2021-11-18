package com.example.cpanelservice.services;

import com.example.cpanelservice.models.*;
import com.google.gson.Gson;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

@Service
public class CollectorService {

  public static String collectorUrl="http://collector:31700";

  public void createFrame(Frame frame) throws IOException {
    URL url = new URL(collectorUrl+"/collector/frame");
    String type = "application/json";
    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
    connection.setRequestMethod("POST");
    connection.setRequestProperty("Content-Type", type);
    connection.setDoOutput(true);
    OutputStream os = connection.getOutputStream();
    OutputStreamWriter osw = new OutputStreamWriter(os, StandardCharsets.UTF_8);
    osw.write(new Gson().toJson(frame));
    osw.flush();
    osw.close();
    connection.connect();
    int responseCode = connection.getResponseCode();
    System.out.println("Response Code for POST request: " + responseCode);
    if(responseCode == HttpURLConnection.HTTP_OK){
      System.out.println("The request is successful: " + responseCode);
    }
    else{
      System.out.println("The request was not successful and has the response code: " + responseCode);
    }
  }

}
