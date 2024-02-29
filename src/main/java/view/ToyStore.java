package view;

import excepcions.SecondException;
import mapping.dtos.ToyDTO;
import model.Toy;
import model.ToyType;
import services.ToyService;
import services.impl.ToyServiceImpl;

import javax.swing.*;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.CompletableFuture;

public class ToyStore {
    public static void main(String[] args) throws Exception {

        String option = "1";
        ToyService service = new ToyServiceImpl();




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
                    System.out.println(service.showByType());
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
                    ToyType type = ToyType.fromType(Integer.parseInt(s.next()));
                    service.addToy(new ToyDTO(name, id, price, +1, type ));

                }
                case "3" -> {
                    System.out.println("LIST TOYS");
                    CompletableFuture<List<ToyDTO>> future = CompletableFuture.supplyAsync(() -> {
                        List<ToyDTO> list = service.listToys();
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
                    System.out.println("Total toys: "+ service.totalToys());
                }

                case "5" -> {
                    System.out.println("SHOW TOTAL PRICES");
                    System.out.println("Total prices:" + service.totalPrices());
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
                    ToyDTO toyStoreDTO = service.findById(id);
                    System.out.println(service.increase(toyStoreDTO, amount));
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
                    ToyDTO toyStoreDTO = service.findById(id);
                    System.out.println(service.decrease(toyStoreDTO, amount));
                }

//
            }
        } while (!option.equals("0")) ;
    }

}
