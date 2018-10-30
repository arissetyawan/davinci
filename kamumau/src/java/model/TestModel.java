/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author arissetyawan.email@gmail.com
 */
public class TestModel{
   
    public static void main(String args[]) throws SQLException{
        /*
        Person p= new Person();
        p.find(1);
        p.setName("Jonox");
        p.create();
        System.out.println(System.currentTimeMillis());
        System.out.println(p.all());
        Order o = new Order();
        o.setUser(1);
        o.initOrCeate(2);
        o.find(1);
        o.setUser(1);
        o.initOrCeate(21);
        o.find(5);
        */
        Product p = new Product();
        //Category c = new Category();
        //List<Product> products = p.getAllProducts();
        
        System.out.println(p.getProductByName("ci"));

    }
}