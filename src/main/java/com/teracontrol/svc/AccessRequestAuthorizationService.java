package com.teracontrol.svc;

import java.time.OffsetDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.teracontrol.access.WebSocketController;
import com.teracontrol.dto.AccessRequest;
import com.teracontrol.models.AuthEntity;
import com.teracontrol.models.DoorControlDevice;
import com.teracontrol.models.Event;
import com.teracontrol.models.User;



@Service
public class AccessRequestAuthorizationService {

    @Autowired
    private KeylockService keylockService;

    @Autowired
    private EventService eventService;

    @Autowired
    private UserService userService;

    @Autowired
    private DoorDeviceService doorDeviceService;

    @Autowired
    private WebSocketController webSocketController;

    public AuthEntity processAccessRequest(AccessRequest accessRequest) {
       
        AuthEntity result = keylockService.verifyAccessRequest(accessRequest.getcode());
       
        Event event = new Event();
        
        if (result.isAuthorized()) {         
       
        User user = userService.findUserByCode(accessRequest.getcode());
        
        event.setUser(user);
        }    
       
        DoorControlDevice doorDevice = doorDeviceService.getDoorByDeviceSerialNumber(accessRequest.getSerialNumber());
        event.setEventType(accessRequest.getEventType());
        event.setAuthEntity(result);
        event.setcode(accessRequest.getcode());
        event.setDoor(doorDevice.getInstalledDoor());   
        
        event.setDateTime(OffsetDateTime.now());
        eventService.createEvent(event);
        
        webSocketController.sendEvent(event);    
      
        System.out.println("Access request:\n" + event.toString() + "\n");


        return result;
    }
}