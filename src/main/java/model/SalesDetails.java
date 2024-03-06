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
    private int idSales;
    private int idToys;
    private int quantity;
}
