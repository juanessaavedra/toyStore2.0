package model;


import lombok.*;

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
    private LocalDateTime birthdayDate;
    private String gender;

}
