package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
TABLE NAME transaction

column
id INT AUTO INCREMENTS
id_order Long
id_product INT
qty INT
total INT
*/

public class Transaction extends MyConnection{
    private int id_order;
    private int id_product;
    private int qty;
    private int total;
    private String name;
    private static final String TABLE_NAME = "transactions";
    private static final String TABLE_NAME_ORDER = "orders";

    public Transaction() {
    }

    public Transaction(int id_order, int id_product, int qty, int total) {
        this.id_order = id_order;
        this.id_product = id_product;
        this.qty = qty;
        this.total = total;
    }

    public int getId_order() {
        return id_order;
    }

    public void setId_order(int id_order) {
        this.id_order = id_order;
    }

    public int getId_product() {
        return id_product;
    }

    public void setId_product(int id_product) {
        this.id_product = id_product;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public int generateNo(){
        return (int) System.currentTimeMillis();
    }
    
    public ArrayList<Transaction> all(int order_id){
        String query = "SELECT t.id, t.id_order, t.id_product, p.name, t.qty, t.total "
                + "FROM "+TABLE_NAME+" t INNER JOIN products p ON t.id_product = p.id WHERE id_order = "+ order_id;
        
        ArrayList<Transaction> transactions = new ArrayList<>();
        try {
            Statement stmt = this.conn().createStatement();
            ResultSet res = stmt.executeQuery(query);
            while (res.next()) {
                Transaction tr = new Transaction();
                tr.setId(res.getInt("id"));
                tr.setId_product(res.getInt("id_product"));
                tr.setId_order(res.getInt("id_order"));
                tr.setId(res.getInt("id"));
                tr.setQty(res.getInt("qty"));
                tr.setTotal(res.getInt("total"));
                tr.setName(res.getString("name"));
                transactions.add(tr);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return transactions;
    }
    
    public Transaction find(int id){
        String query = "SELECT * FROM " + TABLE_NAME + " WHERE id = " + id;
        Transaction tr = new Transaction();
        try {
            Statement stmt = this.conn().createStatement();
            ResultSet res = stmt.executeQuery(query);
            if (res.next()) {
                tr.setId(res.getInt("id"));
                tr.setId_product(res.getInt("id_product"));
                tr.setId_order(res.getInt("id_order"));
                tr.setId(res.getInt("id"));
                tr.setQty(res.getInt("qty"));
                tr.setTotal(res.getInt("total"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return tr;
    }
    
    
    public Transaction getExistedItem(int order_id, int product_id){
        String query = "SELECT * FROM "+TABLE_NAME+" WHERE id_order = '"+order_id+"' and id_product = '"+product_id+
                "'";
        Transaction tr = new Transaction();
        try {
            Statement stmt = this.conn().createStatement();
            ResultSet res = stmt.executeQuery(query);
            if (res.next()) {
                tr.setId(res.getInt("id"));
                tr.setId_product(res.getInt("id_product"));
                tr.setId_order(res.getInt("id_order"));
                tr.setId(res.getInt("id"));
                tr.setQty(res.getInt("qty"));
                tr.setTotal(res.getInt("total"));
            }
        } catch (SQLException e) {
            System.err.println("getExistedItem() : " + e.getMessage());
        }
        return tr;
    }
    
    
    
    public boolean itemAlreadyExists(int product_id, int order_id){
        String query = "SELECT * FROM "+TABLE_NAME+" WHERE id_order = '"+order_id+"' and id_product = '"+product_id+
                "'";
        try{
            Statement stmt = this.conn().createStatement();
            ResultSet res = stmt.executeQuery(query);
            return res.next();
        }catch(SQLException e){
            System.err.println("itemExists() : "+e.getMessage());
            return false;
        }
    }
    
    
    public boolean initOrCeate(Transaction trans, int user_id,int seller_id){
        String query = "SELECT * FROM " + TABLE_NAME_ORDER + " WHERE user_id = " + seller_id + 
                " AND buyer_id = "+user_id+" AND status='open'";
        try {
            Statement stmt = this.conn().createStatement();
            ResultSet res = stmt.executeQuery(query);
            Order order = new Order();
            if (res.next()) {
                order.setId(res.getInt("id"));
                order.setNo(res.getInt("no"));
                order.setUser_id(res.getInt("user_id"));
                order.setBuyer_id(res.getInt("buyer_id"));
                order.setStatus(res.getString("status"));
                order.setCreated_at(res.getDate("created_at"));
                order.setUpdated_at(res.getDate("updated_at"));
                order.update();
                trans.setId_order(res.getInt("no"));
                if(itemAlreadyExists(this.id_product, order.getNo())){
                    trans.updateQuantityOnly();
                }else{
                    trans.create();
                }
            }else{
                order.setUser_id(seller_id);
                order.setBuyer_id(user_id);
                order.setStatus("open");
                if(order.create()){
                   int key = order.getNo();
                   System.out.println("Generated : "+order.getNo());
                   trans.setId_order(order.getNo());
                   trans.create();
                }
            }
            return true;
        } catch (SQLException e) {
            System.out.println("initOrCreateTrans() : "+e.getMessage());
            return false;
        }
    }
    
    
    public boolean create() {
        try {
            Product product = new Product();
            product = product.getProductByID(this.getId_product());
            int price =  (int) product.getPrice();
            String query = "INSERT INTO "+ TABLE_NAME +" (id_order, id_product, qty, total) "
                + "values ('" + this.id_order + "', '" + this.id_product  + "', '" +  this.qty  + "', '"+ price + "')";

            Statement stmt = this.conn().createStatement();
            return stmt.executeUpdate(query) > 0;
        } catch (SQLException e) {
            System.out.println("craete() : "+e.getMessage());
        }
        return false;
    }
    
    
    public boolean updateQuantityOnly(){
        try {
            Product product = new Product();
            product = product.getProductByID(this.getId_product());
            int price =  (int) product.getPrice();
            Transaction tr = new Transaction();
            tr = getExistedItem(this.id_order, this.id_product);
            String query = "UPDATE "+ TABLE_NAME +  " SET qty = "+ (tr.getQty()+1) +", total = "+
                    (tr.getTotal() + price) +" WHERE id = " + tr.getId() +"";    
            System.out.println(query);
            Statement stmt = this.conn().createStatement();
            return stmt.executeUpdate(query) > 0;
        } catch (SQLException e) {
            System.out.println("updateQuantitty() : "+ e.getMessage());
            return false;
        }
    }
    
    
    
    public boolean update() {
        try {
            Product product = new Product();
            product = product.getProductByID(this.getId_product());
            int price =  (int) product.getPrice();
            price *= this.qty;
            String query = "UPDATE "+ TABLE_NAME + " SET id_product='"+ this.id_product + 
                "', qty = '"+ this.qty +"', total = '"+price+"' WHERE id = " + this.id;
            Statement stmt = this.conn().createStatement();
            return stmt.executeUpdate(query) > 0;
        } catch (SQLException e) {
            System.out.println("update() : "+ e.getMessage());
            return false;
        }
    }
    
    public boolean delete() {
        String query = "DELETE FROM " + TABLE_NAME + " WHERE id = " + this.id + " ";
        try {
            Statement stmt = this.conn().createStatement();
            return stmt.executeUpdate(query) > 1;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
    
    
}
