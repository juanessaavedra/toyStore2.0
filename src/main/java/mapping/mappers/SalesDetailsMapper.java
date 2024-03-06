package mapping.mappers;

import mapping.dtos.SalesDetailsDTO;
import model.SalesDetails;

public class SalesDetailsMapper {
    public static SalesDetailsDTO mapFromModel (SalesDetails salesDetails) {
        return new SalesDetailsDTO (salesDetails.getId(), salesDetails.getIdSales(), salesDetails.getIdToys(), salesDetails.getQuantity());
    }

    public static SalesDetails mapFromDTO (SalesDetailsDTO salesDetails) {
        return SalesDetails.builder()
                .id(salesDetails.id())
                .idSales(salesDetails.idSales())
                .idToys(salesDetails.idToys())
                .quantity(salesDetails.quantity())
                .build();
    }
}
