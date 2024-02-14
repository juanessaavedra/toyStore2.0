package services;

import mapping.dtos.ToyDTO;

import java.util.List;
import java.util.Optional;

public interface ToyService {


    List<ToyDTO> addToy (ToyDTO toy) throws  Exception;
    List<ToyDTO> listToys();

    ToyDTO eachType (Integer quantity) throws Exception;

    boolean verifyToyExists (String name);

    Integer totalToys () throws Exception;

    Integer totalPrices () throws Exception;







}
