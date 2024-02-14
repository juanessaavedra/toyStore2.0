package mapping.dtos;

import jdk.jfr.Category;
import model.ToyType;

public record ToyDTO(String name, String id, Integer price, Integer quantity, ToyType type) {
}
