import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.Statement;

public class NewTerm_window {

    private JFrame MainFrame;
    private JLabel name_table;
    private JTextField name_table_field;
    private JButton create_table;
    Connection connection = null;
    Statement stmt =null;


    public NewTerm_window() {
        MainFrame = new JFrame();

        name_table = new JLabel("نام جدول");
        name_table.setBounds(160,30,200,50);
        name_table.setFont(new Font("Arial", Font.PLAIN, 20));

        name_table_field = new JTextField();
        name_table_field.setBounds(50,30,100,50);
        name_table_field.setFont(new Font("Arial", Font.PLAIN, 20));

        create_table = new JButton("ساخت جدول");
        create_table.setBounds(50,100,200,50);
        create_table.setFont(new Font("Arial", Font.PLAIN, 20));

        MainFrame.add(create_table);
        MainFrame.add(name_table);
        MainFrame.add(name_table_field);
        MainFrameOption();
        Create_btn();
    }
    public void MainFrameOption()
    {
        MainFrame.setLayout(null);
        MainFrame.setResizable(false);
        MainFrame.setLocationRelativeTo(null);
        MainFrame.setSize(300,200);
        MainFrame.setVisible(true);
        MainFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
    private void Create_btn()
    {
        create_table.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CreateTable();
            }
        });
    }

    private void CreateTable()
    {
        String name = name_table_field.getText();
        connection = Mysql_Student_Schema.dbConnection();
        String sql = "CREATE TABLE IF NOT EXISTS "+ " "+ name+"(firstname varchar (255),lastname varchar (255),email varchar (255),Presents varchar (255),id int PRIMARY KEY,project varchar (255))";
        System.out.println(sql);
        try {
            stmt = connection.createStatement();
            stmt.executeUpdate(sql);

        }catch (Exception e)
        {
            JOptionPane.showMessageDialog(null,e.getMessage());
        }
    }
}
