package com.example.cpanelservice.services;

import com.example.cpanelservice.models.*;
import com.google.gson.Gson;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.Map;

@Service
public class AlertService {

  public static final String baseUrl = "http://alert/alerts";

  public void createNewAlert(KnownPersonBody knownPersonBody) throws IOException {
    URL urlAlert = new URL(baseUrl);
    String type = "application/json";
    HttpURLConnection connection = (HttpURLConnection) urlAlert.openConnection();
    connection.setRequestMethod("POST");
    connection.setRequestProperty("Content-Type", type);
    connection.setDoOutput(true);
    connection.connect();
    OutputStream os = connection.getOutputStream();
    OutputStreamWriter osw = new OutputStreamWriter(os, StandardCharsets.UTF_8);
    osw.write(new Gson().toJson(knownPersonBody));
    osw.flush();
    osw.close();
    int responseCode = connection.getResponseCode();
    System.out.println("Response Code for POST request: " + responseCode);
    if(responseCode == HttpURLConnection.HTTP_OK){
      System.out.println("The request was successful with response code: " + responseCode);
    }
    else{
      System.out.println("The request was not successful and has the response code: " + responseCode);
    }
  }

  public String getAlertsInAGivenTime(Map<String, String> params) throws IOException {
    Iterator iterator = params.keySet().iterator();
    boolean flag = true;
    String urlString="";
    while(iterator.hasNext()) {
      String key = iterator.next().toString();
      String value = params.get(key);
      if (flag) {
        urlString = "?" + key + "=" + value;
        flag = false;
      }
      urlString += "&" + key + "=" + value;
    }
    URL url = new URL(baseUrl+urlString);
    String type = "application/json";
    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
    connection.setRequestMethod("GET");
    connection.setRequestProperty("Content-Type", type);
    int responseCode = connection.getResponseCode();
    if(responseCode == HttpURLConnection.HTTP_OK){
      BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
      StringBuilder stringBuilder = new StringBuilder();
      String inputLine;
      while((inputLine = bufferedReader.readLine()) != null){
        stringBuilder.append(inputLine);
      }
      String json =  stringBuilder.toString();
      JSONObject jsonObject = new JSONObject(json);
      bufferedReader.close();
      return jsonObject.toString();
    }
    else {
      return "The request was not successful and has the response code: " + responseCode;
    }

  }

  public String getAlertByUuid(String uuid) throws IOException {
    URL urlAlert = new URL(baseUrl+"/"+uuid);
    String type = "application/json";
    HttpURLConnection connection = (HttpURLConnection) urlAlert.openConnection();
    connection.setRequestMethod("GET");
    connection.setRequestProperty("Content-Type", type);
    connection.setDoOutput(true);
    int responseCode = connection.getResponseCode();
    System.out.println("Response Code for GET request: " + responseCode);
    if(responseCode == HttpURLConnection.HTTP_OK){
      BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
      StringBuilder stringBuilder = new StringBuilder();
      String inputLine;
      while((inputLine = bufferedReader.readLine()) != null){
        stringBuilder.append(inputLine);
      }
      String json =  stringBuilder.toString();
      JSONObject jsonObject = new JSONObject(json);
      bufferedReader.close();
      return jsonObject.toString();
    }
    else {
      return "The request was not successful and has the response code: " + responseCode;
    }
  }

  public void deleteAlert(String uuid) throws IOException {
    URL urlDelete = new URL(baseUrl + "/" +uuid);
    HttpURLConnection connection = (HttpURLConnection) urlDelete.openConnection();
    connection.setRequestMethod("DELETE");
    connection.setDoOutput(true);
    connection.setRequestProperty("Content-Type", "application/json");
    connection.connect();
    int responseCode = connection.getResponseCode();
    if(responseCode == HttpURLConnection.HTTP_OK){
      System.out.println("The request was successful with response code: " + responseCode);
    }
    else {
      System.out.println("The request was unsuccessful with response code: " + responseCode);
    }

  }
}
