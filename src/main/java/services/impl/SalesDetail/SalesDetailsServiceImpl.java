package services.impl.SalesDetail;

import mapping.dtos.SalesDetailsDTO;
import mapping.mappers.CustomersMapper;
import mapping.mappers.SalesDetailsMapper;
import mapping.mappers.SalesMapper;
import model.SalesDetails;
import repository.Repository;
import repository.SalesDetails.SalesDetailsRepositoryJDBCImpl;

import java.util.List;

public class SalesDetailsServiceImpl implements Repository<SalesDetailsDTO> {

    private Repository<SalesDetails> salesDetailsRepository;

    public SalesDetailsServiceImpl() {this.salesDetailsRepository = new SalesDetailsRepositoryJDBCImpl();
    }

    @Override
    public List<SalesDetailsDTO> list() {
        return salesDetailsRepository.list()
                .stream()
                .map(SalesDetailsMapper::mapFromModel)
                .toList();
    }

    @Override
    public SalesDetailsDTO byId(Integer id) {
        return SalesDetailsMapper.mapFromModel(salesDetailsRepository.byId(id));
    }

    @Override
    public void save(SalesDetailsDTO salesDetailsDTO) {
        salesDetailsRepository.save(SalesDetailsMapper.mapFromDTO(salesDetailsDTO));
    }

    @Override
    public void delete(Integer id) {
            salesDetailsRepository.delete(id);
    }
}
