package project.attendance.control.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.attendance.control.api.entity.Member;

import java.util.List;
import java.util.Optional;

@Repository
public interface IMemberRespository extends JpaRepository<Member, Integer> {
    Optional<Member> findByBiometricdata(String biometricdata);
    List<Member> findByName(String name);
    List<Member> findByLastname(String lastname);
}
