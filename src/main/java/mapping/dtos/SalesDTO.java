package mapping.dtos;

import lombok.Builder;
import model.Customers;
import model.Employees;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Builder
public record SalesDTO(int id, LocalDate date, Employees idEmployee, Customers idCustomer) {
}
