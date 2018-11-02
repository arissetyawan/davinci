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
import java.util.Date;

/**
 *
 * @author ibnu
 */
public class User extends MyConnection {
    private final String tableName ="users";
    public String email;
    public String password;
    public String retpassword;
    public String fullname;
    public String address;
    public String bankname;
    public String accountno;
    public Date created_at;
    public Date updated_at;

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRetpassword(String retpassword) {
        this.retpassword = retpassword;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setBankname(String bankname) {
        this.bankname = bankname;
    }

    public void setAccountno(String accountno) {
        this.accountno = accountno;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public void setUpdated_at(Date updated_at) {
        this.updated_at = updated_at;
    }
    
    private String generateDate(){
        Date date= new Date();
        return String.format("%1$tY-%1$tm-%1$td", date);
    }
    

    public String getTableName() {
        return tableName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getRetpassword() {
        return retpassword;
    }

    public String getFullname() {
        return fullname;
    }

    public String getAddress() {
        return address;
    }

    public String getBankname() {
        return bankname;
    }

    public String getAccountno() {
        return accountno;
    }
    
    public int getId(){
        /// return id
        return this.id;
    }
    
    public void setId(int id){
        this.id=id;
    }

    public String getName(){
        return "Nama user";
    }
    
    public boolean create(){
        String now_date= generateDate();
        boolean result;
        if(!validate()){
            return false;
        }
        String query ="INSERT INTO "+ tableName+"(email,password,fullname,address,bankname,accountno,created_at) values ('"+this.email+"','"+ this.password+"','"+ this.fullname+"','"+ this.address+"','"+ this.bankname+"','"+ this.accountno+"','"+now_date+"')";
        try{
            Statement stmt = this.conn().createStatement();
            return stmt.executeUpdate(query)>0;
        }catch(SQLException e){
            System.out.println(e.getMessage());
            return false;
        }
    }
  public boolean update() {
        if(!validate()){
            return false;
        }
        String query = "UPDATE "+ tableName + " SET email='"
        + this.email + "', password='" + this.password
        + "', fullname='" + this.fullname
        + "',address='"+ this.address + "',bankname='"
        + this.bankname + "',accountno='"+ this.accountno + "' WHERE id = " + this.id + " ";
        try {
            Statement stmt = this.conn().createStatement();
            return stmt.executeUpdate(query) > 0;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
    private boolean validate() {
        boolean status = false;
        if(!"".equals(this.email)&& !"".equals(this.password)&&!"".equals(this.retpassword)&& !"".equals(this.fullname)&& !"".equals(this.fullname)&& !"".equals(this.address)&& !"".equals(this.bankname)&& !"".equals(this.accountno)){
            status = true;
        }
        return status;
    }
    
    public ArrayList<User> all(){
        String query = "SELECT * FROM " + tableName;
        ArrayList<User> users = new ArrayList<>();
        try {
            Statement stmt = this.conn().createStatement();
            ResultSet res = stmt.executeQuery(query);
            while (res.next()) {
                User user = new User();
                user.setEmail(res.getString("email"));
                user.setPassword(res.getString("password"));
                user.setFullname(res.getString("fullname"));
                user.setAddress(res.getString("address"));
                user.setBankname(res.getString("bankname"));
                user.setAccountno(res.getString("accountno"));
                user.setCreated_at(res.getDate("created_at"));
                
                user.setId(res.getInt("id"));
                users.add(user);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return users;
    }
    
    
   public User find(int id){
        User user = new User();
        String query = "SELECT * FROM " + tableName + " WHERE id = " + id + " ";
        try {
            Statement stmt = this.conn().createStatement();
            ResultSet res = stmt.executeQuery(query);
            if (res.next()) {
                user.setEmail(res.getString("email"));
                user.setPassword(res.getString("password"));
                user.setFullname(res.getString("fullname"));
                user.setAddress(res.getString("address"));
                user.setBankname(res.getString("bankname"));
                user.setAccountno(res.getString("accountno"));
                user.setCreated_at(res.getDate("created_at"));               
                user.setId(res.getInt("id"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return user;
    }

    public boolean doLogin() {
        User user = new User();
        // find to db match it by email and password;
        String query = "SELECT * FROM " + tableName + " WHERE email = '" + this.email + "' and password = '" + this.password + "'";
        // select * from user where email = this.email and password= this.password
        try {
            Statement stmt = this.conn().createStatement();
            ResultSet res = stmt.executeQuery(query);
            if (res.next()) {
                user.setEmail(res.getString("email"));
                user.setPassword(res.getString("password"));
                user.setId(res.getInt("id"));
                System.out.println("id nya"+ this.id);
                System.out.println("nya"+ res.getInt("id"));

                this.id= res.getInt("id"); //get it from db
                return true;
            }else{
                return false;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
    
    
    
    
    




    
    
}
