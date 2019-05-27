import jdk.nashorn.internal.ir.JoinPredecessorExpression;
import sun.rmi.runtime.Log;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SelectSide {

    public static List<String> table_list;
    public static List<UserInfo> user;
    public static String ComboboxValue;
    private JFrame MainFrame;
    private JButton new_term,this_term;
    private JComboBox c1;
    Connection connection =null;
    Connection connection_user = null;

    public SelectSide() {
        MainFrame = new JFrame();
        MainFrame.setBackground(Color.white);

        if(!Login.Username.equals("admin")){
            this_term = new JButton("انتخاب ترم");
            this_term.setBounds(50,30,200,50);
            this_term.setFont(new Font("Arial", Font.PLAIN, 20));
            MainFrame.add(this_term);
        }
        else {
            this_term = new JButton("انتخاب ترم");
            this_term.setBounds(270,100,200,50);
            this_term.setFont(new Font("Arial", Font.PLAIN, 20));
            new_term = new JButton("ترم جدید");
            new_term.setBounds(50,100,200,50);
            new_term.setFont(new Font("Arial", Font.PLAIN, 20));
            MainFrame.add(new_term);
            MainFrame.add(this_term);
        }

        MainFrameOption();
        Table_names();
        ComboBox();
        Check_btn();
    }

    public void ComboBox()
    {
        for (int i = 0; i < table_list.size(); i++) {
            if(table_list.get(i).equals("userinfo")){
                table_list.remove(i);
            }
        }for (int i = 0; i < table_list.size(); i++) {
            if(table_list.get(i).equals("sys_config")){
                table_list.remove(i);
            }
        }
        String[] table_name_array = new String[table_list.size()];
        if(table_list.size()!=0){
            for (int i = 0; i < table_list.size(); i++) {
                table_name_array[i] = table_list.get(i);
            }
        }
        c1 = new JComboBox(table_name_array);
        c1.setBounds(270,30,200,50);
        c1.setBackground(Color.red);

        MainFrame.add(c1);
    }

    private void Table_names(){
        table_list = new ArrayList<>();
        connection = Mysql_Student_Schema.dbConnection();
        try{
            DatabaseMetaData md = connection.getMetaData();
            ResultSet rs = md.getTables(null,null,null,new String[]{"Table"});
            while (rs.next())
            {
                String TableName = rs.getString("TABLE_NAME");
                table_list.add(TableName);
            }
            table_list.size();
        }catch (Exception e)
        {
            JOptionPane.showMessageDialog(null,e.getMessage());
        }
    }

    public void MainFrameOption()
    {
        MainFrame.setLayout(null);
        MainFrame.setResizable(false);
        MainFrame.setLocationRelativeTo(null);
        MainFrame.setSize(500,250);
        MainFrame.setVisible(true);
        MainFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    private void Check_btn()
    {
        if(Login.Username.equals("admin")) {
            new_term.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (Login.Username.equals("admin")) {
                        MainFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                        NewTerm_window newTerm_window = new NewTerm_window();
                    }
                }
            });
        }

        this_term.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ComboboxValue = (String) c1.getSelectedItem();
                MainFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                if (!Login.Username.equals("admin")) {
                    Add_user();
                    Searching();
                }
                else{
                    HomePage homePage = new HomePage();}
            }
        });
    }

    private void Searching()
    {
        boolean checker = false;
        for (int i = 0; i < user.size(); i++) {
            if(Login.Username.equals(user.get(i).getEmail())){
                HomePage homePage = new HomePage();
                checker = true;
                break;
            }
        }
        if(checker == false)
            Insert();
    }

    private void Insert()
    {
        int Number = Login.Number;
        Connection connection = Mysql_Student_Schema.dbConnection();
        PreparedStatement pst = null;
        String sql = "Insert into" + " "+ ComboboxValue + "(firstname,lastname,email,id,presents) values (?,?,?,?,?)" ;
        try {
            pst = connection.prepareStatement(sql);
            pst.setString(1,Login.user.get(Number).getFirst_name());
            pst.setString(2,Login.user.get(Number).getLast_name());
            pst.setString(3,Login.user.get(Number).getEmail());
            pst.setInt(4,Login.user.get(Number).getId());
            pst.setString(3,Integer.toString(0));
            pst.execute();

        }catch (Exception e){
            JOptionPane.showMessageDialog(null,e.getMessage());
        }
        HomePage homePage = new HomePage();
    }

    private void Add_user(){
        user = new ArrayList<>();
        connection_user = Mysql_Student_Schema.dbConnection();
        String sql = "Select * from" + " " + ComboboxValue;
        try{
            Statement st = connection_user.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()){
                String first_name= rs.getString("firstname");
                String last_name= rs.getString("lastname");
                String email = rs.getString("email");
                String present = rs.getString("presents");
                int id = rs.getInt("id");

                UserInfo u = new UserInfo(first_name,last_name,email,present,id);
                user.add(u);
            }
        }catch (Exception e)
        {
            JOptionPane.showMessageDialog(null,e.getMessage());
        }

    }

}
