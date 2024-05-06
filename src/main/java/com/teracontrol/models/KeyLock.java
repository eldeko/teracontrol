package com.teracontrol.models;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "key_locks")
@Getter
@Setter
public class KeyLock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @Column(name = "Enabled")
    public boolean enabled;

    @Column(name = "brand")
    public String brand;

    @Column(name = "model")
    public String model;

    @OneToMany(mappedBy = "keyLock")
    private List<AccessTime> accessTimes;

    @OneToOne
    @JoinColumn(name = "user_id")
    @JsonBackReference
    private User user;

    @Column(name = "keylockCode")
    private String keylockCode;

    public String getKeylockCode() {
        return this.keylockCode;
    }
}