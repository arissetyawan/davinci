/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author XXVII
 */
public class Product {
    protected int product_id;
    protected String name;
    protected String category_id;
    protected float price;
    protected int stock;
    protected String updated_at;
    
    public Product(){
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
    
    
    
}
