package com.example.cpanelservice.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class KnownPersonBody {

  private String timestamp;
  private Integer section;
  private String event;
  @SerializedName(value = "known-persons")
  private List<KnownPerson> knownPersons;
  private String image;
  @SerializedName(value = "extra-info")
  private String extraInfo;

  public KnownPersonBody (){
    this.knownPersons = new ArrayList<>();
  }

  public KnownPersonBody(String timestamp, Integer section, String event, List<KnownPerson> knownPersons, String image, String extraInfo) {
    this.timestamp = timestamp;
    this.section = section;
    this.event = event;
    this.knownPersons = knownPersons;
    this.image = image;
    this.extraInfo = extraInfo;
  }

  public String getTimestamp() {
    return timestamp;
  }

  public void setTimestamp(String timestamp) {
    this.timestamp = timestamp;
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

  public List<KnownPerson> getKnownPersons() {
    return knownPersons;
  }

  public void setKnownPersons(List<KnownPerson> knownPersons) {
    this.knownPersons = knownPersons;
  }

  public String getImage() {
    return image;
  }

  public void setImage(String image) {
    this.image = image;
  }

  public String getExtraInfo() {
    return extraInfo;
  }

  public void setExtraInfo(String extraInfo) {
    this.extraInfo = extraInfo;
  }
}
