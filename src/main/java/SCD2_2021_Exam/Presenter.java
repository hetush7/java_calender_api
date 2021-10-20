package SCD2_2021_Exam;

import java.awt.*;
import java.awt.event.*;

import javax.swing.JOptionPane;

import SCD2_2021_Exam.facade.ApiFacade;
import SCD2_2021_Exam.model.ImageUpload;
import SCD2_2021_Exam.view.CalenderGui;
import SCD2_2021_Exam.view.Login;
import SCD2_2021_Exam.view.MainFrame;

public class Presenter extends MouseAdapter implements ActionListener{
    MainFrame frame;
    ApiFacade facade;
    Login loginPanel;
    CalenderGui calenderPanel;

    public Presenter(MainFrame frame, ApiFacade facade) {
        this.frame = frame;
        this.facade = facade;
        this.loginPanel = frame.getLoginPanel();
        this.calenderPanel = frame.getCalenderPanel();
        this.loginPanel.getLoginButton().addActionListener(this);
        this.calenderPanel.getNextButton().addActionListener(this);
        this.calenderPanel.getPrevButton().addActionListener(this);
        this.calenderPanel.getReportButton().addActionListener(this);
        this.calenderPanel.getCalender().addMouseListener(this);
    }

    /**
     * Depending on what the user clicks make certain calls
     * @param e any event which occurs
     */
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == loginPanel.getLoginButton()){
            if(facade.countrySet(loginPanel.getCountry())){
                loginPanel.hidePanel();
                calenderPanel.getCalenderPanel().setVisible(true);
            }
            else{
                loginPanel.showErrorMessage("Invalid Country Code : Must be 2 letters!!");
            }   
        }

        if(e.getSource() == calenderPanel.getNextButton()) {
            calenderPanel.updateHolidaysHashMap();
            calenderPanel.nextMonthSetup();
        }

        if(e.getSource() == calenderPanel.getPrevButton()){
            calenderPanel.updateHolidaysHashMap();
            calenderPanel.prevMonthSetup();
        }

        if(e.getSource() == calenderPanel.getReportButton()){
            calenderPanel.updateHolidaysHashMap();
            ImageUpload img = facade.uploadImage(calenderPanel.getHolidaysEachMonth().get(calenderPanel.getCurrentMonth()), Integer.toString(calenderPanel.getCurrentMonth()), Integer.toString(calenderPanel.getCurrentYear()),calenderPanel.getMultipleHolidayDays());
            if( img != null){
                calenderPanel.printPopUp("Report has been uploaded!!");
                calenderPanel.setUrlLink(img.getUrl());
            }
            else{
                if(calenderPanel.getHolidaysEachMonth().get(calenderPanel.getCurrentMonth()).size() == 0){
                    calenderPanel.printPopUp("Error : Could not upload! No Holidays Selected!");
                }
                else{
                    calenderPanel.printPopUp("Error : Could not upload! Internal Api Error!");
                }
            }
        }
    }

    /**
     * Depending on the user mouse acitivty on the table do certain events
     * @param e any mouseevent which occurs
     */
    public void mouseClicked(MouseEvent e){
        int row=calenderPanel.getCalender().rowAtPoint(e.getPoint());
        int col= calenderPanel.getCalender().columnAtPoint(e.getPoint());
        String info = calenderPanel.getCalender().getValueAt(row,col).toString();
        String value;
        if (info.contains("(H)")){
            value = Character.toString(info.charAt(0)) + Character.toString(info.charAt(1));
        }
        else{
            value = info;
        }
        if(facade.dateExists(value, Integer.toString(calenderPanel.getCurrentYear()), String.format("%02d", calenderPanel.getCurrentMonth()))){
            calenderPanel.createPopUpOption();
            if(calenderPanel.getSavedData() == JOptionPane.YES_OPTION){
               String name = facade.getNew(value, Integer.toString(calenderPanel.getCurrentYear()), String.format("%02d", calenderPanel.getCurrentMonth()));
               if(name!= null){
                if(!calenderPanel.getHolidays().contains(value)){
                    calenderPanel.getHolidays().add(value);
                }

                if(facade.getNumber() > Integer.parseInt(loginPanel.getThreshold())){
                    for(int i = 0; i < 5 ; i++){
                        calenderPanel.flickerBackground();
                    }
                    calenderPanel.updateMultipleHolidaysHashMap();
                }

                calenderPanel.getCalender().setValueAt(value + "(H)", row, col);
                calenderPanel.printPopUp("It is a Holiday : " + name);
               }
               else{
                calenderPanel.printPopUp("Not a Holiday :(");
               }
            }
            else if (calenderPanel.getSavedData() == JOptionPane.NO_OPTION){
                String name = facade.getExisting(value, Integer.toString(calenderPanel.getCurrentYear()), String.format("%02d", calenderPanel.getCurrentMonth()));
                if(!name.equals("NoHoliday")){
                    if(!calenderPanel.getHolidays().contains(value)){
                        calenderPanel.getHolidays().add(value);
                    }

                    if(facade.getNumberExisting(value, Integer.toString(calenderPanel.getCurrentYear()), String.format("%02d", calenderPanel.getCurrentMonth())) > Integer.parseInt(loginPanel.getThreshold())){
                        for(int i = 0; i < 5 ; i++){
                            calenderPanel.flickerBackground();
                        }
                        calenderPanel.updateMultipleHolidaysHashMap();
                    }
                    calenderPanel.getCalender().setValueAt(value + "(H)", row, col);
                    calenderPanel.printPopUp("It is a Holiday : " + name);
                   }
                   else{
                    calenderPanel.printPopUp("Not a Holiday :(");
                   }
            }
        }
        else {
            if(facade.isHoliday(value, Integer.toString(calenderPanel.getCurrentYear()), String.format("%02d", calenderPanel.getCurrentMonth()))){
                if(!calenderPanel.getHolidays().contains(value)){
                    calenderPanel.getHolidays().add(value);
                }
                if(facade.getNumber() > Integer.parseInt(loginPanel.getThreshold())){
                    for(int i = 0; i < 5 ; i++){
                        calenderPanel.flickerBackground();
                    }
                    calenderPanel.updateMultipleHolidaysHashMap();
                }
                calenderPanel.getCalender().setValueAt(value + "(H)", row, col);
                calenderPanel.printPopUp("It is a Holiday : " + facade.getHolidayName());
            }
            else{
                calenderPanel.printPopUp("Not a Holiday :(");
            }
        }
    }
}