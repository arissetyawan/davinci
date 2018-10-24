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
    private String categories="categories";
    public String name;
    public int category_id;
    public String description;

    
    
    public int getId() {
        return id;
    }
    
    public String getName() {
        return name;
    }

    public int getCategory_id() {
        return category_id;
    }

    public String getDescription() {
        return description;
    }

    public void setId(int id) {
        this.id = id;
    }
    public void setParentId(int category_id) {
        this.category_id = category_id;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public Category find(int id){
        Category category = new Category();
        String query = "SELECT * FROM " + categories + " WHERE id = " + id + " ";
        try {
            Statement stmt = this.conn().createStatement();
            ResultSet res = stmt.executeQuery(query);
            if (res.next()) {
                category.setName(res.getString("name"));
                category.setParentId(res.getInt("category_id"));
                category.setId(res.getInt("id"));
                category.setDescription(res.getString("description"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return category;
    }
    
    public Category Search(String name){
        Category category = new Category();
        String query = "SELECT * FROM " + categories + " WHERE name = " + name + " ";
        try {
            Statement stmt = this.conn().createStatement();
            ResultSet res = stmt.executeQuery(query);
            if (res.next()) {
                category.setName(res.getString("name"));
                category.setParentId(res.getInt("category_id"));
                category.setId(res.getInt("id"));
                category.setDescription(res.getString("description"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return category;
    }

    public boolean create() {
        boolean result;
        String query = "INSERT INTO "+ categories +"(name, category_id, description) values ('" + this.name + "', '" + this.category_id + "', '" + this.description + "')";
        try {
            result= this.stateOpen().executeUpdate(query) > 0;
            this.stateClose();
            return result;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
    
    private boolean validate(){
        boolean status= false;
        if (!"".equals(this.name) &&  !"".equals(this.category_id) &&  !"".equals(this.description)){
            status= true;
        } 
        return status;
    }
    
    public boolean update() {
        if(!validate()){
            return false;
        }
        String query = "UPDATE "+ categories + " SET name='"
        + this.name + "', category_id='" + this.category_id
        + "', description='" + this.description
        + "' WHERE id = " + this.id + " ";
        try {
            Statement stmt = this.conn().createStatement();
            return stmt.executeUpdate(query) > 0;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
    
      public boolean delete() {
        String query = "DELETE FROM " + categories + " WHERE id = " + this.id + " ";
        try {
            Statement stmt = this.conn().createStatement();
            return stmt.executeUpdate(query) > 0;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public ArrayList<Category> all(){
        String query = "SELECT * FROM " + categories;
        ArrayList<Category> categories = new ArrayList<>();
        try {
            Statement stmt = this.conn().createStatement();
            ResultSet res = stmt.executeQuery(query);
            while (res.next()) {
                Category category = new Category();
                category.setName(res.getString("name"));
                category.setParentId(res.getInt("category_id"));
                category.setDescription(res.getString("description"));
                category.setId(res.getInt("id"));
                categories.add(category);
            }
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return categories;
    }
    
    public ArrayList<Category> search(String keyword){
        String query = "SELECT * FROM " + categories + " where name = '"+keyword+"'" ;
        ArrayList<Category> categories = new ArrayList<>();
        try {
            Statement stmt = this.conn().createStatement();
            ResultSet res = stmt.executeQuery(query);
            while (res.next()) {
                Category category = new Category();
                category.setName(res.getString("name"));
                category.setParentId(res.getInt("category_id"));
                category.setDescription(res.getString("description"));
                category.setId(res.getInt("id"));
                categories.add(category);
            }
            
        } catch (SQLException e) {
            System.out.println("search() : "+e.getMessage());
        }
        return categories;
    }
}
