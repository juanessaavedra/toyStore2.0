package model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder

public class SalesDetails {
    private int id;
    private Sales idSales;
    private Toy idToys;
    private int quantity;
}
