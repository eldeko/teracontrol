package com.teracontrol.access;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.teracontrol.dto.UserDto;
import com.teracontrol.models.Event;
import com.teracontrol.models.User;
import com.teracontrol.svc.UserService;

import java.util.List;
import java.util.Map;

@ComponentScan(basePackages = "com.teracontrol.svc")

@RestController
public class UsersController {

    @Autowired
    private final UserService userService;

    public UsersController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/api/users")
    public List<User> getAllUsers() {

        return userService.getAllUsers();
    }

    @GetMapping(value = "/users/{id}/events", produces = "text/html")
    public ResponseEntity<String> getUserWithEvents(@PathVariable Long id) {
        Map<User, List<Event>> userWithEvents = userService.getUserWithEvents(id);
        StringBuilder html = new StringBuilder();

        html.append("<style>");
        html.append("table {border-collapse: collapse; width: 100%;}");
        html.append("th, td {border: 1px solid #ddd; padding: 15px; white-space: nowrap;}");                                                                                              
        html.append("tr:nth-child(even){background-color: #f2f2f2;}");
        html.append("th {padding-top: 12px; padding-bottom: 12px; text-align: left; background-color: #333; color: gray;}");
        html.append("</style>");

        userWithEvents.forEach((user, events) -> {
            html.append("<h3>Name</h3>");
            html.append("<p>").append(user.getSurname()+", "+user.getName()).append("</p>"); // replace with actual user fields
            html.append("<h2>Events</h2>");
            html.append(
                    "<table><tr><th>KeyLockCode</th><th>Datetime</th><th>EventType</th><th>Door number</th><th>Door place</th></tr>");

            final String TD_SEPARATOR = "</td><td>";

            events.forEach(event -> {
                html.append("<tr><td>")
                        .append(event.getUser().getKeyLock().getKeylockCode())
                        .append(TD_SEPARATOR)
                        .append(event.getDateTime())
                        .append(TD_SEPARATOR)
                        .append(event.getEventType())
                        .append(TD_SEPARATOR)
                        .append(event.getDoor().getId())
                        .append(TD_SEPARATOR)
                        .append(event.getDoor().getName())
                        .append("</td></tr>");
            });

            html.append("</table>");
        });

        return ResponseEntity.ok(html.toString());
    }

    @PostMapping("/api/createUser")
    @ResponseStatus(HttpStatus.CREATED)
    public User createUser(@RequestBody UserDto user) {

        User newUser = new User();
        newUser.setName(user.getName());
        newUser.setSurname(user.getSurname());
        newUser.setBirthDate(user.getBirthDate());
        newUser.setFirstDay(user.getFirstDay());
        newUser.setTitle(user.getTitle());   
        newUser.setSeniority(user.getSeniority());
        newUser.setRegion(user.getRegion());
        newUser.setComputerModel(user.getComputerModel());
       

        return userService.createUser(newUser);
    }

}
