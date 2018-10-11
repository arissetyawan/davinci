/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author arissetyawan.email@gmail.com
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MyConnection {
    public int id;
    public Statement state;
    public Connection conn;

    public MyConnection() {
    }
    
    public Statement stateOpen() throws SQLException{
       this.state = this.conn().createStatement();
       return this.state;
    }

    public void stateClose() throws SQLException{
        System.out.println("Closing statement...");
        this.state.close();
        System.out.println(this.state);
        if(this.state==null) {
            System.out.println("Closed");
        }
        System.out.println("Closing connection...");
        this.conn.close();
        if(this.conn==null)  {
            System.out.println("Closed");
        }

    }

    public Connection conn(){
        try {
            System.out.println("Connecting...");
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
            this.conn= DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/jspmvcjdbc", "root", "root");
            System.out.println("Connected");
        } catch (SQLException ex) {
            Logger.getLogger(MyConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return this.conn;
    }

}
