import sun.rmi.runtime.Log;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;

public class HomePage{

    private JFrame MainFrame;
    private JLabel present,projects;
    private JPanel left_side,right_side;
    private static String id_TF;
    //right side text label
    private JLabel table_name;
    //left side text label
    private JPanel student_list_panel = new JPanel();
    private JPanel student_present_panel = new JPanel();

    private JButton update_list = new JButton("حضور غیاب");
    private JTextField present_first_name = new JTextField("نام دانشجو");
    private JTextField present_last_name = new JTextField("نام خانوادگی دانشجو");
    private JTextField present_present = new JTextField("حضوری دانشجو");

    private JButton student_present,student_list;
    private JTable table,table_present;
    Connection connection;
    CardLayout cl =  new CardLayout();


    public HomePage()
    {
        MainFrame = new JFrame();
        left_side = new JPanel();
        right_side= new JPanel();

        left_side.setBounds(0,0,700,500);
        right_side.setBounds(700,0,300,500);

        Border border = BorderFactory.createLineBorder(Color.black, 3);
        left_side.setBorder(border);
        right_side.setBorder(border);
        MainFrame.add(left_side);
        MainFrame.add(right_side);

        MainFrameOption();
        RightPanel();
        connection = Mysql.dbConnection();
        LeftPanel();
    }
    public void MainFrameOption()
    {
        MainFrame.setLayout(null);
        MainFrame.setLocationRelativeTo(null);
        MainFrame.setSize(1000,500);
        MainFrame.setVisible(true);
        MainFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        MainFrame.setResizable(false);
    }

    public void LeftPanel()
    {
        left_side.setLayout(cl);
        left_side.add(student_list_panel,"1");
        left_side.add(student_present_panel,"2");
        cl.show(left_side,"1");
        DrawTable();
    }

    public void  RightPanel()
    {
        right_side.setLayout(null);

        table_name = new JLabel("");
        table_name.setText(SelectSide.ComboboxValue);
        table_name.setBounds(100,10,200,50);
        table_name.setFont(new Font("Arial", Font.PLAIN, 20));

        student_list = new JButton("لیست دانشجویان");
        student_list.setBackground(Color.white);
        student_list.setBounds(100,70,200,50);
        student_list.setFont(new Font("Arial", Font.PLAIN, 20));

        student_present = new JButton("لیست حضور غیاب");
        student_present.setBounds(100, 140, 200, 50);
        student_present.setFont(new Font("Arial", Font.PLAIN, 20));

        right_side.add(table_name);
        right_side.add(student_list);
        if(Login.Username.equals("admin"))
            right_side.add(student_present);
    }
    private void Student_list()
    {
        table = new JTable();
        student_list_panel.setBounds(50,20,600,600);
        student_list_panel.setLayout(null);
        table.setBounds(0,0,550,450);
        student_list_panel.add(table);
    }

    private void Student_presents()
    {
        table_present = new JTable();

        update_list.setBounds(250,100,200,50);
        update_list.setFont(new Font("Arial", Font.PLAIN, 20));
        student_present_panel.add(update_list);

        present_first_name.setBounds(170,20,100,50);
        student_present_panel.add(present_first_name);

        present_last_name.setBounds(280,20,100,50);
        student_present_panel.add(present_last_name);

        present_present.setBounds(390,20,100,50);
        student_present_panel.add(present_present);

        student_present_panel.setLayout(null);
        student_present_panel.setBounds(50,20,600,600);
        table_present.setBounds(0,170,900,450);
        student_present_panel.add(table_present);
        table_present.setRowHeight(20);

        table_present.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                GetTableValue();
            }
        });

        update_list.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(id_TF);
                String sql = "update"+ " " + SelectSide.ComboboxValue + " "+ "set firstname=?,lastname=?,presents=?  where id='"+Integer.parseInt(id_TF)+"'";
                Connection connection = Mysql_Student_Schema.dbConnection();
                System.out.println(sql);
                try{
                    PreparedStatement pst = connection.prepareStatement(sql);
                    pst.setString(1,present_first_name.getText());
                    pst.setString(2,present_last_name.getText());
                    pst.setString(3,present_present.getText());
                    pst.executeUpdate();
                    pst.execute();
                }catch (Exception c){
                 JOptionPane.showMessageDialog(null,c.getMessage());
                }
            }
        });
    }

    private void GetTableValue()
    {
        int SelectedRow = table_present.getSelectedRow();
        present_first_name.setText(table_present.getValueAt(SelectedRow, 0).toString());
        present_last_name.setText(table_present.getValueAt(SelectedRow, 1).toString());
        present_present.setText(table_present.getValueAt(SelectedRow, 2).toString());
        id_TF  = table_present.getValueAt(SelectedRow,3).toString();
        System.out.println(id_TF);
    }

    public void DrawTable(){

        student_list.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Student_list();
                cl.show(left_side,"1");
                String sql ="SELECT firstname,lastname from" + " " + SelectSide.ComboboxValue;
                Student_list obj = new Student_list();
                DefaultTableModel dtm = obj.reading(sql);
                table.setModel(dtm);
            }
        });
        student_present.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Student_presents();
                cl.show(left_side,"2");
                String sql ="SELECT firstname,lastname,presents,id from" + " " + SelectSide.ComboboxValue;
                Student_list obj = new Student_list();
                DefaultTableModel dtm = obj.reading(sql);
                table_present.setModel(dtm);
            }
        });
    }
}
