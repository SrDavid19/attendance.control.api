package project.attendance.control.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.attendance.control.api.entity.Event;
import project.attendance.control.api.repository.IEventRespository;

import java.util.List;

@Service
public class EventService {
    @Autowired
    IEventRespository eventRespository;

    public List<Event> findAll() {
        return eventRespository.findAll();
    }

}
