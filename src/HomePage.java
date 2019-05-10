import javax.swing.*;

public class HomePage {

    private JFrame MainFrame;

    public HomePage()
    {
        GUI();
    }
    public void GUI()
    {
        MainFrame = new JFrame();
        MainFrame.setLocationRelativeTo(null);
        MainFrame.setSize(600,600);
        MainFrame.setVisible(true);
        MainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
