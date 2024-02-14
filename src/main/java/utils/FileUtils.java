package utils;

import model.Toy;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileUtils {

    public static void saveContacts(File file, List<Toy> lista) {

        try {
            FileOutputStream fileOut = new FileOutputStream(file);
            ObjectOutputStream objOut = new ObjectOutputStream(fileOut);
            objOut.writeObject(lista);
            objOut.close();
        } catch (FileNotFoundException e) {
            System.out.println("File doesn't exist");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static List<Toy> getToys(File file) {

        List<Toy> list = new ArrayList<>();
        try {
            FileInputStream fileIn = new FileInputStream(file);
            ObjectInputStream objIn = new ObjectInputStream(fileIn);
            list = (List<Toy>) objIn.readObject();
            objIn.close();
        } catch (FileNotFoundException e) {
            System.out.println("File doesn't exist");
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return list;
    }



}
