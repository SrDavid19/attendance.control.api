package project.attendance.control.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.attendance.control.api.entity.Member;
import project.attendance.control.api.service.MemberService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/member")
public class MemberController {

    @Autowired
    MemberService memberService;

    @GetMapping("/")
    public List<Member> getAllMembers() {
        return (List<Member>) memberService.getAllMembers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Member> getById(@PathVariable Integer id) {
        return memberService.findById(id);
    }

    @GetMapping("/biometric/{biometricdata}")
    public ResponseEntity<Member> getByBiometricdata(@PathVariable String biometricdata) {
        return memberService.findByBiometricdata(biometricdata);
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<List<Member>> getByName(@PathVariable String name) {
        return memberService.findbyName(name);
    }

    @GetMapping("/lastname/{lastname}")
    public ResponseEntity<List<Member>> getByLastname(@PathVariable String lastname) {
        return memberService.findbyLastname(lastname);
    }

    @PostMapping("/add")
    public ResponseEntity<String> addMember(@RequestBody Member member){
        try{
            memberService.saveMember(member);
            return ResponseEntity.status(HttpStatus.CREATED).body("Member added successfully");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateMember(@PathVariable Integer id, @RequestBody Member member) {
        return memberService.updateMember(id, member);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteMember(@PathVariable Integer id) {
        return memberService.deleteMember(id);
    }

}
