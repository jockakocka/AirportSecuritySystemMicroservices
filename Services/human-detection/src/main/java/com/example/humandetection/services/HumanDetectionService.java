package com.example.humandetection.services;

import com.example.humandetection.models.*;
import com.example.humandetection.models.Frame;
import com.google.api.Http;
import com.google.gson.Gson;

import org.springframework.stereotype.Service;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@Service
public class HumanDetectionService {


  public static final String googleCloudUrl = "https://vision.googleapis.com/v1/images:annotate?key=AIzaSyAKhdAbNSHnl9FcWua0nTcI4N5a9HCW270";
  public static final String faceUrl = "http://face-recognition";

  public String postFrameToGCV(Frame frame) throws IOException {
    URL url = new URL(googleCloudUrl);
    String imageJson= frame.getImage();
    List<Request> requests = new ArrayList<>();
    com.example.humandetection.models.Image image=new com.example.humandetection.models.Image(imageJson);
    Integer maxResults = 10;
    String type = "OBJECT_LOCALIZATION";
    Features feature = new Features(maxResults,type);
    List<Features> features = new ArrayList<>();
    features.add(feature);
    Request request = new Request(image, features);
    requests.add(request);
    FinalRequest finalRequest = new FinalRequest(requests);
    int personCount=0;
    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
    connection.setRequestMethod("POST");
    connection.setRequestProperty("Content-Type", "application/json");
    connection.setDoOutput(true);
    connection.connect();
    OutputStream os = connection.getOutputStream();
    OutputStreamWriter osw = new OutputStreamWriter(os, StandardCharsets.UTF_8);
    osw.write(new Gson().toJson(finalRequest));
    osw.flush();
    osw.close();
    int responseCode = connection.getResponseCode();
    System.out.println("Response Code for POST request: " + responseCode);
    if(responseCode == HttpURLConnection.HTTP_OK){
      System.out.println("The request is successful.");
      InputStream inputStream = new BufferedInputStream(connection.getInputStream());
      BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
      String line;
      StringBuilder stringBuilder = new StringBuilder();
      while((line = bufferedReader.readLine()) != null){
        stringBuilder.append(line);
        if(line.contains("Person")){
          personCount++;
        }
      }
      String responseString = stringBuilder.toString();
      System.out.println("Response body received from Google Cloud Vision: \n" + responseString);
      if(personCount > 0){
        URL faceRecognitionUrl = new URL(faceUrl+"/frame");
        HttpURLConnection httpURLConnection = (HttpURLConnection) faceRecognitionUrl.openConnection();
        httpURLConnection.setRequestMethod("POST");
        httpURLConnection.setRequestProperty("Content-Type", "application/json");
        httpURLConnection.setDoOutput(true);
        httpURLConnection.connect();
        OutputStream outputStream = httpURLConnection.getOutputStream();
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream, StandardCharsets.UTF_8);
        outputStreamWriter.write(new Gson().toJson(frame));
        outputStreamWriter.flush();
        outputStreamWriter.close();
        int responseCodeFace = httpURLConnection.getResponseCode();
        if(responseCodeFace==HttpURLConnection.HTTP_OK || responseCodeFace==HttpURLConnection.HTTP_NO_CONTENT
           || responseCodeFace==HttpURLConnection.HTTP_ACCEPTED || responseCodeFace==HttpURLConnection.HTTP_CREATED){
          System.out.println("Success response from frame sent to Face Recognition service: " + responseCodeFace);
        }
        else {
          System.out.println("Unsuccessful response code from frame sent to Face Recognition service: " + responseCodeFace);
        }
      }
      frame.setPersonCount(personCount);
      if(frame.getDestination()!=null){
        URL forwardDestination = new URL(frame.getDestination());
        HttpURLConnection urlConnection = (HttpURLConnection) forwardDestination.openConnection();
        urlConnection.setRequestMethod("POST");
        urlConnection.setRequestProperty("Content-Type", "application/json");
        urlConnection.setDoOutput(true);
        urlConnection.connect();
        OutputStream outputStreamForward = urlConnection.getOutputStream();
        OutputStreamWriter outputStreamWriterForward = new OutputStreamWriter(outputStreamForward, StandardCharsets.UTF_8);
        outputStreamWriterForward.write(new Gson().toJson(frame));
        outputStreamWriterForward.flush();
        outputStreamWriterForward.close();
        int forwardResponseCode = urlConnection.getResponseCode();
        return "Forward to destination URL response code: " + forwardResponseCode;
      }
      else {
        return "Frame response body  with added person count field:\n" + new Gson().toJson(frame);
      }
    }
    else{
      return "The request sent to Google Cloud Vision API was unsuccessful with has the response code: " + responseCode;
    }
  }

}
