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
    public ToyDTO search(String name) throws Exception {
        if(verifyToyExists(name)){
            List<ToyDTO> list = toyList.stream().filter(toyList-> Objects.equals(toyList.getName(), name))
                    .findFirst().stream().map(ToyMapper::mapFrom).toList();
            return list.get(0);
        }
        throw new Exception("Not found");
    }

    @Override
    public boolean verifyToyExists(String name) {
        return toyList.stream()
                .anyMatch(t -> t.getName().equalsIgnoreCase(name));
    }


}
