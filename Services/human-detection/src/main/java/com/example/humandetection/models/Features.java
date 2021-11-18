package com.example.humandetection.models;

public class Features {

  private Integer maxResults;
  private String type;

  public Features(Integer maxResults, String type) {
    this.maxResults = maxResults;
    this.type = type;
  }

  public Integer getMaxResults() {
    return maxResults;
  }

  public void setMaxResults(Integer maxResults) {
    this.maxResults = maxResults;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }
}
