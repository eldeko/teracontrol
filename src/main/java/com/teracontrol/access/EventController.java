package com.teracontrol.access;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.*;

import com.teracontrol.models.Event;
import com.teracontrol.svc.EventService;

import java.util.List;

@ComponentScan(basePackages="com.teracontrol.svc")

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
    
        html.append("<style>");
        html.append("table {border-collapse: collapse; width: 100%;}");
        html.append("th, td {border: 1px solid #ddd; padding: 15px; white-space: nowrap;}");
        html.append("tr:nth-child(even){background-color: #f2f2f2;}");
        html.append("th {padding-top: 12px; padding-bottom: 12px; text-align: left; background-color: #333; color: gray;}");
        html.append("</style>");
    
        html.append("<table><tr><th>User ID</th><th>User Name</th><th>KeyCode</th><th>Datetime</th><th>EventType</th><th>Door number</th><th>Door place</th></tr>");
    
        for (Event event : events) {
            html.append("<tr><td>")
                .append(event.getUser().getId())
                .append("</td><td>")
                .append(event.getUser().getUsername())
                .append("</td><td>")
                .append(event.getKeyCode())
                .append("</td><td>")
                .append(event.getDateTime())
                .append("</td><td>")
                .append(event.getEventType())
                .append("</td><td>")
                .append(event.getDoor().getId())
                .append("</td><td>")
                .append(event.getDoor().getLocation())
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

    @PostMapping
    public Event createEvent(@RequestBody Event event) {
        return eventService.createEvent(event);
    }
}