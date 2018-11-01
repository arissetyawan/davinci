package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;


public class Order extends MyConnection{
    private final String tableName= "orders";
    private final String tableTransaction= "transactions";
    public int no;
    public int user_id;
    public Date created_at;
    public Date updated_at;
    private String status;
    public int buyer_id;
    public String buyer_name;
    public String user_name;
    
    public Order(int id,int no, int user_id, int buyer_id, Date created_at, Date updated_at) {
        this.no = no;
        this.id = id;
        this.buyer_id = buyer_id;
        this.user_id = user_id;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }

    public Order() {
        super();
    }

    public int getBuyer_id() {
        return buyer_id;
    }

    public void setBuyer_id(int buyer_id) {
        this.buyer_id = buyer_id;
    }

    public String getBuyer_name() {
        return buyer_name;
    }

    public void setBuyer_name(String buyer_name) {
        this.buyer_name = buyer_name;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    
    
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public Date getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(Date updated_at) {
        this.updated_at = updated_at;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    public String generateDate(){
        Date date= new Date();
        return String.format("%1$tY-%1$tm-%1$td", date);
    }
    
    public int generateNo(){
        return (int) System.currentTimeMillis();
    }
    
    
    public ArrayList<Order> all(int user_id){
        String query = "SELECT * FROM " + tableName  + " WHERE user_id = " + user_id + " ORDER BY id DESC";
        ArrayList<Order> orders = new ArrayList<>();
        try {
            Statement stmt = this.conn().createStatement();
            ResultSet res = stmt.executeQuery(query);
            while (res.next()) {
                Order order = new Order();
                order.setNo(res.getInt("no"));
                order.setCreated_at(res.getDate("created_at"));
                order.setUpdated_at(res.getDate("updated_at"));
                order.setId(res.getInt("id"));
                order.setUser_id(res.getInt("user_id"));
                order.setStatus(res.getString("status"));
                orders.add(order);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return orders;
    }
    
    
    
    
    public ArrayList<Order> allMyOrders(int user_id){
        String query = "SELECT o.id,o.no,o.user_id,o.buyer_id,u.fullname as seller_name, o.created_at, o.updated_at, o.status FROM orders o \n" +
        "INNER JOIN users u ON o.user_id = u.id WHERE o.buyer_id = "+user_id+" and STATUS = 'open' ORDER by id DESC;";    
        ArrayList<Order> orders = new ArrayList<>();
        try {
            Statement stmt = this.conn().createStatement();
            ResultSet res = stmt.executeQuery(query);
            while (res.next()) {
                Order order = new Order();
                order.setNo(res.getInt("no"));
                order.setCreated_at(res.getDate("created_at"));
                order.setUpdated_at(res.getDate("updated_at"));
                order.setId(res.getInt("id"));
                order.setUser_id(res.getInt("user_id"));
                order.setBuyer_id(res.getInt("buyer_id"));
                order.setUser_name(res.getString("seller_name"));
                order.setStatus(res.getString("status"));
                orders.add(order);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return orders;
    }

    public ArrayList<Order> allIncoming(int user_id){
        String query = "SELECT o.id,o.no,o.user_id,o.buyer_id,u.fullname as buyer_name, o.created_at, o.updated_at, o.status FROM orders o \n" +
        "INNER JOIN users u ON o.buyer_id = u.id WHERE o.user_id = "+user_id+" AND status <> 'open' and status <> 'completed' ORDER BY id DESC;";
        
        
        ArrayList<Order> orders = new ArrayList<>();
        try {
            Statement stmt = this.conn().createStatement();
            ResultSet res = stmt.executeQuery(query);
            while (res.next()) {
                Order order = new Order();
                order.setNo(res.getInt("no"));
                order.setCreated_at(res.getDate("created_at"));
                order.setUpdated_at(res.getDate("updated_at"));
                order.setId(res.getInt("id"));
                order.setUser_id(res.getInt("user_id"));
                order.setBuyer_id(res.getInt("buyer_id"));
                order.setBuyer_name(res.getString("buyer_name"));
                order.setStatus(res.getString("status"));
                orders.add(order);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return orders;
    }
    
    
    public ArrayList<Order> allOutcoming(int user_id){
        String query = "SELECT o.id,o.no,o.user_id,o.buyer_id,u.fullname as name, o.created_at, o.updated_at, o.status FROM orders o \n" +
        "INNER JOIN users u ON o.user_id = u.id WHERE o.buyer_id = "+user_id+" AND status <> 'open' AND status <> 'completed';";
        
        
        ArrayList<Order> orders = new ArrayList<>();
        try {
            Statement stmt = this.conn().createStatement();
            ResultSet res = stmt.executeQuery(query);
            while (res.next()) {
                Order order = new Order();
                order.setNo(res.getInt("no"));
                order.setCreated_at(res.getDate("created_at"));
                order.setUpdated_at(res.getDate("updated_at"));
                order.setId(res.getInt("id"));
                order.setUser_id(res.getInt("user_id"));
                order.setBuyer_id(res.getInt("buyer_id"));
                order.setUser_name(res.getString("name"));
                order.setStatus(res.getString("status"));
                orders.add(order);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return orders;
    }
    
    
    
    public ArrayList<Order> allCompletedOrders(int user_id){
        //String query = "SELECT id, no, user_id, (SELECT u.fullname from orders o INNER JOIN users u ON o.buyer_id = u.id "
        //        + ") as buyer_id FROM " + tableName  + " WHERE user_id = " + user_id + " ORDER BY id DESC";
        
        String query = "SELECT o.id,o.no,o.user_id,o.buyer_id,u.fullname as name, o.created_at, o.updated_at, o.status FROM orders o \n" +
        "INNER JOIN users u ON o.user_id = u.id WHERE o.buyer_id = "+user_id+" AND status = 'completed' ORDER BY id DESC;";
        
        
        ArrayList<Order> orders = new ArrayList<>();
        try {
            Statement stmt = this.conn().createStatement();
            ResultSet res = stmt.executeQuery(query);
            while (res.next()) {
                Order order = new Order();
                order.setNo(res.getInt("no"));
                order.setCreated_at(res.getDate("created_at"));
                order.setUpdated_at(res.getDate("updated_at"));
                order.setId(res.getInt("id"));
                order.setUser_id(res.getInt("user_id"));
                order.setBuyer_id(res.getInt("buyer_id"));
                order.setUser_name(res.getString("name"));
                order.setStatus(res.getString("status"));
                orders.add(order);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return orders;
    }
    
    
    
    private boolean validate(){
        return true;
    }

    public boolean update() {
        String now_date= generateDate();
        String query = "UPDATE "+ tableName + " SET status='"+ this.status + "', updated_at = '"+ now_date +"' WHERE id = " + this.id;
        try {
            Statement stmt = this.conn().createStatement();
            return stmt.executeUpdate(query) > 0;
        } catch (SQLException e) {
            System.out.println("update order() : " + e.getMessage());
            return false;
        }
    }
    
    public boolean delete() {
        try {
            String query = "DELETE FROM "+ tableTransaction+ " WHERE id_order = " + this.no;
            Statement stmt = this.conn().createStatement();
            stmt.executeUpdate(query);
            query = "DELETE FROM " + tableName + " WHERE id = " + this.id;
            Statement st = this.conn().createStatement();
            return st.executeUpdate(query) > 1;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
    
   
    public Order find(int no){
        String query = "SELECT * FROM " + tableName + " WHERE no = " + no + " ";
        Order order = new Order();
        try {
            Statement stmt = this.conn().createStatement();
            ResultSet res = stmt.executeQuery(query);
            if (res.next()) {
                order.setId(res.getInt("id"));
                order.setNo(res.getInt("no"));
                order.setUser_id(res.getInt("user_id"));
                order.setBuyer_id(res.getInt("buyer_id"));
                order.setStatus(res.getString("status"));
                order.setCreated_at(res.getDate("created_at"));
                order.setUpdated_at(res.getDate("updated_at"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return order;
    }

       
    public boolean create() {
        String now_date= generateDate();
        String status= "open";
        this.setNo(this.generateNo());
        String query = "INSERT INTO "+ tableName +
                "(no, user_id, buyer_id, created_at, updated_at,status) values ('" 
                + this.getNo() + "', '"+ this.user_id+"', '" + this.buyer_id  + "', '" +  now_date + "', '" + now_date + "','" + status + "')";
        //Order order = new Order();
        try {
            Statement stmt = this.conn().createStatement();
            return stmt.executeUpdate(query) > 0;
        } catch (SQLException e) {
            System.out.println("create Order() : "+e.getMessage());
        }
        return false;
    }

    
    
}
