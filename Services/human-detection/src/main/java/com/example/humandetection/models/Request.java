package com.example.humandetection.models;

import java.util.List;

public class Request {

  private Image image;
  private List<Features> features;

  public Request(Image image, List<Features> features) {
    this.image = image;
    this.features = features;
  }

  public Image getImage() {
    return image;
  }

  public void setImage(Image image) {
    this.image = image;
  }

  public List<Features> getFeatures() {
    return features;
  }

  public void setFeatures(List<Features> features) {
    this.features = features;
  }
}
