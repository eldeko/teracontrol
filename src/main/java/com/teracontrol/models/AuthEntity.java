package com.teracontrol.models;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@Setter
@Getter
@NoArgsConstructor
@Embeddable
public class AuthEntity {

    private boolean isAuthorized;
    private String reason;

    public AuthEntity(boolean isAuthorized, String reason) {
        this.isAuthorized = isAuthorized;
        this.reason = reason;
    }
}
