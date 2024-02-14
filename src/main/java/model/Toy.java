package model;

import java.io.Serializable;

public class Toy implements Serializable {
    private String name;
    private String id;
    private Integer price;

    private Integer quantity;
    private ToyType type;

    public Toy(String name, String id, Integer price, Integer quantity, ToyType type) {
        this.name = name;
        this.id = id;
        this.price = price;
        this.quantity = quantity;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public ToyType getType() {
        return type;
    }

    public void setType(ToyType type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Toy{" +
                "name='" + name + '\'' +
                ", id='" + id + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                ", type=" + type +
                '}';
    }
}
