package model;

import lombok.*;

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
    private String user;
    private String password;
    private LocalDateTime birthdayDate;
    private String gender;
}
