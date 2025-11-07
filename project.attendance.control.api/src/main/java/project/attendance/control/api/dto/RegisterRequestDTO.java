package project.attendance.control.api.dto;
import lombok.Data;

@Data
public class RegisterRequestDTO {
    private int memberId;
    private String date;  // "yyyy-MM-dd"
    private String time;  //  "HH:mm"
}
