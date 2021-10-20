package SCD2_2021_Exam.view;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;


/**
 * View of the Calender Page
 */
public class CalenderGui extends MouseAdapter {

    private JTextField imageUrl;

    private JPanel calender;
    private DefaultTableModel modelTable;
    private JScrollPane jScrollPaneTable;
    private JTable table;
    private JButton nextMonth;
    private JLabel month;
    private JButton prevMonth;
    private JButton report;
    private int savedData;
    private int currentMonth = 6;
    private int currentYear = 2021;
    private int currentDay = 1;
    private int offset = 2;

    private JLabel background;
    private Timer timer;
    private int duration = 1000;
    private ImageIcon img1;

    String[] header = {"","","","","","",""};
    String[][] data = {{"Sun","Mon","Tue","Wed","Thu","Fri","Sat"},{},{},{},{},{},{}};
    ArrayList<String> months = new ArrayList<>(Arrays.asList(" ","January","February","March","April","May","June","July","August","September","October","November","December"));
    HashMap<Integer,ArrayList<String>> holidayEachMonth;
    ArrayList<String> holidays;

    HashMap<Integer, Boolean> multipleHolidayDays;
    
    /**
    * Constructor for the calender page
    * @param frame the main handler of the view
    */
    public CalenderGui(MainFrame frame){
        //[2]
        calender = new JPanel();
        Font f = new Font(Font.MONOSPACED, Font.BOLD, 60);
        holidays = new ArrayList<>();
        holidayEachMonth = new HashMap<>();
        multipleHolidayDays = new HashMap<>();

        // Panel Settings
        calender.setLayout(null);
        calender.setBackground(new Color(0,0,0,10));
        calender.setBounds(0,0,1500,900);
        calender.setVisible(false);

        month = new JLabel("June");
        month.setBounds(625,50,400,50);
        month.setFont(f);
        month.setForeground(Color.WHITE);
        calender.add(month);

        imageUrl = new JTextField("");
        imageUrl.setBackground(Color.WHITE);
        imageUrl.setBounds(20,450,200,50);
        calender.add (imageUrl);

        nextMonth = new JButton("next->");
        nextMonth.setOpaque(true);
        nextMonth.setBackground(Color.BLACK);
        nextMonth.setBounds(1275,50,80,50);
        calender.add(nextMonth);

        prevMonth = new JButton("<-prev");
        prevMonth.setOpaque(true);
        prevMonth.setBackground(Color.BLACK);
        prevMonth.setBounds(50,50,80,50);
        calender.add(prevMonth);

        report = new JButton("Generate Report");
        report.setOpaque(true);
        report.setBackground(Color.BLACK);
        report.setBounds(50,400,150,50);
        calender.add(report);

        modelTable = new DefaultTableModel(data, header);
        table = new JTable(modelTable);
        jScrollPaneTable = new JScrollPane();
        table.setGridColor(Color.black);
        table.setIntercellSpacing(new Dimension(10,10));
        table.setEnabled(false);
        table.setRowHeight(76);
        jScrollPaneTable.setViewportView(table);
        jScrollPaneTable.setBounds(225,200,1000,600);
        calender.add(jScrollPaneTable);
        setMonthLayout();

        img1 = new ImageIcon("src/main/resources/background2.jpg");
        background = new JLabel("", img1, JLabel.CENTER);
        background.setBounds(0,0,1500,900);
        calender.add(background);

        table.addMouseListener(this);
    }

    /**
     * Simple getter to get the calender panel
     * @return The calender panel.
     */
    public JPanel getCalenderPanel(){
        return this.calender;
    }

    /**
     * Simple getter to get the go to previous page button
     * @return The go to previous month button.
     */
    public JButton getPrevButton(){
        return this.prevMonth;
    }

    /**
     * Simple getter to get the go to next page button
     * @return The go to next month button.
     */
    public JButton getNextButton(){
        return this.nextMonth;
    }

    /**
     * Simple getter to get the generate report button
     * @return The generate report button.
     */
    public JButton getReportButton(){
        return this.report;
    }

    /**
     * Simple getter to get the main calender
     * @return The main calender table.
     */
    public JTable getCalender(){
        return this.table;
    }
    /**
     * Clears all of the calender dates so that next month can be set up
     */
    public void clearCalender(){
        for(int row = 1 ; row < 7; row++){
            for(int col = 0; col < 7; col++){
                modelTable.setValueAt("", row, col);
            }
        }
    }

    /**
     * Calculates the offset of the previous month - which day it starts from
     */
    public void setPrevOffset(){
        int days;
        if(currentMonth+1 == 2){
            days = 28;
        }
        else if(currentMonth+1 == 4 || currentMonth+1 == 6 ||  currentMonth+1 == 9 ||  currentMonth+1 == 11){
            days = 30;
        }
        else{
            days = 31;
        }
        
        offset = (offset - (days%7));
        if(offset < 0){
            offset+=7;
        }

        if(currentMonth == 2){
            days = 28;
        }
        else if(currentMonth == 4 || currentMonth == 6 ||  currentMonth == 9 ||  currentMonth == 11){
            days = 30;
        }
        else{
            days = 31;
        }

        offset = (offset - (days%7));
        if(offset < 0){
            offset+=7;
        }
        
    }


