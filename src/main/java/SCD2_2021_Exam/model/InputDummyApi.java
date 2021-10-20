package SCD2_2021_Exam.model;
import java.util.*;


public class InputDummyApi implements InputApi{

    private String country;
    public Holiday getHoliday(String day,String year, String month){
        String date = day + "/"+ month + "/" + year;
        //Coin toss if holiday or not
        int x = new Random().nextInt(2);
            if(x==0){
                Holiday holiday = new Holiday("Assumption of Mary","","","",country,"United States","Christian",date,year,month,day,"Sunday");
                return holiday;
            }  
            else{ 
                return null;
            }    
    }

    public void setCountry(String country){
        this.country = country;
    }
}