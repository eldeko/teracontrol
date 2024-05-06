package com.teracontrol.svc;

import java.time.OffsetDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.teracontrol.dto.AccessRequest;
import com.teracontrol.models.AuthEntity;
import com.teracontrol.models.DoorControlDevice;
import com.teracontrol.models.Event;
import com.teracontrol.models.User;
import com.teracontrol.repositories.UserRepository;



@Service
public class AccessRequestAuthorizationService {

    @Autowired
    private KeylockService keylockService;

    @Autowired
    private EventService eventService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DoorDeviceService doorDeviceService;

    public AuthEntity processAccessRequest(AccessRequest accessRequest) {

        AuthEntity result = keylockService.verifyAccessRequest(accessRequest.getkeylockCode());

        Event event = new Event();
        User user = userRepository.findByKeylockCode(accessRequest.getkeylockCode());
        DoorControlDevice doorDevice = doorDeviceService.getDoorByDeviceSerialNumber(accessRequest.getSerialNumber());
        event.setEventType(accessRequest.getEventType());
        event.setAuthEntity(result);
        event.setKeylockCode(accessRequest.getkeylockCode());
        event.setDoor(doorDevice.getInstalledDoor());   

        event.setUser(user);
        
        event.setDateTime(OffsetDateTime.now());
        eventService.createEvent(event);

        if (!result.isAuthorized() && result.getReason().equals("Key does not exist in DB")) {
            return new AuthEntity(false, "Unknown key access attempt");
        }

        return result;
    }
}