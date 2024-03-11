package view;


import config.DatabaseConnection;
import excepcions.SecondException;
import mapping.dtos.*;
import mapping.mappers.ToyMapper;
import model.Customers;
import model.ToyType;
import repository.Repository;
import services.ToyService;
import services.impl.Customers.CustomersServiceImpl;
import services.impl.Employees.EmployeesServiceImpl;
import services.impl.Sales.SalesServiceImpl;
import services.impl.SalesDetail.SalesDetailsServiceImpl;
import services.impl.Toy.ToyServiceImpl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.CompletableFuture;

public class ToyStore {
    public static void main(String[] args) throws Exception {

        try(Connection conn = DatabaseConnection.getInstance()){

            String option = "1";
            ToyService toyService = new ToyServiceImpl();
            Repository<CustomersDTO> customerService = new CustomersServiceImpl();
            Repository<EmployeesDTO> employeesService = new EmployeesServiceImpl();
            Repository<SalesDTO> salesService = new SalesServiceImpl();
            Repository<SalesDetailsDTO> salesDetailsService = new SalesDetailsServiceImpl();



            do {
                Scanner s = new Scanner(System.in);
                System.out.println("MENU");
                System.out.println("0. Exit \n 1. Toys for each type   \n2. Add Toys  \n 3. List Toys \n 4.Show total toys \n 5. Show total prices \n  6. Increase \n 7. Decrease");
                option = s.next();
                switch (option) {

                    case "1" -> {
                        System.out.println("Toys for each type");
                        System.out.println("Enter the toy name");
                        String name = s.next();
                        System.out.println(toyService.showByType());
                    }
                    case "2" -> {
                        System.out.println("ADD TOY");
                        System.out.println("Enter the name:");
                        String name = s.next();
                        System.out.println("Enter id:");
                        String id = s.next();
                        System.out.println("Enter the price");
                        Integer price = Integer.valueOf(s.next());
                        System.out.println("Choose type of toy: \n 0.Female \n 1.Male \n 2.Unisex");

                        toyService.addToy(new ToyDTO(name, id, price, +1, new ToyType().getName()));

                    }
                    case "3" -> {
                        System.out.println("LIST TOYS");
                        CompletableFuture<List<ToyDTO>> future = CompletableFuture.supplyAsync(() -> {
                            List<ToyDTO> list = toyService.listToys();
                            if (!list.isEmpty()) {
                                for (ToyDTO toys : list) {
                                    System.out.println(toys);
                                    System.out.println("Loading...");
                                    try {
                                        Thread.sleep(5000);
                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    }
                                }
                            } else {
                                System.out.println("The list is empty");
                            }
                            return list;
                        });
                        future.join();
                        System.out.println("Service completed");

                    }
//
                    case "4"->{
                        System.out.println("SHOW TOTAL TOYS");
                        System.out.println("Total toys: "+ toyService.totalToys());
                    }

                    case "5" -> {
                        System.out.println("SHOW TOTAL PRICES");
                        System.out.println("Total prices:" + toyService.totalPrices());
                    }

                    case "6" -> {
                        System.out.println("INCREASE");
                        System.out.println("Enter the id toy");

                        String id = s.next();
                        if (id == null) {
                            throw new SecondException("Id invalid"); //Exception lectura
                        }
                        System.out.println("Entaser the amount");
                        int amount = Integer.parseInt(s.next());
                        ToyDTO toyStoreDTO = toyService.findById(id);
                        System.out.println(toyService.increase(ToyMapper.mapFromDTO(toyStoreDTO), amount));
                    }

                    case "7" -> {
                        System.out.println("DECREASE");
                        System.out.println("Enter the id toy");
                        String id = s.next();
                        System.out.println("Enter the amount");
                        int amount = Integer.parseInt(s.next());
                        if (id == null) {
                            throw new SecondException("Id invalid");
                        }
                        ToyDTO toyStoreDTO = toyService.findById(id);
                        System.out.println(toyService.decrease(ToyMapper.mapFromDTO(toyStoreDTO), amount));
                    }

//
                }
            } while (!option.equals("0")) ;
        }
            /* toyService.addToy(new ToyDTO("prueba1", 20, 100, new ToyType(1,"muebles")));
            toyService.listToys().stream().forEach(System.out::println);
            /*Repository<Product> repository = new ProductRepositoryImpl();
            System.out.println("**** List products from database");
            repository.list().stream().forEach(System.out::println);
            Thread.sleep(3000);
            System.out.println("**** Get by Id: 1");
            System.out.println(repository.byId(1).toString());
            Thread.sleep(3000);

            System.out.println("**** Save product ");
            repository.save(new Product(0, "testClase2",2.00, LocalDateTime.now(),
                    new Category(1,"muebles")));
            repository.list().stream().forEach(System.out::println);
            Thread.sleep(3000);
            System.out.println("**** Delete product 13");

            repository.delete(13);
            repository.list().stream().forEach(System.out::println);*/

        }catch (SQLException e) {
            e.printStackTrace();
        }
    }







}

