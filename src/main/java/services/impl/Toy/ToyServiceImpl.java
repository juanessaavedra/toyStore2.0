package services.impl.Toy;


import mapping.dtos.ToyDTO;
import mapping.mappers.ToyMapper;
import model.Toy;
import repository.Repository;
import repository.Toy.ToyRepository;
import repository.Toy.ToyRepositoryJDBCImpl;
import services.ToyService;

import java.util.ArrayList;
import java.util.List;

public class ToyServiceImpl implements ToyService {

    private ToyRepository toyRepository;


    public ToyServiceImpl() {this.toyRepository = new ToyRepositoryJDBCImpl();
    }

    @Override
    public List<ToyDTO> addToy(ToyDTO toy) throws Exception {
        if (!verifyToyExists(toy.id())) {
            toyRepository.save(ToyMapper.mapFromDTO(toy));
        }
        throw new Exception("The toy has already been created");
    }

    @Override
    public List<ToyDTO> listToys() {
        return toyRepository.listToys()
                .stream()
                .map(ToyMapper::mapFromModel)
                .toList();
    }

    @Override
    public boolean verifyToyExists(int id) {
        return false;
    }


    @Override
    public Integer totalToys() throws Exception {
        return toyRepository.totalToys();
    }

    @Override
    public double totalPrices() throws Exception {
        return toyRepository.totalPrices();
    }

    @Override
    public List <ToyDTO> increase(Toy toy, int quantity) throws Exception {
        List<ToyDTO> toyList = toyRepository.increase(toy,quantity).stream().map(ToyMapper::mapFromModel).toList();
        return toyList;
    }

    @Override
    public List <ToyDTO> decrease(Toy toy, int quantity) throws Exception {
        List<ToyDTO> toyList = toyRepository.increase(toy,quantity).stream().map(ToyMapper::mapFromModel).toList();
        return toyList;
    }

    @Override
    public ToyDTO findById(int id) {
        return ToyMapper.mapFromModel(toyRepository.findById(id));
    }

    @Override
    public List<ToyDTO> showByType() {
        return null;
    }


}

