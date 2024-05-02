package com.teracontrol.svc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import com.teracontrol.exception.EventNotFoundException;
import com.teracontrol.models.Event;
import com.teracontrol.repositories.EventRepository;

import java.util.List;

@ComponentScan(basePackages="com.teracontrol.repositories")

@Service
public class EventService {

    private final EventRepository eventRepository;

    @Autowired    
    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

            
    public List<Event> getAllEvents() {

        var events = eventRepository.findAll();
        return events;
    }

    public Event getEventById(Long id) {
        return eventRepository.findById(id)
                .orElseThrow(() -> new EventNotFoundException(id));
    }

    public Event createEvent(Event event) {
        // Add logic for handling keychain not found exception
        return eventRepository.save(event);
    }
}
