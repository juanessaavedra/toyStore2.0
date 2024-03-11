package services.impl.Customers;

import mapping.dtos.CustomersDTO;
import mapping.mappers.CustomersMapper;
import mapping.mappers.ToyMapper;
import model.Customers;
import repository.Customers.CustomersRepositoryJDBCImpl;
import repository.Repository;

import java.util.List;

public class CustomersServiceImpl implements Repository<CustomersDTO> {

    private Repository<Customers> customerRepository;
    public CustomersServiceImpl() { this.customerRepository = new CustomersRepositoryJDBCImpl();
    }

    @Override
    public List<CustomersDTO> list() {
        return customerRepository.list()
                .stream()
                .map(CustomersMapper::mapFromModel)
                .toList();
    }

    @Override
    public CustomersDTO byId(Integer id) {
        return CustomersMapper.mapFromModel(customerRepository.byId(id));
    }

    @Override
    public void save(CustomersDTO customersDTO) {
        customerRepository.save(CustomersMapper.mapFromDTO(customersDTO));
    }

    @Override
    public void delete(Integer id) {
        customerRepository.delete(id);
    }
}
