package mapping.dtos;

import java.time.LocalDateTime;

public record SalesDTO(int id, LocalDateTime date, int idEmployee, int idCustomer) {
}
