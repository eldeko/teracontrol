package com.teracontrol.models;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AccessRequestAuthorizationResponse extends ResponseEntity<AuthEntity> {
    
    public AccessRequestAuthorizationResponse(AuthEntity authEntity) {
        super(authEntity, HttpStatus.OK);
    }

    AuthEntity authEntity;
}
