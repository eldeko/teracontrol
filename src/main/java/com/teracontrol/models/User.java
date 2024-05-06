package com.teracontrol.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import org.hibernate.annotations.CreationTimestamp;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Setter
@Getter
@Entity
@Table(name = "users")
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  private String name;

  private String surname;

  private String username;

  private String email;

  private String region;

  private String title;

  private String seniority;
  @Column(name = "birth_date")
  private Date birthDate;

  @Column(name = "first_day")
  private Date firstDay;

  @Column(name = "computer_model")
  private String computerModel;

  @JsonManagedReference
  @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = false)
  private KeyLock keyLock;

  @CreationTimestamp
  @Column(name = "created_at", nullable = false, updatable = false)
  private Date createdAt;

  @CreationTimestamp
  @Column(name = "updated_at", nullable = false)
  private Date updatedAt;

  public void setName(String name) {
    this.name = name;    
  }

  public void setSurname(String surname) {
    this.surname = surname;
    updateUsernameAndEmail();
  }

  private void updateUsernameAndEmail() {
    if (name != null && surname != null) {
      this.username = (name + "." + surname).toLowerCase();
      this.email = this.username + "@teracontrol.com";
    }
  }
}