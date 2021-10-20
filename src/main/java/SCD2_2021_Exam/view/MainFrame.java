package SCD2_2021_Exam.view;

import java.awt.event.*;
import javax.swing.*;

import SCD2_2021_Exam.facade.ApiFacade;


public class MainFrame extends JFrame{
    private JLabel background;
    private Login login;
    private ImageIcon img1;
    private CalenderGui calender;

    /**
     * Sets up the view classes and the main GUI
     */
    public MainFrame(){
        login = new Login(this);
        img1 = new ImageIcon("src/main/resources/background2.jpg");
        calender = new CalenderGui(this);
        // Frame settings
        setTitle("Calender");
        setSize(1500,900);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        background = new JLabel("", img1, JLabel.CENTER);
        background.setBounds(0,0,1500,900);

        add(login.getHeading());
        add(login.getLoginPanel());
        add(calender.getCalenderPanel());
        add(background);
    }

    /**
     * Simple getter to get the login class
     * @return Login class
     */
    public Login getLoginPanel(){
        return this.login;
    }

    /**
     * Simple getter to get the calender class
     * @return Calender class
     */
    public CalenderGui getCalenderPanel() {
        return this.calender;
    }
}