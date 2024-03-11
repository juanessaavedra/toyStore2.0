package mapping.dtos;

import lombok.Builder;
import model.Sales;
import model.Toy;

@Builder
public record SalesDetailsDTO(int id, Sales idSales, Toy idToys, int quantity) {
}
