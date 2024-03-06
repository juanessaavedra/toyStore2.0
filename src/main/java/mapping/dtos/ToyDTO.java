package mapping.dtos;

import model.ToyType;

public record ToyDTO(String name, int id, double price, int quantity, ToyType type) {
}
