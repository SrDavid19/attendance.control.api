package project.attendance.control.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import project.attendance.control.api.dto.RegisterRequestDTO;
import project.attendance.control.api.service.RegisterService;

import java.time.LocalDate;
import java.util.Date;

@Controller
@RequestMapping("api/register")
public class RegisterController {

    @Autowired
    private RegisterService registerService;

    @PostMapping
    public ResponseEntity<String> addRegister(@RequestBody RegisterRequestDTO request) {
        try {
            String message = registerService.addRegister(request);
            return ResponseEntity.ok(message);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/date/{date}")
    public ResponseEntity<?> getByDate(@PathVariable String date) {
        LocalDate parsedDate = LocalDate.parse(date);
        return registerService.getRegistersByDate(parsedDate);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteRegister(@PathVariable Integer id) {
        return registerService.deleteRegister(id);
    }

}
