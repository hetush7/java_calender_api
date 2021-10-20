package SCD2_2021_Exam.view;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import SCD2_2021_Exam.facade.ApiFacade;


public class Login {
    
    private JLabel name;
    private JLabel subtitle;
    private JPanel heading;
    private JPanel loginPanel;

    private JTextField country;

    private JButton loginButton;

    private JComboBox threshold;

    String values[] = { "1", "2", "3", "4", "5" };

    /**
     * GUI setup for the Login panel and the heading
     * @param frame Main frame
     */
    public Login(MainFrame frame) {
        //[2]
        //Heading
        Font f = new Font(Font.MONOSPACED, Font.BOLD, 60);
        Font f2 = new Font(Font.MONOSPACED, Font.BOLD, 20);
        heading = new JPanel();
        heading.setBackground(new Color(0,0,0,0));
        heading.setBounds(275,150,900,120);
        name = new JLabel("SetUp Page");
        name.setBounds(275,150,400,50);
        name.setFont(f);
        name.setForeground(Color.WHITE);
        heading.add(name);

        // Login page button
        loginPanel = new JPanel();
        loginPanel.setLayout(null);
        loginPanel.setSize(400,350);
        loginPanel.setBackground(new Color(0,0,0,40));
        loginPanel.setBounds(525,300,400,350);

        subtitle = new JLabel("Threshold Holiday Count :");
        subtitle.setBounds(50,150,400,30);
        subtitle.setFont(f2);
        subtitle.setForeground(Color.black);
        loginPanel.add(subtitle);

        threshold = new JComboBox<String>(values);
        threshold.setBackground(Color.WHITE);
        threshold.setBounds(125,200,115,30);
        loginPanel.add(threshold);

        // Textfield for password
        country = new JTextField("Enter 2 letter region code to continue");
        country.setBackground(Color.WHITE);
        country.setBounds(50,50,300,50);
        loginPanel.add (country);

        // Login page buttons
        loginButton = new JButton("Continue");
        loginButton.setBackground(Color.GREEN);
        loginButton.setBounds(100,100,200,30);
        loginPanel.add(loginButton);
    }

    /**
     * Simple getter to get the heading
     * @return The heading panel
     */
    public JPanel getHeading() {
        return this.heading;
    }

    /**
     * Simple getter to get the login panel
     * @return JPanel for Login
     */
    public JPanel getLoginPanel() {
        return this.loginPanel;
    }
    
    /**
     * Simple getter to get the country from the JTextField
     * @return The country
     */
    public String getCountry() {
        return this.country.getText();
    }

    /**
     * Simple getter to get the login button
     * @return The login button
     */
    public JButton getLoginButton(){
        return this.loginButton;
    }

    /**
     * Display the given message as a popup
     */
    public void showErrorMessage(String message){
        JOptionPane.showMessageDialog(null, message);
    }

    /**
     * To hide the current panel
     */
    public void hidePanel(){
        this.loginPanel.setVisible(false);
        this.heading.setVisible(false);
    }

    public String getThreshold() {
        String x = threshold.getSelectedItem().toString();
        return x;
    }

}