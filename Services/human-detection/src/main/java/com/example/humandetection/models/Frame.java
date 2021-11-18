package com.example.humandetection.models;

import com.google.gson.annotations.SerializedName;

public class Frame {

  private String timestamp;
  private String image;
  private Integer section;
  private String event;
  private String destination;
  @SerializedName(value = "extra-info")
  private String extraInfo;
  @SerializedName(value = "person-count")
  private Integer personCount;

  public Frame(String timestamp, String image, Integer section, String event, String destination, String extraInfo, Integer personCount) {
    this.timestamp = timestamp;
    this.image = image;
    this.section = section;
    this.event = event;
    this.destination = destination;
    this.extraInfo = extraInfo;
    this.personCount = personCount;
  }

  public String getTimestamp() {
    return timestamp;
  }

  public void setTimestamp(String timestamp) {
    this.timestamp = timestamp;
  }

  public String getImage() {
    return image;
  }

  public void setImage(String image) {
    this.image = image;
  }

  public Integer getSection() {
    return section;
  }

  public void setSection(Integer section) {
    this.section = section;
  }

  public String getEvent() {
    return event;
  }

  public void setEvent(String event) {
    this.event = event;
  }

  public String getDestination() {
    return destination;
  }

  public void setDestination(String destination) {
    this.destination = destination;
  }

  public String getExtraInfo() {
    return extraInfo;
  }

  public void setExtraInfo(String extraInfo) {
    this.extraInfo = extraInfo;
  }

  public Integer getPersonCount() {
    return personCount;
  }

  public void setPersonCount(Integer personCount) {
    this.personCount = personCount;
  }
}
