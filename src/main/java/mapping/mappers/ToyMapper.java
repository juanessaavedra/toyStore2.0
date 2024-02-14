package mapping.mappers;

import mapping.dtos.ToyDTO;
import model.Toy;
import model.ToyType;

public class ToyMapper {
    public static Toy mapFrom (ToyDTO dto) {return new Toy(dto.name(), dto.id(), dto.price(), dto.quantity(), dto.type()); }

    public static ToyDTO mapFrom (Toy model) {return new ToyDTO(model.getName(), model.getId(), model.getPrice(), model.getQuantity(), model.getType());}
}

