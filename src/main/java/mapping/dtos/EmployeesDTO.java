package mapping.dtos;

import lombok.Builder;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Builder
public record EmployeesDTO(int id, String name,String position, double salary, String user, String password, LocalDate entry_date, LocalDate birthdayDate, String gender) {
}
