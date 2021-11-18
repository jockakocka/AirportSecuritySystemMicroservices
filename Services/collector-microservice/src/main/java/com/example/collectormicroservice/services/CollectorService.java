package com.example.collectormicroservice.services;

import com.example.collectormicroservice.domains.*;
import com.google.gson.Gson;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

@Service
public class CollectorService {

  public static final String imageAnalysisUrl = "http://image-analysis";
  public static final String sectionUrl = "http://section";
  public static final String alertUrl = "http://alert";
  public static final String faceAnalysisUrl = "http://face-recognition";
  public static final String humanDetectionUrl = "http://human-detection:31600";

  public void createAFrame(Frame frame) throws IOException {

    URL humanUrl = new URL(humanDetectionUrl+"/human-detection/frame");
    HttpURLConnection httpURLConnection = (HttpURLConnection) humanUrl.openConnection();
    httpURLConnection.setRequestMethod("POST");
    httpURLConnection.setRequestProperty("Content-Type", "application/json");
    httpURLConnection.setDoOutput(true);
    httpURLConnection.connect();
    OutputStream outputStreamHuman = httpURLConnection.getOutputStream();
    OutputStreamWriter outputStreamWriterHuman = new OutputStreamWriter(outputStreamHuman, StandardCharsets.UTF_8);
    outputStreamWriterHuman.write(new Gson().toJson(frame));
    outputStreamWriterHuman.flush();
    outputStreamWriterHuman.close();
    int responseCodeHumanDetection = httpURLConnection.getResponseCode();

    if(frame.getDestination()==null || frame.getDestination().equals("")){

        URL url = new URL(imageAnalysisUrl+"/frame");
        URL urlSection = new URL(sectionUrl+"/persons");
        frame.setDestination(null);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setDoOutput(true);
        connection.connect();
        OutputStream os = connection.getOutputStream();
        OutputStreamWriter osw = new OutputStreamWriter(os, StandardCharsets.UTF_8);
        osw.write(new Gson().toJson(frame));
        osw.flush();
        osw.close();
        int responseCode = connection.getResponseCode();
        System.out.println("Response Code for POST request: " + responseCode);
        StringBuilder stringBuilder = new StringBuilder();
        if(responseCode == HttpURLConnection.HTTP_OK){
          System.out.println("The request is successful.");
          InputStream inputStream = new BufferedInputStream(connection.getInputStream());
          BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
          String line;
          //mora dobar format da se isfrlat tie dve cifri i tockata
          while((line = bufferedReader.readLine()) != null){
              stringBuilder.append(line);
          }
          String responseString = stringBuilder.toString();
          bufferedReader.close();
          HttpURLConnection sectionConn = (HttpURLConnection) urlSection.openConnection();
          sectionConn.setRequestMethod("POST");
          sectionConn.setRequestProperty("Content-Type", "application/json");
          sectionConn.setDoOutput(true);
          sectionConn.connect();
          OutputStream outputStream = sectionConn.getOutputStream();
          OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream, StandardCharsets.UTF_8);
          outputStreamWriter.write(responseString);
          outputStreamWriter.flush();
          outputStreamWriter.close();
          int responseSectionCode = sectionConn.getResponseCode();
          System.out.println("Response Code for POST section request: " + responseSectionCode);
        }
        else{
          System.out.println("The request was not successful and has the response code: " + responseCode);
        }
    }
    else {
      if(frame.getDestination().contains("face-recognition")){
        URL faceUrl = new URL(faceAnalysisUrl+"/frame");
        URL alert = new URL(alertUrl+"/alerts");
        frame.setDestination(null);
        HttpURLConnection faceCon = (HttpURLConnection) faceUrl.openConnection();
        faceCon.setRequestMethod("POST");
        faceCon.setRequestProperty("Content-Type", "application/json");
        faceCon.setDoOutput(true);
        faceCon.connect();
        OutputStream os = faceCon.getOutputStream();
        OutputStreamWriter osw = new OutputStreamWriter(os, StandardCharsets.UTF_8);
        osw.write(new Gson().toJson(frame));
        osw.flush();
        osw.close();
        int responseCode = faceCon.getResponseCode();
        System.out.println("Response Code for POST request: " + responseCode);
        StringBuilder stringBuilder = new StringBuilder();
        if(responseCode == HttpURLConnection.HTTP_OK){
          System.out.println("The request is successful.");
          InputStream inputStream = new BufferedInputStream(faceCon.getInputStream());
          BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
          String line;
          while((line = bufferedReader.readLine()) != null){
            stringBuilder.append(line);
          }
          String responseString = stringBuilder.toString();
          if(responseString.contains("name")){
            HttpURLConnection alertCon = (HttpURLConnection) alert.openConnection();
            alertCon.setRequestMethod("POST");
            alertCon.setRequestProperty("Content-Type", "application/json");
            alertCon.setDoOutput(true);
            alertCon.connect();
            bufferedReader.close();
            OutputStream outputStream = alertCon.getOutputStream();
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream, StandardCharsets.UTF_8);
            outputStreamWriter.write(responseString);
            outputStreamWriter.flush();
            outputStreamWriter.close();
            int responseSectionCode = alertCon.getResponseCode();
            System.out.println("Response Code for POST section request: " + responseSectionCode);
          }
        }
        else{
          System.out.println("The request was not successful and has the response code: " + responseCode);
        }
      }
    }

  }
}
