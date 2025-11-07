package project.attendance.control.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import project.attendance.control.api.entity.Register;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface IRegisterRepository extends JpaRepository<Register, Integer> {
    @Query("SELECT r FROM Register r WHERE r.registerdate = :date ORDER BY r.event.id ASC")
    List<Register> findByRegisterDateOrderByEvent(LocalDate date);

    @Query("SELECT r FROM Register r WHERE r.member.id = :memberId AND r.event.id = :eventId AND r.registerdate = :date")
    Optional<Register> findDuplicate(Integer memberId, Integer eventId, LocalDate date);

    @Query("SELECT CASE WHEN COUNT(r) > 0 THEN TRUE ELSE FALSE END " +
            "FROM Register r WHERE r.member.id = :memberId AND r.event.id = :eventId AND r.registerdate = :date")
    boolean existsByMemberAndEventAndDate(int memberId, int eventId, LocalDate date);
}
