
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author toqa khaled
 */
public class LogIn {
     public static String validate (String email,String pass) throws ClassNotFoundException, SQLException {
         boolean status= false;
         String type="";
        String url = "jdbc:mysql://localhost:3306/office_hours?autoReconnect=true&useSSL=false";
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con =  DriverManager.getConnection(url, "root", "1234");
            Statement Stmt = (Statement) con.createStatement();
            ResultSet RS = Stmt.executeQuery("SELECT * FROM student where email ='"+email+"' and password='"+pass+"'");
            status = RS.next();
            type = RS.getString("type");
        }
        catch (SQLException ex) {
      
            ex.printStackTrace();
        }
         System.out.println(type);
        return type;
    }
    
}
