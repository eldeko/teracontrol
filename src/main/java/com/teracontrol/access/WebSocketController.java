package com.teracontrol.access;

import java.util.Collections;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import com.teracontrol.models.Event;
import com.teracontrol.svc.EventService;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

@Controller
public class WebSocketController {

    private final EventService eventService;
    private final SimpMessagingTemplate template;

    public WebSocketController(EventService eventService, SimpMessagingTemplate template) {
        this.eventService = eventService;
        this.template = template;
    }

    @MessageMapping("/getEvents")
    public void getEvents(Pagination pagination) {
        Pageable pageable = PageRequest.of(pagination.getActivePage(), pagination.getItemsCountPerPage());
        Page<Event> page = eventService.getAllEvents(pageable);
        template.convertAndSend("/topic/events", page.getContent());
    }

    // This method will be used to send real-time events
    public void sendEvent(Event event) {
        template.convertAndSend("/topic/events", Collections.singletonList(event));
    }
}