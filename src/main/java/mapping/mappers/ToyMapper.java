package mapping.mappers;

import mapping.dtos.ToyDTO;
import model.Toy;

public class ToyMapper {
    public static ToyDTO mapFromModel (Toy toy) {
        return new ToyDTO(toy.getName(), toy.getId(), toy.getPrice(), toy.getQuantity(), toy.getType());
    }

    public static Toy mapFromDTO (ToyDTO toy) {
        return Toy.builder()
                .name(toy.name())
                .id(toy.id())
                .price(toy.price())
                .quantity(toy.quantity())
                .type(toy.type())
                .build();
    }
}
