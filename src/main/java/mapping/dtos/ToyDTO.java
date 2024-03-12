package mapping.dtos;

import lombok.Builder;
import model.ToyType;

@Builder
public record ToyDTO(String name, int id, double price, int quantity, ToyType type) {
}
