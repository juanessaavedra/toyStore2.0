package services.impl;


import mapping.dtos.ToyDTO;
import mapping.mappers.ToyMapper;
import model.Toy;
import repository.Repository;
import repository.Toy.ToyRepositoryJDBCImpl;
import services.ToyService;

import java.util.List;

public class ToyServiceImpl implements ToyService {

    private Repository<Toy> toyRepository;




    public ToyServiceImpl() {this.toyRepository = new ToyRepositoryJDBCImpl();
    }

    @Override
    public List<ToyDTO> addToy(ToyDTO toy) throws Exception {
        if (!verifyToyExists(toy.name())) {
            toyRepository.save(ToyMapper.mapFromDTO(toy));
        }
        throw new Exception("The toy has already been created");
    }

    @Override
    public List<ToyDTO> listToys() {
        return toyRepository.list()
                .stream()
                .map(ToyMapper::mapFromModel)
                .toList();
    }



    @Override
    public boolean verifyToyExists(String name) {

        return toyList.stream()
                .anyMatch(t -> t.getName().equalsIgnoreCase(name));
    }

    @Override
    public Integer totalToys() throws Exception {
        Integer addition = 0;
            for (Toy t: toyList) {
                addition+= t.getQuantity();
            }
            return  addition;
    }

    @Override
    public Integer totalPrices() throws Exception {
        Integer additionPrices = 0;
        for (Toy t: toyList) {
            additionPrices+= t.getPrice();
        }
        return  additionPrices;
    }

    @Override
    public <List> ToyDTO increase(ToyDTO toyDTO, int quantity) throws Exception {
        for (Toy toy1: toyList) {
            if (toy1.getId().equals(toyDTO.id())) {
                int newQuantity = toy1.getQuantity() + quantity;
                toy1.setQuantity(newQuantity);

                break;
            }
        }
        return toyDTO;
    }

    @Override
    public <List> ToyDTO decrease(ToyDTO toyDTO, int quantity) throws Exception {
        for (Toy toy1: toyList) {
            if (toy1.getId().equals(toyDTO.id())) {
                int newQuantity = toy1.getQuantity() - quantity;
                toy1.setQuantity(newQuantity);

                break;
            }
        }
        return toyDTO;
    }

    @Override
    public ToyDTO findById(int id) {
        List<ToyDTO> list = toyList.stream().filter(toyList-> Objects.equals(toyList.getId(), id))
                .findFirst().stream().map(ToyMapper::mapFrom).toList();
        return list.get(0);
    }


    @Override
    public Map<ToyType, Integer> showByType() throws Exception {
        if(toyList!=null) {
            Map<ToyType, Integer> showByType = new TreeMap<>();
            for (model.Toy toy : toyList) {
                ToyType type = toy.getType();
                showByType.put(type, showByType.getOrDefault(type, 0) + 1);
            }
            return showByType;
        } throw new Exception("Null");
    }




}

