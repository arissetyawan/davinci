/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
    
 * CREATE TABLE categories (
    id int NOT NULL AUTO_INCREMENT,
    name varchar(30) NOT NULL,
    category_id int(30),
    PRIMARY KEY (id)
); 
 */
package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author x201
 */
public class Category extends MyConnection {
    private String tableName="categories";
    public String name;
    public int category_id;

    public void setId(int id) {
        this.id = id;
    }
    public void setParentId(int category_id) {
        this.category_id = category_id;
    }
    
    public void setName(String name) {
        this.name = name;
    }

    public Category find(int id){
        Category category = new Category();
        String query = "SELECT * FROM " + tableName + " WHERE id = " + id + " ";
        try {
            Statement stmt = this.conn().createStatement();
            ResultSet res = stmt.executeQuery(query);
            if (res.next()) {
                category.setName(res.getString("name"));
                category.setParentId(res.getInt("category_id"));
                category.setId(res.getInt("id"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return category;
    }

    public boolean create() {
        boolean result;
        String query = "INSERT INTO "+ tableName +"(name, category_id) values ('" + this.name + "', '" + this.category_id + "')";
        try {
            result= this.stateOpen().executeUpdate(query) > 0;
            this.stateClose();
            return result;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }


    public ArrayList<Category> all(){
        String query = "SELECT * FROM " + tableName;
        ArrayList<Category> categories = new ArrayList<>();
        try {
            Statement stmt = this.conn().createStatement();
            ResultSet res = stmt.executeQuery(query);
            while (res.next()) {
                Category category = new Category();
                category.setName(res.getString("name"));
                category.setParentId(res.getInt("category_id"));
                category.setId(res.getInt("id"));
                categories.add(category);
            }
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return categories;
    }
}
