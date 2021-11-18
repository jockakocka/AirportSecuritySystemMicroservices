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
public class SectionService {

  public static String baseUrl = "http://section/persons";


  public String getPersonsInATimeFrame(Map<String, String> params) throws IOException {
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

  public void postPerson(SectionPerson sectionPerson) throws IOException {
    URL url = new URL(baseUrl);
    String type = "application/json";
    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
    connection.setRequestMethod("POST");
    connection.setRequestProperty("Content-Type", type);
    connection.setDoOutput(true);
    OutputStream os = connection.getOutputStream();
    OutputStreamWriter osw = new OutputStreamWriter(os, StandardCharsets.UTF_8);
    osw.write(new Gson().toJson(sectionPerson));
    osw.flush();
    osw.close();
    connection.connect();
    int responseCode = connection.getResponseCode();
    System.out.println("Response Code for POST request: " + responseCode);
    if(responseCode == HttpURLConnection.HTTP_OK){
      System.out.println("The request is successful.");
    }
    else{
      System.out.println("The request was not successful and has the response code: " + responseCode);
    }

  }

}
