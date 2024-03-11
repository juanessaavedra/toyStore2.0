package services.impl.Sales;

import mapping.dtos.SalesDTO;
import mapping.mappers.CustomersMapper;
import mapping.mappers.EmployeesMapper;
import mapping.mappers.SalesMapper;
import model.Sales;
import repository.Repository;
import repository.Sales.SalesRepositoryJDBCImpl;

import java.util.List;

public class SalesServiceImpl implements Repository<SalesDTO> {

    private Repository<Sales> salesRepository;

    public SalesServiceImpl() { this.salesRepository = new SalesRepositoryJDBCImpl();
    }

    @Override
    public List<SalesDTO> list() {
        return salesRepository.list()
                .stream()
                .map(SalesMapper::mapFromModel)
                .toList();
    }

    @Override
    public SalesDTO byId(Integer id) {
        return SalesMapper.mapFromModel(salesRepository.byId(id));
    }

    @Override
    public void save(SalesDTO salesDTO) {
        salesRepository.save(SalesMapper.mapFromDTO(salesDTO));
    }

    @Override
    public void delete(Integer id) {
        salesRepository.delete(id);
    }
}
