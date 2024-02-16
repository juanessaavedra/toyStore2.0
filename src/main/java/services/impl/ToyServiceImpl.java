package services.impl;

import mapping.dtos.ToyDTO;
import mapping.mappers.ToyMapper;
import model.Toy;
import services.ToyService;
import utils.Constants;
import utils.FileUtils;

import java.io.File;
import java.util.*;

public class ToyServiceImpl implements ToyService {

    private List<Toy> toyList;

    public ToyServiceImpl() {
            toyList = FileUtils.getToys(new File(Constants.PATH_TOYS));
        }

    @Override
    public List<ToyDTO> addToy(ToyDTO toy) throws Exception {
        if (!verifyToyExists(toy.name())) {
            toyList.add(ToyMapper.mapFrom(toy));
            toyList.add(ToyMapper.mapFrom(toy));
            FileUtils.saveContacts(new File(Constants.PATH_TOYS), toyList);
            return toyList.stream().map(ToyMapper::mapFrom).toList();
        }
        throw new Exception("The toy has already been created");
    }

    @Override
    public List<ToyDTO> listToys() {
        return toyList.stream().map(ToyMapper::mapFrom).toList();
    }

    @Override
    public ToyDTO eachType(Integer quantity) throws Exception {
        return null;
    };


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
                int nuevaCantidad = toy1.getQuantity() + quantity;
                toy1.setQuantity(nuevaCantidad);

                break;
            }
        }
        return toyDTO;
    }

    @Override
    public <List> ToyDTO decrease(ToyDTO toyDTO, int quantity) throws Exception {
        for (Toy toy1: toyList) {
            if (toy1.getId().equals(toyDTO.id())) {
                int nuevaCantidad = toy1.getQuantity() - quantity;
                toy1.setQuantity(nuevaCantidad);

                break;
            }
        }
        return toyDTO;
    }

    @Override
    public ToyDTO findById(String id) {
        List<ToyDTO> list = toyList.stream().filter(toyList-> Objects.equals(toyList.getId(), id))
                .findFirst().stream().map(ToyMapper::mapFrom).toList();
        return list.get(0);
    }


}
