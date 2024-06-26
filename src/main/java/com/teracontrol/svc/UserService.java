package com.teracontrol.svc;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import org.springframework.context.annotation.ComponentScan;

import com.teracontrol.models.Event;
import com.teracontrol.models.Keylock;
import com.teracontrol.models.User;
import com.teracontrol.repositories.EventRepository;
import com.teracontrol.repositories.KeylockRepository;
import com.teracontrol.repositories.UserRepository;

@ComponentScan(basePackages = "com.teracontrol.repositories")

@Service
public class UserService {

    private final UserRepository userRepository;
    private final EventRepository eventRepository;
    private final KeylockRepository keylockRepository;

    @Autowired
    public UserService(UserRepository userRepository, EventRepository eventRepository, KeylockRepository keylockRepository) {
        this.userRepository = userRepository;
        this.eventRepository = eventRepository;
        this.keylockRepository = keylockRepository;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Map<User, List<Event>> getUserWithEvents(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("User not found"));
        List<Event> events = eventRepository.findByUserId(userId);
        Map<User, List<Event>> userWithEvents = new HashMap<>();
        userWithEvents.put(user, events);
        return userWithEvents;
    }

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public User findUserByCode(String code) {
        return userRepository.findByKeylock_code(code);     
    }

}
