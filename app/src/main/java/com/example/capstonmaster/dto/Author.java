package com.example.capstonmaster.dto;

import java.io.Serializable;

public class Author implements Serializable {

  private Long id;
  private String name;
  private String email;
  private String studentId;
  private String imgSrc;
  private String password;

  public String getImgSrc() {
    return imgSrc;
  }

  public void setImgSrc(String imgSrc) {
    this.imgSrc = imgSrc;
  }

  public Author(Long id, String name, String email, String studentId, String imgSrc) {
    this.id = id;
    this.name = name;
    this.email = email;
    this.studentId = studentId;
    this.imgSrc = imgSrc;
  }

  public Author(Long id, String name, String email, String studentId) {
    this.id = id;
    this.name = name;
    this.email = email;
    this.studentId = studentId;
  }



  public Author(String email, String name, String password, String studentId) {
    this.name = name;
    this.email = email;
    this.studentId = studentId;
    this.password = password;
  }

//  public Author(String email, String password, String name) {
//      this.id = id;
//      this.name = name;
//      this.email = email;
//      this.studentId = studentId;
//    }

//  public Author(Long id, String name, String email, String studentId) {
//    this.id = id;
//    this.name = name;
//    this.email = email;
//    this.studentId = studentId;
//  }

  public void setId(Long id) {
    this.id = id;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public void setStudentId(String studentId) {
    this.studentId = studentId;
  }

  public Long getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public String getEmail() {
    return email;
  }

  public String getStudentId() {
    return studentId;
  }

  @Override
  public String toString() {
    return "Author{" +
      "id=" + id +
      ", name='" + name + '\'' +
      ", email='" + email + '\'' +
      ", studentId='" + studentId + '\'' +
      '}';
  }
}
