package mapping.dtos;

import lombok.Builder;

@Builder
public record SalesDetailsDTO(int id, int idSales, int idToys, int quantity) {
}
