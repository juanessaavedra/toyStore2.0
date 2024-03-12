package repository.Toy;

import model.Toy;

import java.util.List;

public interface ToyRepository {


    List<Toy> listToys();

    void save (Toy t);

    void delete(Integer id);

    boolean verifyToyExists (int id);

    Integer totalToys () throws Exception;

    double totalPrices () throws Exception;

    List<Toy> increase (Toy toyDTO, int quantity)throws Exception;

    List<Toy> decrease (Toy toyDTO, int quantity) throws Exception;

    Toy findById (int id);

    List<Toy> showByType(int value);


    void update(Toy toy);
}
