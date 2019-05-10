import javax.swing.*;
import java.sql.*;

public class Mysql {

    public static Connection dbConnection()
    {
        String host = "jdbc:mysql://localhost:3306/users";
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

//    public void Conncetion(){
//        String host = "jdbc:mysql://localhost:3306/portal";
//        String user = "root";
//        String pass = "mohitala90990";
//        try {
//            Class.forName("com.mysql.cj.jdbc.Driver");
//            Connection connection = DriverManager.getConnection(host, user, pass);
//            Statement stmt = connection.createStatement();
//            String sqlSelect = "SELECT  * " +
//                    "FROM   Users ";
//
//            ResultSet rs = stmt.executeQuery(sqlSelect);
//            while(rs.next()) {
//                String first_name= rs.getString("first_name");
//                String last_name= rs.getString("last_name");
//                String email = rs.getString("email");
//                String password = rs.getString("password");
//                int id = rs.getInt("id");
//            }
//        } catch (
//                SQLException e) {
//            e.printStackTrace();
//        } catch (ClassNotFoundException b) {
//            b.printStackTrace();
//        }
//    }
}
