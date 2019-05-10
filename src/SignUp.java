import com.sun.xml.internal.bind.annotation.OverrideAnnotationOf;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class SignUp {

    Connection connection = null;
    PreparedStatement pst = null;
    private JFrame MainFrame;
    private JLabel email,pass,first_name,last_name;
    private JTextField email_tf,pass_tf,first_name_tf,last_name_tf;
    private JButton sign_up;

    public SignUp(){

        MainFrame = new JFrame();
        email = new JLabel("Email");
        pass = new JLabel("Password");
        first_name = new JLabel("FirstName");
        last_name = new JLabel("LastName");
        first_name_tf = new JTextField();
        last_name_tf = new JTextField();
        email_tf = new JTextField();
        pass_tf = new JTextField();
        sign_up = new JButton("Sig Up");

        SignUp_btn();
        GUI();

    }

    public void GUI()
    {
        MainFrame.setSize(600,700);
        MainFrame.setVisible(true);
        MainFrame.setLocationRelativeTo(null);
        MainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        MainFrame.setResizable(false);
//        save me from set bounds
        MainFrame.setLayout(null);


        MainFrame.add(email);
        MainFrame.add(email_tf);
        MainFrame.add(pass);
        MainFrame.add(pass_tf);
        MainFrame.add(first_name);
        MainFrame.add(first_name_tf);
        MainFrame.add(last_name);
        MainFrame.add(last_name_tf);
        MainFrame.add(sign_up);

        first_name.setBounds(100,100,150,50);
        first_name.setFont(new Font("Arial", Font.PLAIN, 20));
        first_name_tf.setBounds(270,100,150,50);
        first_name_tf.setFont(new Font("Arial", Font.PLAIN, 20));

        last_name.setBounds(100,170,150,50);
        last_name.setFont(new Font("Arial", Font.PLAIN, 20));
        last_name_tf.setBounds(270,170,150,50);
        last_name_tf.setFont(new Font("Arial", Font.PLAIN, 20));

        email.setBounds(100,240,150,50);
        email.setFont(new Font("Arial", Font.PLAIN, 20));
        email_tf.setBounds(270,240,150,50);
        email_tf.setFont(new Font("Arial", Font.PLAIN, 20));

        pass.setBounds(100,310,150,50);
        pass.setFont(new Font("Arial", Font.PLAIN, 20));
        pass_tf.setBounds(270,310,150,50);
        pass_tf.setFont(new Font("Arial", Font.PLAIN, 20));

        sign_up.setBounds(220,400,150,50);
        sign_up.setFont(new Font("Arial", Font.PLAIN, 20));
    }
    public void InsertToDatabase()
    {
        connection = Mysql.dbConnection();
        String sql ="Insert into userinfo(first_name,last_name,email,password) values(?,?,?,?) ";
        try{
            pst = connection.prepareStatement(sql);
            pst.setString(1,first_name_tf.getText());
            pst.setString(2,last_name_tf.getText());
            pst.setString(3,email_tf.getText());
            pst.setString(4,pass_tf.getText());
            JOptionPane.showMessageDialog(null,"congratulation");

            pst.execute();

        }catch (Exception e)
        {
            JOptionPane.showMessageDialog(null,"cant Insert information");
        }

    }
    public void SignUp_btn()
    {
        sign_up.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (email_tf.getText().equals("") || first_name_tf.getText().equals("") || last_name_tf.getText().equals("") || pass_tf.getText().equals(""))
                {
                    JOptionPane.showMessageDialog(null,"you have to fill every textfield");
                }
                else {
                    InsertToDatabase();
                    MainFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                }
            }
        });
    }
}
