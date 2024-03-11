package services;

import mapping.dtos.ToyDTO;
import model.Toy;

import java.util.List;

public interface ToyService {


    List<ToyDTO> addToy (ToyDTO toy) throws  Exception;
    List<ToyDTO> listToys();



    boolean verifyToyExists (int id);

    Integer totalToys () throws Exception;

    double totalPrices () throws Exception;

    List <ToyDTO> increase (Toy toy, int quantity)throws Exception;

    List <ToyDTO> decrease (Toy toyDTO, int quantity) throws Exception;

    ToyDTO findById (int id);

    List<ToyDTO> showByType();











}
