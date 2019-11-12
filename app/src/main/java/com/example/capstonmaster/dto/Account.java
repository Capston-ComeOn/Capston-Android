package com.example.capstonmaster.dto;



import java.io.Serializable;

public class Account implements Serializable {

  private Long id;
  private String name;
  private String email;
  private String studentId;

  public Account() {
  }

  public Account(Long id, String name, String email, String studentId) {
    this.id = id;
    this.name = name;
    this.email = email;
    this.studentId = studentId;
  }

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
    return "Account{" +
      "id=" + id +
      ", name='" + name + '\'' +
      ", email='" + email + '\'' +
      ", studentId='" + studentId + '\'' +
      '}';
  }
}
