package com.example.cpanelservice.models;

public class Person {

  private String age;
  private String gender;

  public Person(String age, String gender) {
    this.age = age;
    this.gender = gender;
  }

  public String getAge() {
    return age;
  }

  public void setAge(String age) {
    this.age = age;
  }

  public String getGender() {
    return gender;
  }

  public void setGender(String gender) {
    this.gender = gender;
  }

}
