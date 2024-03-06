package mapping.dtos;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record CustomersDTO(int id, String name, String user, String password, LocalDateTime birthdayDate, String gender) {
}
