package com.teracontrol.dto;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;

@Getter
@Setter
@AllArgsConstructor
public class UserDto {

    private String name;
    private String surname;   
    private String computerModel;
    private String region;
    private String seniority;
    private Date birthDate;
    private Date firstDay;
    private String title;
}