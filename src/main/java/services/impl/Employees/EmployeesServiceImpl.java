package services.impl.Employees;

import mapping.dtos.EmployeesDTO;
import mapping.mappers.CustomersMapper;
import mapping.mappers.EmployeesMapper;
import model.Employees;
import repository.Employees.EmployeesRepositoryJDBCImpl;
import repository.Repository;

import java.util.List;

public class EmployeesServiceImpl implements Repository<EmployeesDTO> {

    private Repository<Employees> employeesRepository;

    public EmployeesServiceImpl() { this.employeesRepository = new EmployeesRepositoryJDBCImpl();
    }

    @Override
    public List<EmployeesDTO> list() {
        return employeesRepository.list()
                .stream()
                .map(EmployeesMapper::mapFromModel)
                .toList();
    }

    @Override
    public EmployeesDTO byId(Integer id) {
        return EmployeesMapper.mapFromModel(employeesRepository.byId(id));
    }

    @Override
    public void save(EmployeesDTO employeesDTO) {
        employeesRepository.save(EmployeesMapper.mapFromDTO(employeesDTO));
    }

    @Override
    public void delete(Integer id) {
        employeesRepository.delete(id);
    }
}
