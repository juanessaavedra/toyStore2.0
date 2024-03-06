package model;

import lombok.*;

import java.io.Serializable;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString

public class Toy implements Serializable {
    private String name;
    private int id;
    private double price;
    private int quantity;
    private ToyType type;

}