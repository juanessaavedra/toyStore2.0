package model;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class Employees {
    private int id;
    private String name;
    private String position;
    private double salary;
    private String user;
    private String password;
    private LocalDate entry_date;
    private LocalDate birthdayDate;
    private String gender;
}
