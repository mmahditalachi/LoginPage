import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class HomePage {

    private JFrame MainFrame;
    private JLabel present,projects;
    private JPanel left_side,right_side,above_side;
    //right side text label
    private JTextField history;
    //left side text label
    private JLabel date;

    public HomePage()
    {
        MainFrame = new JFrame();
        left_side = new JPanel();
        right_side= new JPanel();
        above_side= new JPanel();
        present = new JLabel("Present");
        projects = new JLabel("Projects");
        date = new JLabel("Date");

        left_side.setBounds(0,300,300,700);
        right_side.setBounds(301,300,700,700);
        above_side.setBounds(0,0,1000,300);

        Border border = BorderFactory.createLineBorder(Color.black, 3);
        MainFrame.add(above_side);
        above_side.setBorder(border);
        left_side.setBorder(border);
        right_side.setBorder(border);
        MainFrame.add(left_side);
        MainFrame.add(right_side);

        MainFrameOption();
        AboveSide();
//        RightPanel();
        LeftPanel();
    }
    public void MainFrameOption()
    {
        MainFrame.setLayout(null);
        MainFrame.setLocationRelativeTo(null);
        MainFrame.setSize(1000,1000);
        MainFrame.setVisible(true);
        MainFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    public void LeftPanel()
    {
        left_side.setLayout(null);
        left_side.add(date);
        date.setBounds(0,0,100,50);
        date.setFont(new Font("Arial", Font.PLAIN, 20));

    }

    public void  RightPanel()
    {


    }

    public void AboveSide()
    {
        above_side.setLayout(null);

        above_side.add(present);
        present.setBounds(100,200,100,50);
        present.setFont(new Font("Arial", Font.PLAIN, 20));


        above_side.add(projects);
        projects.setBounds(220,200,100,50);
        projects.setFont(new Font("Arial", Font.PLAIN, 20));

    }
}
