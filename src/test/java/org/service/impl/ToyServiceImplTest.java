package org.service.impl;

import model.ToyType;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import services.impl.ToyServiceImpl;

import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.*;


public class ToyServiceImplTest {

    private ToyServiceImpl service;

    @Before
    public void setup() {service = new ToyServiceImpl();}

    @Test
    public void addToy_Successful() throws Exception {
        String name = "Esteban";
        ToyType type = new ToyType(1, "Femenino");
        Integer price = 0;
        Integer quantity = 1;
        String id = "10";
        ToyDTO toyDTO = new ToyDTO(name,id,price,quantity,type);
        List<ToyDTO> expected = Collections.singletonList(toyDTO);

        List<ToyDTO> result = service.addToy(toyDTO);
        assertEquals(expected,result);

    }

    @Test
    public void addToy_Exception() throws Exception {
        String name = "Esteban";
        ToyType type = ToyType.M;
        Integer price = 0;
        Integer quantity = 1;
        String id = "10";
        ToyDTO toyToAdd = new ToyDTO(name,id,price,quantity,type);
        List<ToyDTO> expected = Collections.singletonList(toyToAdd);
        List<ToyDTO> result = service.addToy(toyToAdd);
        assertEquals(expected,result);
        assertThrows(Exception.class,()->service.addToy(toyToAdd));
    }

    @Test
    public void list_Successful(){
        List<ToyDTO> toyStoreDTOList = service.listToys();
        assertNotNull(toyStoreDTOList);
        assertFalse(toyStoreDTOList.isEmpty());
    }

    @Test
    public void findByid_Successful() throws Exception {

    }

    @Test
    public void findByid_Exception() throws Exception {

    }

    @Test
    public void increase_Successful()throws Exception{
        String name = "Esteban";
        String id = "0";
        ToyType type = ToyType.M;
        Integer price = 200000;
        int quantity = 3;
        int newQuantity = 4;
        ToyDTO toyToAdd = new ToyDTO(name, id, quantity+newQuantity, price, type);
        List<ToyDTO> listExpected = Collections.singletonList(toyToAdd);
        List<ToyDTO> result = Collections.singletonList(service.increase(toyToAdd, newQuantity));
        assertEquals(listExpected,result);

    }

    @Test
    public void increase_Exception()throws Exception{
        String name = "Esteban";
        String id = "0";
        ToyType type = ToyType.M;
        Integer price = 200000;
        int quantity = 3;
        int newQuantity = 4;
        ToyDTO toyToAdd = new ToyDTO(name, id, quantity+newQuantity, price, type);
        assertThrows(Exception.class,()->service.increase(toyToAdd,newQuantity));

    }
    @Test
    public void decrease_Successful() throws Exception{
        String name = "Esteban";
        String id = "0";
        ToyType type = ToyType.M;
        Integer price = 200000;
        int quantity = 3;
        int newQuantity = 4;
        ToyDTO toyToAdd = new ToyDTO(name, id, quantity-newQuantity, price, type);
        List<ToyDTO> listExpected = Collections.singletonList(toyToAdd);
        List<ToyDTO> result = Collections.singletonList(service.decrease(toyToAdd, newQuantity));
        assertEquals(listExpected,result);

    }

    @Test
    public void showByType_Successful()throws Exception{
        Map<ToyType,Integer> mapExpected = new TreeMap<>();
        mapExpected.put(ToyType.M,1);
        Map<ToyType,Integer> result = service.showByType();
        assertEquals(mapExpected,result);
    }

    @Test
    public void showByType_Exception()throws Exception{
        service.setToyList(null);
        assertThrows(Exception.class,()->service.showByType());
    }



}
