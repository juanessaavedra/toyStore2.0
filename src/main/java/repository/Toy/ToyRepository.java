package repository.Toy;

import model.Toy;

import java.util.List;

public interface ToyRepository {


    List<Toy> addToy (Toy toy) throws  Exception;
    List<Toy> listToys();



    boolean verifyToyExists (String name);

    Integer totalToys () throws Exception;

    Integer totalPrices () throws Exception;

    <List> Toy increase (Toy toyDTO, int quantity)throws Exception;

    <List> Toy decrease (Toy toyDTO, int quantity) throws Exception;

    Toy findById (int id);

    /**Map<ToyType, Integer> showByType() throws Exception;**/











}
