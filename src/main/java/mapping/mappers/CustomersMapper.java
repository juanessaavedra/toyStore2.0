package mapping.mappers;

import mapping.dtos.CustomersDTO;
import model.Customers;

public class CustomersMapper {

    public static CustomersDTO mapFromModel (Customers customers) {
        return new CustomersDTO(customers.getId(), customers.getName(), customers.getUser(), customers.getPassword(), customers.getBirthdayDate(), customers.getGender());
    }

    public static Customers mapFromDTO (CustomersDTO customers) {
        return Customers.builder().
                id(customers.id())
                .name(customers.name())
                .user(customers.user())
                .password(customers.password())
                .birthdayDate(customers.birthdayDate())
                .gender(customers.gender())
                .build();
    }
}
