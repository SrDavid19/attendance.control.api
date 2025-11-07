package project.attendance.control.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.attendance.control.api.entity.Event;

@Repository
public interface IEventRespository extends JpaRepository<Event,Integer> {

}
