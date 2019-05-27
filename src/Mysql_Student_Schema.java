import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;

public class Mysql_Student_Schema {
    public static Connection dbConnection()
    {
        String host = "jdbc:mysql://localhost:3306/Student_Term";
        String user = "root";
        String pass = "mohitala90990";

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(host, user, pass);
            JOptionPane.showMessageDialog(null,"Connection successful");
            return connection;

        }catch (Exception e)
        {
            JOptionPane.showMessageDialog(null,e);
            return null;
        }
    }
}
