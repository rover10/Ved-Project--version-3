import java.sql.Connection;  
import java.sql.DriverManager;  
import java.sql.ResultSet;  
import java.sql.Statement;  
  
/** 
 * @author www.javaworkspace.com 
 *  
 */  
public class Test2 {  
    public static void main(String[] args) {  
        try {  
            DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());  
            Connection connection = DriverManager.getConnection(  
                    "jdbc:oracle:thin:@localhost:1521:XE", "system", "tiger");  
  
            Statement statement = connection.createStatement();  
            ResultSet resultSet = statement  
                    .executeQuery("SELECT first FROM EMPLOYEE");  
            while (resultSet.next()) {  
                System.out.println("EMPLOYEE NAME:"  
                        + resultSet.getString("first"));  
            }  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
    }  
} 