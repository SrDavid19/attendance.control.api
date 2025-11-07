package project.attendance.control.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import project.attendance.control.api.entity.Member;
import project.attendance.control.api.repository.IMemberRespository;

import java.util.List;
import java.util.Optional;

@Service
public class MemberService{
    @Autowired
    IMemberRespository memberRepository;

    public List<Member> getAllMembers(){
        return (List<Member>) memberRepository.findAll();
    }

    public ResponseEntity<Member> findById(Integer id) {
        Optional<Member> member = memberRepository.findById(id);
        return member.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    public ResponseEntity<List<Member>> findbyName(String name) {
        List<Member> members = memberRepository.findByName(name);
        if (members.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(members);
    }

    public ResponseEntity<List<Member>> findbyLastname(String lastname) {
        List<Member> members = memberRepository.findByLastname(lastname);
        if (members.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(members);
    }

    public ResponseEntity<Member> findByBiometricdata(String biometricdata) {
        Optional<Member> member = memberRepository.findByBiometricdata(biometricdata);
        return member.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    public void saveMember(Member member){
        Optional<Member> exist = memberRepository.findByBiometricdata(member.getBiometricdata());
        if(exist.isPresent()){
            throw new IllegalArgumentException("Biometric data already exists");
        }
        memberRepository.save(member);
    }

    public ResponseEntity<?> updateMember(Integer id, Member updated) {
        Optional<Member> existingMember = memberRepository.findById(id);
        if (existingMember.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Member member = existingMember.get();

        if (updated.getBiometricdata() != null) {
            Optional<Member> other = memberRepository.findByBiometricdata(updated.getBiometricdata());
            if (other.isPresent() && !other.get().getId().equals(id)) {
                return ResponseEntity.badRequest().body("Biometric data already exists");
            }
        }

        member.setName(updated.getName());
        member.setLastname(updated.getLastname());
        member.setBiometricdata(updated.getBiometricdata());

        memberRepository.save(member);
        return ResponseEntity.ok("Member updated successfully");
    }

    public ResponseEntity<?> deleteMember(Integer id) {
        if (!memberRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        memberRepository.deleteById(id);
        return ResponseEntity.ok("Member deleted successfully");
    }
}
