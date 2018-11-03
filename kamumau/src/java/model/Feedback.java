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
public class Feedback extends MyConnection {
    private String tableName="feedbacks";
    public String content;
    public int rating;
    public int order_id;
    public int seller_id;
    public int buyer_id;
    

     public Feedback() {
        super();
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
   public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public int getRating() {
        return rating;
    }
    public void setRating(int rating) {
        this.rating = rating;
    }
    public int getOrder_id() {
        return order_id;
    }
    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }
    public int getSeller_id() {
        return seller_id;
    }
    public void setSeller_id(int seller_id) {
        this.seller_id = seller_id;
    }
    public int getBuyer_id() {
        return buyer_id;
    }
    public void setBuyer_id(int buyer_id) {
        this.buyer_id = buyer_id;
    }
    
    public boolean create() {
        boolean result;
        if(!validate()){
            return false;
        }
        String query = "INSERT INTO "+ tableName +"(content, rating, order_id, seller_id, buyer_id) values ('" + this.content + "', '" + this.rating + "', '" + this.order_id + "', '" + this.seller_id + "', '" + this.buyer_id + "')";
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
        if (!"".equals(this.content) &&  !"".equals(this.rating)&&  !"".equals(this.order_id)&&  !"".equals(this.seller_id)&&  !"".equals(this.buyer_id)){
            status= true;
        } 
        return status;
    }

    public boolean update() {
        if(!validate()){
            return false;
        }
        String query = "UPDATE "+ tableName + " SET name='"
        + this.content + "', rating='" + this.rating
        + "', order_id='" + this.order_id
        + "', seller_id='" + this.seller_id
        + "', buyer_id='" + this.buyer_id        
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
        String query = "DELETE FROM " + tableName + " WHERE id = " + this.id + " ";
        try {
            Statement stmt = this.conn().createStatement();
            return stmt.executeUpdate(query) > 0;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public Feedback findFeedback(int id){
        Feedback feedbacks = new Feedback();
        String query = "SELECT * FROM " + tableName + " WHERE feedbacks = " + id + " ";
        try {
            Statement stmt = this.conn().createStatement();
            ResultSet res = stmt.executeQuery(query);
            if (res.next()) {
                feedbacks.setId(res.getInt("id"));
                feedbacks.setContent(res.getString("content"));
                feedbacks.setRating(res.getInt("rating"));
                feedbacks.setOrder_id(res.getInt("order_id"));
                feedbacks.setSeller_id(res.getInt("seller_id"));
                feedbacks.setBuyer_id(res.getInt("buyer_id"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return feedbacks;
    }

    public ArrayList<Feedback> all(){
        String query = "SELECT * FROM " + tableName;
        ArrayList<Feedback> feedbackc = new ArrayList<>();
        try {
            Statement stmt = this.conn().createStatement();
            ResultSet res = stmt.executeQuery(query);
            while (res.next()) {
                Feedback feedback = new Feedback();
                feedback.setContent(res.getString("content"));
                feedback.setRating(res.getInt("rating"));
                feedback.setOrder_id(res.getInt("order_id"));
                feedback.setSeller_id(res.getInt("seller_id"));
                feedback.setBuyer_id(res.getInt("buyer_id"));
                feedback.setId(res.getInt("id"));
                feedbackc.add(feedback);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return feedbackc;
    }
    
    
   
}