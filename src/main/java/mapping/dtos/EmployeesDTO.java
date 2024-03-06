package mapping.dtos;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record EmployeesDTO(int id, String name, String user, String password, LocalDateTime birthdayDate, String gender) {
}
