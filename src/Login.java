import javax.jws.soap.SOAPBinding;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Login {

    public static boolean user_correct=false,pass_correct=false;
    public static List<Users> user;
    public static int Number;
    Connection connection = null;
    public static String Username =" ";
    private JFrame MainFrame;
    private JButton login,sign_up;
    private JTextField username,pass;
    private JLabel username_title,pass_title,pass_label;


    public Login() {
        MainFrame = new JFrame("Login");
        login = new JButton("Login");
        sign_up = new JButton("Sign Up");
        username = new JTextField();
        pass = new JTextField();
        username_title = new JLabel("Email :");
        pass_label = new JLabel("Password :");
        pass_title = new JLabel();

        ReadUserData();
        Login_btn();
        GUI();
        SignUp_btn();
    }
    public void SignUp_btn()
    {
        sign_up.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SignUp signUp = new SignUp();
            }
        });
    }

    public void GUI()
    {

        MainFrame.setSize(600,500);
        MainFrame.setVisible(true);
        MainFrame.setLocationRelativeTo(null);
        MainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        MainFrame.setResizable(false);

        MainFrame.add(login);
        MainFrame.add(pass_label);
        MainFrame.add(username);
        MainFrame.add(username_title);
        MainFrame.add(pass);
        MainFrame.add(sign_up);
        MainFrame.add(pass_title);


        username_title.setBounds(80,120,150,50);
        username.setBounds(200,120,200,50);
        username_title.setFont(new Font("Arial", Font.PLAIN, 20));

        pass_label.setBounds(80,200,150,50);
        pass_label.setFont(new Font("Arial", Font.PLAIN, 20));
        pass.setBounds(200,200,200,50);

        login.setBounds(150,300,150,50);
        login.setFont(new Font("Arial", Font.PLAIN, 20));

        sign_up.setBounds(310,300,150,50);
        sign_up.setFont(new Font("Arial", Font.PLAIN, 20));

    }
    public void SearchUsers()
    {
        for (int i = 0; i < user.size(); i++) {
            if(username.getText().equals(user.get(i).getEmail()) && !username.getText().equals("admin")){
                user_correct =true;
                Number = i;
                Username = user.get(i).getEmail();
                break;
            }
        }
        for (int i = 0; i < user.size(); i++) {
            if(pass.getText().equals(user.get(i).getPassword()) && !pass.getText().equals("admin")){
                pass_correct=true;
                break;
            }
        }
        if (pass.getText().equals("admin") && username.getText().equals("admin"))
        {
            Username = "admin";
            SelectSide selectSide = new SelectSide();
        }
    }
    public void Login_btn()
    {
            login.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    SearchUsers();
                    if(pass_correct && user_correct) {
                        SelectSide selectSide = new SelectSide();
                        pass_correct=false;
                        user_correct=false;
                    }
                    else if(pass_correct ==false && user_correct==false && !Username.equals("admin")){
                        JOptionPane.showMessageDialog(null,"ایمیل یا پسورد اشتباه وارد شده است");}
                    }
            });
    }
    public void ReadUserData()
    {
        user = new ArrayList<>();
        connection = Mysql.dbConnection();
        String sql = "Select * from userinfo";
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()) {
                String first_name= rs.getString("first_name");
                String last_name= rs.getString("last_name");
                String email = rs.getString("email");
                String password = rs.getString("password");
                int id = rs.getInt("id");

                Users u = new Users(first_name,last_name,email,password,id);
                user.add(u);
            }
        }catch (Exception c)
        {
            JOptionPane.showMessageDialog(null,"cant to read your information");
        }
    }

}
