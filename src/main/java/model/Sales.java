package model;


import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder

public class Sales {
    private int id;
    private LocalDateTime date;
    private int idEmployee;
    private int idCustomer;
}
