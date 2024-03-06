package mapping.mappers;


import mapping.dtos.SalesDTO;
import model.Sales;

public class SalesMapper {

    public static SalesDTO mapFromModel (Sales sales) {
        return new SalesDTO(sales.getId(), sales.getDate(), sales.getIdEmployee(), sales.getIdCustomer());
    }

    public static Sales mapFromDTO (SalesDTO sales) {
        return Sales.builder()
                .id(sales.id())
                .date(sales.date())
                .idEmployee(sales.idEmployee())
                .idCustomer(sales.idCustomer())
                .build();
    }

}
