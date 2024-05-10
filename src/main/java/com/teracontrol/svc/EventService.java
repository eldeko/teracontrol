package com.teracontrol.svc;

import org.springframework.data.domain.Sort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.teracontrol.exception.EventNotFoundException;
import com.teracontrol.models.Event;
import com.teracontrol.repositories.EventRepository;
import java.util.List;

@ComponentScan(basePackages = "com.teracontrol.repositories")

@Service
public class EventService {

    private final EventRepository eventRepository;
    

    @Autowired
    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }


    public Page<Event> getAllEvents(Pageable pageable) {
    // Sort by date in descending order
    pageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by("dateTime").descending());

    return eventRepository.findAll(pageable);
}

    public Event getEventById(Long id) {
        return eventRepository.findById(id)
                .orElseThrow(() -> new EventNotFoundException(id));
    }

    public Event createEvent(Event event) {

        return eventRepository.save(event);
    }
}
