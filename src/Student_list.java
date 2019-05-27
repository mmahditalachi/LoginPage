import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;

public class Student_list {

    public Student_list() {

    }

    public DefaultTableModel reading(String sql)
    {
//        String sql = "SELECT Username,Email,Age,Score,SnakeLength FROM Scors";
        try{
            Connection connection = Mysql_Student_Schema.dbConnection();
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            ResultSetMetaData rsmd = rs.getMetaData();
            int col_num = rsmd.getColumnCount();
            DefaultTableModel dtm = new DefaultTableModel();

            for (int i = 0; i < col_num; i++) {
                dtm.addColumn(rsmd.getColumnName(i+1));
            }
            while(rs.next())
            {
                Object[] data = new Object[col_num];
                for (int i = 0; i < col_num; i++) {
                    data[i] = rs.getString(i+1);
                }
                dtm.addRow(data);

            }
            rs.close();
            stmt.close();
            connection.close();

            return dtm;
        }
        catch (SQLException c )
        {
            JOptionPane.showMessageDialog(null,c.getMessage());
        }

        return null;
    }
}
