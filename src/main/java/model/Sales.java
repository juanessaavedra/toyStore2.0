package model;


import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder

public class Sales {
    private int id;
    private LocalDate date;
    private Employees idEmployee;
    private Customers idCustomer;
}
