package project.attendance.control.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import project.attendance.control.api.entity.Event;
import project.attendance.control.api.service.EventService;

import java.util.List;

@RestController
@RequestMapping("api/event/")
public class EventController {

    @Autowired
    EventService eventService;

    @GetMapping("/")
    public List<Event> getAllEvents() {
        return (List<Event>) eventService.findAll();
    }
}
