package model;


import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString

public class Customers {
    private int id;
    private String name;
    private String user;
    private String password;
    private LocalDate birthdayDate;
    private String gender;

}
