package mapping.mappers;

import mapping.dtos.EmployeesDTO;
import model.Employees;


public class EmployeesMapper {
    public static EmployeesDTO mapFromModel (Employees employees) {
        return  new EmployeesDTO(employees.getId(), employees.getName(), employees.getPosition(), employees.getSalary(), employees.getUser(), employees.getPassword(), employees.getEntry_date() ,employees.getBirthdayDate(), employees.getGender());
    }

    public static Employees mapFromDTO (EmployeesDTO employees) {
        return Employees.builder()
                .id(employees.id())
                .name(employees.name())
                .position(employees.position())
                .salary(employees.salary())
                .user(employees.user())
                .password(employees.password())
                .entry_date(employees.entry_date())
                .birthdayDate(employees.birthdayDate())
                .gender(employees.gender())
                .build();
    }

}
