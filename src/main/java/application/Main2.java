package application;

import config.DatabaseConnection;

import model.Toy;
import repository.Repository;


import java.sql.Connection;
import java.sql.SQLException;

/**
public class Main2 {
    public static void main(String[] args) {
    try (Connection conn = DatabaseConnection.getInstance()) {
        Repository<Toy> repository = new ToyRepositoryImpl();
        System.out.println("*** List products from database");
        repository.list().stream().forEach(System.out::println);
        System.out.println("*** Get by Id: 1");
        System.out.println(repository.byId(1).toString());
        System.out.println("*** Insert new product");
        Toy product = new Toy();
        product.setName("Desk");
        product.setPrice(1500);
        //product.setRegistrationDate();
        repository.save(product);
        System.out.println("Results:");
        repository.list().stream().forEach(System.out::println);

        System.out.println("*** Update");
        product.setId(3);
        product.setName("Desk Gamer");
        product.setPrice(2000);
        //product.setRegistrationDate();
        repository.save(product);
        System.out.println("Updated results:");
        repository.list().stream().forEach(System.out::println);

        System.out.println("*** Delete");
        repository.delete(3);
        System.out.println("Deleted results");
        repository.list().stream().forEach(System.out::println);

    } catch (SQLException e) {
        e.printStackTrace();
    }
}
}

 **/