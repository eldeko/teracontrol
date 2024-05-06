package com.teracontrol.svc;

import com.teracontrol.models.AccessTime;
import com.teracontrol.models.AuthEntity;
import com.teracontrol.models.KeyLock;
import com.teracontrol.repositories.KeylockRepository;

import java.time.DayOfWeek;
import java.time.OffsetDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class KeylockService {

    @Autowired
    private KeylockRepository KeylockRepository;


    AuthEntity verifyAccessRequest(String keylockCode) {
        
        var keylocks = KeylockRepository.findAll();

        Optional<KeyLock> keylock = keylocks.stream()
            .filter(k -> k.getKeylockCode().contains(keylockCode))
            .findFirst();

        if (keylock.isEmpty()) {
            return new AuthEntity(false, "Key does not exist in DB");
        }
        
        //Verify it is enabled
        if (!keylock.get().isEnabled()) {
            return new AuthEntity(false, "Key is disabled");
        }

        //Verify time control
        //VerifyTimeControl(keylock); Turned off until implementing


        return new AuthEntity(true, "Verified existance and time control");
    }

    private AuthEntity VerifyTimeControl(KeyLock result) {
        // Get the current date
        DayOfWeek currentDate = OffsetDateTime.now().getDayOfWeek();
        
        // Get the accessTime for the current day from the keylock
        Optional<AccessTime> accessTimeOptional = result.getAccessTimes().stream()
            .filter(accessTime -> accessTime.getDayOfWeek() == currentDate)
            .findFirst();
        
        if (accessTimeOptional.isPresent()) {
            AccessTime accessTime = accessTimeOptional.get();

            // Get the current time
            OffsetDateTime currentTime = OffsetDateTime.now();

            // Check if the current time is within the access time range
            if (currentTime.isAfter(accessTime.getStartTime()) && currentTime.isBefore(accessTime.getEndTime())) {
                // Access time is in range
                // Perform further operations with the accessTime object
                    return new AuthEntity(true, "Access granted");

            } else {
                // Access time is not in range
                // Handle the case when the current time is outside the access time range
                return new AuthEntity(false, "Access denied by time control. Out of time range");
            }
                       
        } 
        return new AuthEntity(false, "Access denied for unknown error in time control verification"); 
    }
    
    }   

