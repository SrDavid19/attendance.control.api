package project.attendance.control.api.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "event")
@Data
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
}
