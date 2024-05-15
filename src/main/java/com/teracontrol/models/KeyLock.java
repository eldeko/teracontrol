package com.teracontrol.models;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity
@Table(name = "keylocks")
public class Keylock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @Column(name = "enabled")
    public boolean enabled;

    @Column(name = "brand")
    public String brand;

    @Column(name = "model")
    public String model;

@OneToOne
@JoinColumn(name = "user_id", nullable = true)
@OnDelete(action = OnDeleteAction.NO_ACTION)
@JsonBackReference
private User user;

    @Column(name = "code")
    private String code;

    public String getcode() {
        return this.code;
    }

    @Column (name = "type")
    public KeylockType type;
}