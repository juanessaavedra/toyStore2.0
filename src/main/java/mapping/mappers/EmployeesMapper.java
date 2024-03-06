package mapping.mappers;

import mapping.dtos.EmployeesDTO;
import model.Employees;


public class EmployeesMapper {
    public static EmployeesDTO mapFromModel (Employees employees) {
        return  new EmployeesDTO(employees.getId(), employees.getName(), employees.getUser(), employees.getPassword(), employees.getBirthdayDate(), employees.getGender());
    }

    public static Employees mapFromDTO (EmployeesDTO employees) {
        return Employees.builder()
                .id(employees.id())
                .name(employees.name())
                .user(employees.user())
                .password(employees.password())
                .birthdayDate(employees.birthdayDate())
                .gender(employees.gender())
                .build();
    }

}
