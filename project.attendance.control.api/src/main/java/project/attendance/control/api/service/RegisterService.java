package project.attendance.control.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import project.attendance.control.api.dto.RegisterRequestDTO;
import project.attendance.control.api.entity.Event;
import project.attendance.control.api.entity.Member;
import project.attendance.control.api.entity.Register;
import project.attendance.control.api.repository.IEventRespository;
import project.attendance.control.api.repository.IMemberRespository;
import project.attendance.control.api.repository.IRegisterRepository;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;

@Service
public class RegisterService {

    @Autowired
    private IRegisterRepository registerRepository;

    @Autowired
    private IMemberRespository memberRepository;

    @Autowired
    private IEventRespository eventRepository;

    public String addRegister(RegisterRequestDTO request) {
        // Buscar member
        Member member = memberRepository.findById(request.getMemberId())
                .orElseThrow(() -> new IllegalArgumentException("Member not found"));

        // Convertir fecha y hora
        LocalDate date = LocalDate.parse(request.getDate());
        LocalTime time = LocalTime.parse(request.getTime());
        DayOfWeek dayOfWeek = date.getDayOfWeek();

        // Determinar eventId
        int eventId = determineEventId(dayOfWeek, time);

        // Validar si ya existe un registro igual
        boolean exists = registerRepository.existsByMemberAndEventAndDate(member.getId(), eventId, date);
        if (exists) {
            return "Member already registered.";
        }

        // Crear el registro
        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new IllegalArgumentException("Evet not found"));

        Register register = new Register();
        register.setMember(member);
        register.setEvent(event);
        register.setRegisterdate(date);
        register.setRegistertime(time);

        registerRepository.save(register);
        return "Attendance registered succesfully in event: " + event.getName();
    }

    public ResponseEntity<?> getRegistersByDate(LocalDate date) {
        List<Register> list = registerRepository.findByRegisterDateOrderByEvent(date);

        List<Map<String, Object>> result = new ArrayList<>();
        for (Register r : list) {
            Map<String, Object> map = new HashMap<>();
            map.put("id", r.getId());
            map.put("member", r.getMember().getName() + " " + r.getMember().getLastname());
            map.put("event", r.getEvent().getName());
            map.put("time", r.getRegistertime().toString());
            result.add(map);
        }

        return ResponseEntity.ok(result);
    }

    public ResponseEntity<?> deleteRegister(Integer id) {
        if (!registerRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        registerRepository.deleteById(id);
        return ResponseEntity.ok("Register successfully deleted");
    }

    private int determineEventId(DayOfWeek dayOfWeek, LocalTime time) {
        boolean isSunday = dayOfWeek == DayOfWeek.SUNDAY;

        if (time.isAfter(LocalTime.of(4, 0)) && time.isBefore(LocalTime.of(5, 31))) {
            return 1;
        }

        if (!isSunday && time.isAfter(LocalTime.of(8, 0)) && time.isBefore(LocalTime.of(10, 1))) {
            return 2;
        }

        if (isSunday && time.isAfter(LocalTime.of(8, 30)) && time.isBefore(LocalTime.of(12, 1))) {
            return 4;
        }

        if (time.isAfter(LocalTime.of(17, 0)) && time.isBefore(LocalTime.of(20, 31))) {
            return 3;
        }

        throw new IllegalArgumentException("Not Events available.");
    }

}
