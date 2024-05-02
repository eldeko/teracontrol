package com.teracontrol.models;

import jakarta.persistence.*;

@Entity
@Table(name = "key_locks")
public class KeyLock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "Enabled")
    private boolean enabled;

    @Column(name = "brand")
    private String brand;

    @Column(name = "model")
    private String model;

    @Column(name = "key_type")
    private String keyType;

    @Column(name = "code")
    private String code;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User users;    
    
}