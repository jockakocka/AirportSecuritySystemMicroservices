package com.example.humandetection.models;

import java.util.List;

public class FinalRequest {

  private List<Request> requests;

  public FinalRequest(List<Request> requests) {
    this.requests = requests;
  }

  public List<Request> getRequests() {
    return requests;
  }

  public void setRequests(List<Request> requests) {
    this.requests = requests;
  }
}
