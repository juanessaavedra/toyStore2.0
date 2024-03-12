package view;


import config.DatabaseConnection;
import excepctions.FirstException;
import mapping.dtos.*;
import mapping.mappers.ToyMapper;
import model.Toy;
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
                System.out.println("MENU TOYS");
                System.out.println("0. Exit \n 1. Toys for each type   \n2. Add Toys  \n 3. List Toys \n 4.Show total toys \n 5. Show total prices \n  6. Increase \n 7. Decrease \n 8. List Customers \n 9. New Customer \n 10. List Employees \n 11. New Employee \n 12. List Sails \n 13. New Sail \n 14. List Sails Details \n 15. New Sail Detail");
                option = s.next();
                switch (option) {

                    case "1" -> {
                        System.out.println("Toys for each type");
                        System.out.println(toyService.showByType());
                    }
                    case "2" -> {
                        System.out.println("ADD TOY");
                        System.out.println("Enter the name:");
                        String name = s.next();
                        System.out.println("Enter id:");
                        Integer id = Integer.valueOf(s.next());
                        System.out.println("Enter the price");
                        Integer price = Integer.valueOf(s.next());
                        System.out.println("Choose type of toy: \n 0.Female \n 1.Male \n 2.Unisex");
                        String type = s.next();
                        ToyType toyType = new ToyType(id, type);

                        toyService.addToy(new ToyDTO(name, id, price, +1, toyType));

                    }
                    case "3" -> {
                        System.out.println("LIST TOYS");
                        CompletableFuture<List<ToyDTO>> future = CompletableFuture.supplyAsync(() -> {
                            List<ToyDTO> list = toyService.listToys();
                            if (!list.isEmpty()) {
                                for (ToyDTO toys : list) {
                                    System.out.println(toys);
                                    System.out.println("Loading");
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
                        System.out.println("Enter the id");
                        Integer id = s.nextInt();
                        if (id == null) {
                            throw new FirstException("Id invalid"); //Exception lecture
                        }
                        System.out.println("Enter the amount");
                        int amount = Integer.parseInt(s.next());
                        ToyDTO toyStoreDTO = toyService.findById(id);
                        System.out.println(toyService.increase(ToyMapper.mapFromDTO(toyStoreDTO), amount));
                    }

                    case "7" -> {
                        System.out.println("DECREASE");
                        System.out.println("Enter the id toy");
                        Integer id = s.nextInt();
                        System.out.println("Enter the amount");
                        int amount = Integer.parseInt(s.next());
                        if (id == null) {
                            throw new FirstException("Id invalid");
                        }
                        ToyDTO toyStoreDTO = toyService.findById(id);
                        System.out.println(toyService.decrease(ToyMapper.mapFromDTO(toyStoreDTO), amount));
                    }

                    case "8" -> {
                        System.out.println("LIST CUSTOMERS");
                            List<CustomersDTO> listCustomers = customerService.list();
                            for (CustomersDTO customersDTO : listCustomers) {
                                System.out.println(customersDTO);
                            }


                    }

                    case "9" -> {
                        System.out.println("NEW CUSTOMER");
                        System.out.println("Enter your name");
                        String name = s.next();
                        System.out.println("Enter your new user");
                        String user = s.next();
                        System.out.println("Enter your password");
                        String password = s.next();
                        System.out.println("Enter your birthday date");
                        String birthday = s.next();
                        System.out.println("Enter your gender");
                        String gender = s.next();
                        /*int idNewCustomer =
                                customerService.save(CustomersDTO.builder()
                                        .name(name)
                                        .user(user)
                                        .password(password)
                                        .birthdayDate(birthday.)
                                        .gender(gender)
                                        .build()); */

                    }
                    case "10" -> {
                        System.out.println("LIST EMPLOYEES");
                        List<EmployeesDTO> listEmployees = employeesService.list();
                        for (EmployeesDTO employeesDTO : listEmployees) {
                            System.out.println(employeesDTO);
                        }
                    }
                    case "11" -> {
                        System.out.println("NEW EMPLOYEE");
                    }
                    case "12" -> {
                        System.out.println("LIST SALES");
                        List<SalesDTO> listSales = salesService.list();
                        for (SalesDTO salesDTO : listSales) {
                            System.out.println(salesDTO);
                        }
                    }
                    case "13" -> {
                        System.out.println("NEW SALE");

                    }
                    case "14" -> {
                        System.out.println("LIST SALES DETAILS");
                        List<SalesDetailsDTO> listSailsDetails = salesDetailsService.list();
                        for (SalesDetailsDTO salesDetailsDTO : listSailsDetails) {
                            System.out.println(salesDetailsDTO);
                        }
                    }
                    case "15" -> {
                        System.out.println("NEW SALES DETAILS");
                    }
                    default -> System.out.println("Invalid option");

//
                }
            } while (!option.equals("0")) ;
        }

        catch (SQLException e) {
            e.printStackTrace();
        }
    }







}

