//Program to Insert Data

import java.sql.PreparedStatement;
import java.sql.Connection;  
import java.sql.DriverManager;  
import java.sql.ResultSet;  
import java.sql.Statement;  
  
/** 
 * @author www.javaworkspace.com 
 *  
 */  
public class Test3 {  
    public static void main(String[] args) {  
        try {  
            DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());  
            Connection connection = DriverManager.getConnection(  
                    "jdbc:oracle:thin:@localhost:1521:XE", "system", "tiger");  

PreparedStatement pstmt = 
      connection.prepareStatement ("insert into EMPLOYEE (ID, AGE,FIRST,LAST) values (?, ?,?,?)");

    // Add LESLIE as employee number 1500
    pstmt.setInt (1, 8);          
    pstmt.setInt (2, 26);          
    pstmt.setString (3, "JOHN");   
    pstmt.setString (4, "CENA");   
    pstmt.execute ();
    pstmt.close();  
    connection.close();
 }
catch(Exception e){
System.out.println("Exception Occured !");
}
}
} 