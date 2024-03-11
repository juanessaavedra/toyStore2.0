package mapping.dtos;

import model.Customers;
import model.Employees;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record SalesDTO(int id, LocalDate date, Employees idEmployee, Customers idCustomer) {
}
