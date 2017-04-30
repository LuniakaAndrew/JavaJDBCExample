/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javajdbcexamples;

import java.util.List;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author USER
 */
public class JavaJDBCExamples {
    // JDBC driver name and database URL
   static final String JDBC_DRIVER = "org.postgresql.Driver";  
   static final String DB_URL = "jdbc:postgresql://localhost:5432/Zoo";

   //  Database credentials
   static final String USER = "postgres";
   static final String PASS = "";
   
   private Connection conn = null;
   
   public JavaJDBCExamples(){
       ConnectToBase();
   }
   
   public JavaJDBCExamples(Connection conn){
   this.conn=conn;
   }
   
   public void ConnectToBase(){
    /*try{
        if(conn!=null)
            conn.close();
    }catch(SQLException se){
        se.printStackTrace();
    }*/
   try{
      //Register JDBC driver
      Class.forName("org.postgresql.Driver");

      //Open a connection
      System.out.println("Connecting to a selected database...");
      conn = DriverManager.getConnection(DB_URL, USER, PASS);
      System.out.println("Connected database successfully...");
   }
   catch(SQLException se){
      //Handle errors for JDBC
      se.printStackTrace();
   }
   catch(Exception e){
      //Handle errors for Class.forName
      e.printStackTrace();
   }finally{
     
   }
   
   }
   public void DisconnectBase(){
   try{
        if(conn!=null)
            conn.close();
    }catch(SQLException se){
        se.printStackTrace();
    }
   }
   public void SelectData(){
    Statement stmt = null;
    try{
    conn.setAutoCommit(false);
    stmt = conn.createStatement();
         ResultSet rs = stmt.executeQuery( "SELECT * FROM Employee;" );
         while ( rs.next() ) {
            int id = rs.getInt("id");
            String  name = rs.getString("name");
            System.out.println( "ID = " + id );
            System.out.println( "NAME = " + name );
            System.out.println("---------------------------------------------");
         }
         rs.close();
         stmt.close();
    } catch ( Exception e ) {
        System.err.println( e.getClass().getName()+": "+ e.getMessage() );
        System.exit(0);
      }
      System.out.println("Operation done successfully");
   }

    /**
     *
     * @return
     */
    public List<String[]> SelectDataEmployee(){
    Statement stmt = null;
    List<String[]> tableData=new ArrayList<String[]>();
    try{
    conn.setAutoCommit(false);
    stmt = conn.createStatement();
         ResultSet rs = stmt.executeQuery( "SELECT * FROM employee;" );
         while ( rs.next() ) {
            int id = rs.getInt("id");
            String  name = rs.getString("name");
            String[] temp={String.valueOf(id),name};
            tableData.add(temp);
         }
         rs.close();
         stmt.close();
    } catch ( Exception e ) {
        System.err.println( e.getClass().getName()+": "+ e.getMessage() );
        System.exit(0);
      }
      System.out.println("Operation done successfully");
      return tableData;
   }
}
