package com.teracontrol.access;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.*;

import com.teracontrol.models.Event;
import com.teracontrol.svc.EventService;

import java.util.List;

@ComponentScan(basePackages = "com.teracontrol.svc")

@RestController
@RequestMapping("/events")
public class EventController {

    private final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping(produces = "text/html")
    public String getAllEvents() {
        List<Event> events = eventService.getAllEvents();
    
        StringBuilder html = new StringBuilder();
    
        html.append(" <style>"
        + "table {border-collapse: collapse; width: 100%;}"
        + "th, td {border: 1px solid #ddd; padding: 15px; white-space: nowrap;}"        
        + "th {padding-top: 12px; padding-bottom: 12px; text-align: left; background-color: #333; color: darkgray;}"
        + ".authorized-entry {background-color: #2ECC71;}"
        + ".authorized-exit {background-color: #82E0AA;}"
        + ".disabled-key {background-color: #A93226; color: #F0F3F4;}"
        + ".unknown-key-access {background-color: gray; color: #F0F3F4;}"
        + ".unauthorized-entry {background-color: darkorange;}"
        + "</style> ");
    
        html.append("<table><tr><th>User ID</th><th>User Name</th><th>KeyLock Code</th><th>Datetime</th><th>EventType</th><th>Authorization</th><th>Reason</th></tr>");
    
        for (Event event : events) {
            String rowClass;

            if (event.getAuthEntity().isAuthorized()) {

                switch (event.getEventType().toString()) {
                case "ENTRY":                    
                        rowClass = "authorized-entry";                    
                    break;
                case "EXIT":                    
                        rowClass = "authorized-exit";                    
                    break;
                default:
                    rowClass = "";
                    break;
            }
            } else {
                switch (event.getAuthEntity().getReason()) {
                    case "Key does not exist in DB":
                        rowClass = "unknown-key-access";
                        break;
                    case "Key is disabled":
                        rowClass = "disabled-key";
                        break;   
                case "Access denied by time control. Out of time range":
                    rowClass = "unauthorized-entry";
                    break;
                 default:
                    rowClass = "";
                    break;
            }}
    
            String authorization = event.getAuthEntity().isAuthorized() ? "GRANTED" : "DENIED";
    
            html.append("<tr class=\"").append(rowClass).append("\"><td>");
            if (event.getUser() != null) {
                html.append(event.getUser().getId());
            }
            html.append("</td><td>")
                .append(event.getUser().getUsername())
                .append("</td><td>")
                .append(event.getKeylockCode())
                .append("</td><td>")
                .append(event.getDateTime())
                .append("</td><td>")
                .append(event.getEventType())
                .append("</td><td>")
                .append(authorization)
                .append("</td><td>")
                .append(event.getAuthEntity().getReason())
                .append("</td></tr>");
        }
    
        html.append("</table>");
        var output = html.toString();
        return output;
    }

    @GetMapping("/{id}")
    public Event getEventById(@PathVariable Long id) {
        return eventService.getEventById(id);
    }
}