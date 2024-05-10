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
  

    @GetMapping("/{id}")
    public Event getEventById(@PathVariable Long id) {
        return eventService.getEventById(id);
    }
}