package com.teracontrol.models;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Setter
@Getter
@Entity
@Table(name = "events")
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private AuthEntity authEntity;      

    @Column(name = "datetime")
    private OffsetDateTime dateTime;

    @Enumerated(EnumType.STRING)
    private EventType eventType;    

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "door_id")
    private Door door;

    @Column(name = "code")
    private String code;

    public void setcode(String code) {
        this.code = code;
    }

    public String getDateTime() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return dateTime.format(formatter);
    }

    public User getUser() {
        if (user != null) {
            return user;
        } else {
            User unknownUser = new User();
            unknownUser.setUsername("***Unknown Keylock Code***");
            unknownUser.setKeylock(new Keylock());
            return unknownUser;
        }
    }
}