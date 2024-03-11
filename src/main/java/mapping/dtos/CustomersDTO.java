package mapping.dtos;

import lombok.Builder;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Builder
public record CustomersDTO(int id, String name, String user, String password, LocalDate birthdayDate, String gender) {
}
