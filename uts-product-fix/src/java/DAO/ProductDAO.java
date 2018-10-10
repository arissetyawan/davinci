/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.Product;
import model.koneksi;
/**
 *
 * @author XXVII
 */
public class ProductDAO {
    private Connection con;
    
    public ProductDAO(){
        koneksi konek = new koneksi();
        try {
            con = konek.getConnection();
        } catch (SQLException e) {
            System.out.println("error, "+e);
        }
        
    }
    
    public boolean addProduct(Product p){
        try {
            String query = "insert into product(name, category_id, price, stock) "
                    + "values('"+p.getName()+"', "
                    + "'"+p.getCategory_id()+"', "
                    + "'"+p.getPrice()+"', "
                    + "'"+p.getStock()+"')";
            
            Statement st = con.createStatement();
            st.executeUpdate(query);
            st.close();
        
        } catch (SQLException ex) {
            System.out.println("gagal insert, "+ex);
        }
        return true;
    }
    
    public boolean removeProduct(int id){
        String query = "delete from product where product_id='"+id+"'";
        try {
            Statement st = con.createStatement();
            st.executeUpdate(query);
            st.close();
            
        } catch (SQLException e) {
            System.out.println("gagal hapus, "+e);
        }
        return true;
    }    
    
    public boolean updateProduct(Product p){
        String query = "update product set name='"+p.getName()+"',"
                + "category_id='"+p.getCategory_id()+"',"
                + "price='"+p.getPrice()+"',"
                + "stock='"+p.getStock()+"',"
                + "updated_at=CURRENT_TIMESTAMP"
                + " where product_id='"+p.getProduct_id()+"'";
        try {
            Statement st = con.createStatement();
            st.executeUpdate(query);
            st.close();
        
        } catch (SQLException e) {
            System.out.println("gagal update, "+e);
        }
        
        return true;
    }
    
    public ArrayList<Product> getProducts() throws SQLException{
        String query = "select p.product_id, p.name as name, c.category_name as category, p.price as price, "
                + "p.stock as stock, p.updated_at as updated_at "
                + "from product p inner join category c on p.category_id = c.category_id;";
        ArrayList<Product> products = new ArrayList<>();
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(query);
        
        while(rs.next()){
            Product p = new Product();
            p.setProduct_id(Integer.parseInt(rs.getString("product_id")));
            p.setName(rs.getString("name"));
            p.setCategory_id(rs.getString("category"));
            p.setPrice(Float.parseFloat(rs.getString("price")));
            p.setStock(Integer.parseInt(rs.getString("stock")));
            p.setUpdated_at(rs.getString("updated_at"));
            
            products.add(p);
        }
        st.close();
        rs.close();
        return products;
    }
    
    public Product getProductByID(int id) throws SQLException{
        String query = "select * from product where product_id='"+id+"'";
        
        Product p = new Product();
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(query);
        if(rs.next()){
            p.setCategory_id(rs.getString("category_id"));
            p.setName(rs.getString("name"));
            p.setPrice(Float.parseFloat(rs.getString("price")));
            p.setStock(Integer.parseInt(rs.getString("stock")));
        }
        st.close();
        rs.close();
               
        return p;
    }
    
    
}
