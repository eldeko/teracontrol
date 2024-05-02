package com.teracontrol.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.util.Date;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name = "users")
public class User {

  @Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private long id;
  private String username;
  private String email;


  @Column(name = "full_name")
  private String fullName;
  
  public String getFullName() {
    return fullName;
  }

  public void setFullName(String fullName) {
    this.fullName = fullName;
  }


  @Column(name = "computer_model")
  private String computerModel;

  public String getComputerModel() {
    return computerModel;
  }

  public void setComputerModel(String computerModel) {
    this.computerModel = computerModel;
  }


  private String region;
  private String seniority;

  public String getRegion() {
    return region;
  }

  public void setRegion(String region) {
    this.region = region;
  }

  public String getSeniority() {
    return seniority;
  }

  public void setSeniority(String seniority) {
    this.seniority = seniority;
  }


  @Column(name = "birth_date")
  private Date birthDate;

  public void setBirthDate(Date birthDate) {
    this.birthDate = birthDate;
  }


  @Column(name = "first_day")
  private Date firstDay;

  public Date getFirstDay() {
    return firstDay;
  }

  public void setFirstDay(Date firstDay) {
    this.firstDay = firstDay;
  }


  private String title;

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }


  @CreationTimestamp
 @Column(name = "created_at", nullable = false, updatable = false)
  private Date createdAt;

  @CreationTimestamp
 @Column(name = "updated_at", nullable = false)
  private Date updatedAt;

  public long getId() {
    return id;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }
  
  public Date getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(Date createdAt) {
    this.createdAt = createdAt;
  }

  public Date getUpdatedAt() {
    return updatedAt;
  }

  public void setUpdatedAt(Date updatedAt) {
    this.updatedAt = updatedAt;
  }


   @OneToMany(mappedBy = "users", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  private List<KeyLock> keyLocks;

  public User() {
    
  }

 

}