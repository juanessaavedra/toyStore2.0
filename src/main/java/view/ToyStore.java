package view;

import mapping.dtos.ToyDTO;
import model.Toy;
import model.ToyType;
import services.ToyService;
import services.impl.ToyServiceImpl;

import javax.swing.*;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Scanner;

public class ToyStore {
    public static void main(String[] args) throws Exception {

        String option = "1";
        ToyService service = new ToyServiceImpl();

        do {
            Scanner s = new Scanner(System.in);
            System.out.println("MENU");
            System.out.println("0. Exit \n 1.Search Toys  \n2. Add Toys  \n 3. List Toys \n 4.Show total toys");
            option = s.next();
            switch (option) {

                case "1" -> {
                    System.out.println("SEARCH");
                    System.out.println("Enter the toy name");
                    String name = s.next();
                    System.out.println(service.search(name));
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
                    if (!service.listToys().isEmpty()) {
                        System.out.println(service.listToys());
                    } else {
                        System.out.println("There isn't toys yet");
                    }
                }

//
                case "4"->{
                    System.out.println("SHOW TOTAL TOYS");
                    System.out.println("Total toys: "+ service.totalToys());
                }

//
            }
        } while (!option.equals("0")) ;
    }

}
