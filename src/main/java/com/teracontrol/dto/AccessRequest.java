package com.teracontrol.dto;

import java.time.LocalDateTime;

import com.teracontrol.models.EventType;

import lombok.ToString;

@ToString
public class AccessRequest {
    
    public AccessRequest() {
        super();
    }
    private String code;
    public LocalDateTime requestDateTime = LocalDateTime.now(); 
    private EventType eventType;
    private String serialNumber;

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String deviceSerialNumber) {
        this.serialNumber = deviceSerialNumber;
    }

    public EventType getEventType() {
        return eventType;
    }

    public void setEventType(EventType eventType) {
        this.eventType = eventType;
    }

    // Getters and setters
    public String getcode() {
        return code;
    }

    public void setcode(String code) {
        this.code = code;
    }

    public LocalDateTime getRequestDateTime() {
        return LocalDateTime.now();
    }

    public void setRequestDateTime(LocalDateTime requestDateTime) {
        this.requestDateTime = requestDateTime;
    }
}