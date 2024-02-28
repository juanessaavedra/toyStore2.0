package services;

import mapping.dtos.ToyDTO;
import model.Toy;
import model.ToyType;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface ToyService {


    List<ToyDTO> addToy (ToyDTO toy) throws  Exception;
    List<ToyDTO> listToys();



    boolean verifyToyExists (String name);

    Integer totalToys () throws Exception;

    Integer totalPrices () throws Exception;

    <List> ToyDTO increase (ToyDTO toyDTO, int quantity)throws Exception;

    <List> ToyDTO decrease (ToyDTO toyDTO, int quantity) throws Exception;

    ToyDTO findById (String id);

    Map<ToyType, Integer> showByType() throws Exception;










}
