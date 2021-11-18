package com.example.collectormicroservice.domains;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class KnownPerson {

  private String timestamp;
  private Integer section;
  private String event;
  private List<Person> persons;
  private String destination;
  private String image;
  @SerializedName(value = "extra-info")
  private String extraInfo;

  public KnownPerson(String timestamp, Integer section, String event, List<Person> persons, String destination, String image, String extraInfo) {
    this.timestamp = timestamp;
    this.section = section;
    this.event = event;
    this.persons = persons;
    this.destination = destination;
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

  public List<Person> getPersons() {
    return persons;
  }

  public void setPersons(List<Person> persons) {
    this.persons = persons;
  }

  public String getDestination() {
    return destination;
  }

  public void setDestination(String destination) {
    this.destination = destination;
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
