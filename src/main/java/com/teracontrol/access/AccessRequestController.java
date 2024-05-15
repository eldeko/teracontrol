package com.teracontrol.access;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.teracontrol.dto.AccessRequest;
import com.teracontrol.models.AuthEntity;
import com.teracontrol.svc.AccessRequestAuthorizationService;

@RestController
public class AccessRequestController {

@Autowired
private AccessRequestAuthorizationService accessRequestAuthorizationService;

    @PostMapping("/access")    
    public ResponseEntity<String> handleAccessRequest(@RequestBody AccessRequest accessRequest) {
        
       AuthEntity result = accessRequestAuthorizationService.processAccessRequest(accessRequest);

      //console log


        if (result.isAuthorized() == true) {
            return ResponseEntity.ok("Access granted");

        } else {

          var reason = result.getReason();

          switch (reason) {
            case "Key does not exist in DB":
            return ResponseEntity.status(404).body(result.getReason());    
           
            case "Key is disabled":
            return ResponseEntity.status(423).body(result.getReason());
           
            case "Access denied by time control. Out of time range":
            return ResponseEntity.status(401).body(result.getReason());
           
            default:
            return ResponseEntity.status(422).body(result.getReason());
          }           
        }
    }
}