    /**
     * Fills the dates into the table
     */
    public void setMonthLayout(){
        if(holidayEachMonth.containsKey(currentMonth)){
            this.holidays = holidayEachMonth.get(currentMonth);
        }
       
        int maxdays;
        if(currentMonth == 2){
            maxdays = 28;
        }
        else if(currentMonth == 4 || currentMonth == 6 ||  currentMonth == 9 ||  currentMonth == 11){
            maxdays = 30;
        }
        else{
            maxdays = 31;
        }
        int day = 1;
        for(int row = 1 ; row < 7; row++){
            int col = 0;
            if( row == 1){
                col = offset;
            }
            while(col < 7){
                String cellvalue = String.format("%02d", day);;
                if(holidays.contains(cellvalue)){
                    cellvalue = cellvalue + "(H)";
                }
    
                modelTable.setValueAt(cellvalue, row, col);
                day+=1;
                if(day > maxdays){
                    this.offset = col+1;
                    if(offset == 7){
                        offset = 0;
                    }
                    return;
                }
                col+=1;
            }
        }
    }

    /**
     * Shows a pop up message to the user
     */
    public void printPopUp(String message){
        JOptionPane.showMessageDialog(null, message);
    }

    /**
     * Adds the holidays of this month to the hashmap
     */
    public void updateHolidaysHashMap(){
        if(holidayEachMonth.containsKey(currentMonth)){
            ArrayList<String> copy = new ArrayList<>(holidays);
            holidayEachMonth.replace(currentMonth, copy);
        }
        else{
            ArrayList<String> copy = new ArrayList<>(holidays);
            holidayEachMonth.put(currentMonth, copy);
        }
    }

    /**
     * Simple getter to get the current month
     * @return The current month
     */
    public int getCurrentMonth(){
        return this.currentMonth;
    }

    /**
     * Simple getter to get the holidays in the current month
     * @return The holidays in the current month
     */
    public ArrayList<String> getHolidays(){
        return this.holidays;
    }

    /**
     * Simple getter to get all the holidays for each month
     * @return The holidays in each month
     */
    public HashMap<Integer,ArrayList<String>> getHolidaysEachMonth(){
        return this.holidayEachMonth;
    }

    /**
     * Simple getter to get the current year
     * @return The current year
     */
    public int getCurrentYear(){
        return this.currentYear;
    }

    /**
     * Adds the url recieved to the JTextField
     */
    public void setUrlLink(String link){
        imageUrl.setText(link);
    }

    /**
     * Adjusts the month and year when the next month is requested
     */
    public void prevMonthSetup(){
        holidays.clear();
            currentMonth-=1;
            if(currentMonth < 1){
                currentMonth = 12;
                currentYear-=1;
            }
            month.setText(months.get(currentMonth));
            month.setBackground(null);
            this.setPrevOffset();
            clearCalender();
            setMonthLayout();
    }

    /**
     * Adjusts the month and year when the previous month is requested
     */
    public void nextMonthSetup(){
        holidays.clear();
            currentMonth+=1;
            if(currentMonth > 12){
                currentMonth = 1;
                currentYear+=1;
            }
            month.setText(months.get(currentMonth));
            month.setBackground(null);
            clearCalender();
            setMonthLayout();
    }

    /**
     * Shows a pop up message with 2 options for the user
     */
    public void createPopUpOption(){
        String[] options = {"New!", "Old!"}; 
               this.savedData = JOptionPane.showOptionDialog(calender,"Data for this already exists!", "Which one would you like?",            
               JOptionPane.YES_NO_OPTION,
               JOptionPane.QUESTION_MESSAGE,
               null,     //no custom icon
               options,  //button titles
               options[0] //default button
            );
    }

    /**
     * Simple getter to get the result of the pop up message
     * @return The result of the pop up message
     */
    public int getSavedData(){
        return this.savedData;
    }

    /**
     * Flickers the background of the panel
     */
    public void flickerBackground() {
        timer = new Timer(duration, e -> {
            background.setVisible(true);
        });
        background.setVisible(false);
        timer.start();  
    }

    public Boolean getMultipleHolidayDays(){
        return this.multipleHolidayDays.get(currentMonth);
    }

    /**
     * Adds if some day of this month has multiple holidays to the hashmap
     */
    public void updateMultipleHolidaysHashMap() {
        if (multipleHolidayDays.containsKey(currentMonth)) {
            multipleHolidayDays.replace(currentMonth, true);
        } else {
            multipleHolidayDays.put(currentMonth, true);
        }
    }
}