/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *CREATE TABLE orders (
    id int NOT NULL AUTO_INCREMENT,
    no varchar(10) NOT NULL,
    user_id int NOT NULL.
    created_at DATE NOT NULL,
    updated_at DATE NOT NULL,
    PRIMARY KEY (id)
);
 * @author x201
 */
public class Product extends MyConnection{
    protected int product_id;
    protected String name;
    protected String category_id;
    protected float price;
    protected int stock;
    protected String updated_at;
    protected int owner;
    
    public int user_id=2; //hardcoded user_id
            
    public Product(){
        super();
    }

    public int getOwner() {
        return owner;
    }
    
    public int getProduct_id() {
        return product_id;
    }

    public String getName() {
        return name;
    }

    public String getCategory_id() {
        return category_id;
    }

    public float getPrice() {
        return price;
    }

    public void setOwner(int owner) {
        this.owner = owner;
    }
    
    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public int getStock() {
        return stock;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCategory_id(String category_id) {
        this.category_id = category_id;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
    
    public boolean addProduct(Product p){
        
        String query = "insert into products(name, category_id, price, stock, owner) "
                    + "values('"+p.getName()+"', "
                    + "'"+p.getCategory_id()+"', "
                    + "'"+p.getPrice()+"', "
                    + "'"+p.getStock()+"',"
                    + "'"+user_id+"')";
            
        try{
            Statement st = this.conn().createStatement();
            st.executeUpdate(query);
        } catch (SQLException ex) {
            System.out.println("gagal insert, "+ex);
        }
        return true;
    }
    
    public boolean removeProduct(int id){
        String query = "delete from products where id='"+id+"'";
       try{    
            Statement st = this.conn().createStatement();
            st.executeUpdate(query);   
        } catch (SQLException e) {
            System.out.println("gagal hapus, "+e);
        }
        
        return true;
    }    
    
    public boolean updateProduct(Product p){
        String query = "update products set name='"+p.getName()+"',"
                + "category_id='"+p.getCategory_id()+"',"
                + "price='"+p.getPrice()+"',"
                + "stock='"+p.getStock()+"',"
                + "updated_at=CURRENT_TIMESTAMP"
                + " where id='"+p.getProduct_id()+"'";
       
        try (Statement st = this.conn().createStatement()) {
            st.executeUpdate(query);
        }catch (SQLException e) {
            System.out.println("gagal update, "+e);
        }
        
        return true;
    }
    
    public ArrayList<Product> getProducts() throws SQLException{
        String query = "select p.id as product_id , p.name as name, c.name as category, "
                + "p.price as price, p.stock as stock , p.updated_at as updated_at from products p inner join "
                + "categories c on p.category_id = c.category_id inner join users u on p.owner = u.id "
                + "where p.owner = "+user_id+" and stock <> 0 order by c.name, p.id"; //user_id hardcoded
        ArrayList<Product> products = new ArrayList<>();
        ResultSet rs;
        try(Statement st = this.conn().createStatement()){
            rs = st.executeQuery(query);
            while(rs.next()){
                Product p = new Product();
                p.setProduct_id(rs.getInt("product_id"));
                p.setName(rs.getString("name"));
                p.setCategory_id(rs.getString("category"));
                p.setPrice(rs.getFloat("price"));
                p.setStock(rs.getInt("stock"));
                p.setUpdated_at(rs.getString("updated_at"));
                
                products.add(p);
            }
            rs.close();
        }catch(SQLException e){
            System.err.println("getProducts() : "+e.getMessage());
        }
        return products;
    }
    
    public ArrayList<Product> getProductsZeroStock() throws SQLException{
        String query = "select p.id as product_id , p.name as name, c.name as category, "
                + "p.price as price, p.stock as stock , p.updated_at as updated_at from products p inner join "
                + "categories c on p.category_id = c.category_id inner join users u on p.owner = u.id "
                + "where p.owner = "+user_id+" and p.stock ='0' order by c.name, p.id"; //user_id hardcoded
        ArrayList<Product> products = new ArrayList<>();
        ResultSet rs;
        try(Statement st = this.conn().createStatement()){
            rs = st.executeQuery(query);
            while(rs.next()){
                Product p = new Product();
                p.setProduct_id(rs.getInt("product_id"));
                p.setName(rs.getString("name"));
                p.setCategory_id(rs.getString("category"));
                p.setPrice(rs.getFloat("price"));
                p.setStock(rs.getInt("stock"));
                p.setUpdated_at(rs.getString("updated_at"));
                
                products.add(p);
            }
            rs.close();
        }catch(SQLException e){
            System.err.println("getProducts() : "+e.getMessage());
        }
        return products;
    }
    
    public ArrayList<Product> getAllProducts() throws SQLException{
        String query = "select p.id as product_id , p.name as name, c.name as category, "
                + "p.price as price, p.stock as stock , p.updated_at as updated_at, p.owner as owner from products p "
                + "inner join categories c on p.category_id = c.category_id inner join "
                + "users u on p.owner = u.id where p.owner <> "+user_id+" and p.stock <> 0 order by c.name, p.id";
        ArrayList<Product> products = new ArrayList<>();
        ResultSet rs;
        try(Statement st = this.conn().createStatement()){
            rs = st.executeQuery(query);
            while(rs.next()){
                Product p = new Product();
                p.setProduct_id(rs.getInt("product_id"));
                p.setName(rs.getString("name"));
                p.setCategory_id(rs.getString("category"));
                p.setPrice(rs.getFloat("price"));
                p.setStock(rs.getInt("stock"));
                p.setUpdated_at(rs.getString("updated_at"));
                p.setOwner(rs.getInt("owner"));
                products.add(p);
            }
            rs.close();
        }catch(SQLException e){
            System.err.println("getProducts() : "+e.getMessage());
        }
        return products;
    }
    
    public Product getProductByID(int id) throws SQLException{
        String query = "select * from products where id='"+id+"'";
        
        Product p = new Product();
        ResultSet rs;
        try (Statement st = this.conn().createStatement()) {
            rs = st.executeQuery(query);
            if(rs.next()){
                p.setProduct_id(rs.getInt("id"));
                p.setCategory_id(rs.getString("category_id"));
                p.setName(rs.getString("name"));
                p.setPrice(rs.getFloat("price"));
                p.setStock(rs.getInt("stock"));
            }
        }
        rs.close();
               
        return p;
    }
    
    
    public ArrayList<Product> getProductByName(String param){
        ArrayList<Product> products = new ArrayList<>();
        String query = "select p.id as product_id , p.name as name, c.name as category, "
                + "p.price as price, p.stock as stock , p.updated_at as updated_at from products p "
                + "inner join categories c on p.category_id = c.category_id "
                + "where p.name like '"+param+"%' and p.owner <> "+user_id+" and p.stock <> 0 order by c.name, p.id";
        try {
            Statement stmt = this.conn().createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                Product p = new Product();
                p.setName(rs.getString("name"));
                p.setCategory_id(rs.getString("category"));
                p.setPrice(rs.getFloat("price"));
                
                products.add(p);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return products;
    }
    
    
    public ArrayList<Product> getProductByCategory(int id){
        ArrayList<Product> products = new ArrayList<>();
        String query = "select p.id as product_id , p.name as name, c.name as category, "
                + "p.price as price, p.stock as stock , p.updated_at as updated_at from products p "
                + "inner join categories c on p.category_id = c.category_id "
                + "where c.category_id like '"+id+"%' and p.owner <> "+user_id+" and p.stock <> 0 order by c.name, p.id";
        try {
            Statement stmt = this.conn().createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                Product p = new Product();
                p.setName(rs.getString("name"));
                p.setCategory_id(rs.getString("category"));
                p.setPrice(rs.getFloat("price"));
                
                products.add(p);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return products;
    }

    
}
