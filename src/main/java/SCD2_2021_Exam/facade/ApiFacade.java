package SCD2_2021_Exam.facade;

import SCD2_2021_Exam.model.CreateQRCode;
import SCD2_2021_Exam.model.Holiday;
import SCD2_2021_Exam.model.ImageUpload;
import SCD2_2021_Exam.model.InputApi;
import SCD2_2021_Exam.model.OutputApi;

import java.util.*;
import java.io.*;
import org.apache.commons.io.FileUtils;

import java.util.List;

public class ApiFacade {
    
    InputApi inputApi;
    Holiday holiday;
    CreateQRCode createQRCode;
    OutputApi outputApi;
    Cache cache;
    
    public ApiFacade(InputApi inputApi,OutputApi outputApi,Cache cache){
        this.inputApi = inputApi;
        createQRCode = new CreateQRCode();
        this.outputApi = outputApi;
        this.cache = cache;
    }

    /**
    * Returns if the given date is a holiday or not
    * @param day The day of the holiday
    * @param year The year of the holiday
    * @param month The month of the holiday
    * @return if the date if a holiday or not
    */
    public boolean isHoliday(String day,String year, String month){
        String date = month+"/"+day+"/"+year;
        Holiday holiday = inputApi.getHoliday(day, year, month);
        if(holiday == null){
            cache.insert("NoHoliday", "", "", "", "", "", "", date, "", "", "", "","0");
            return false;
        }
        this.holiday = holiday;
        cache.insert(holiday.getName(), holiday.getNameLocal(), holiday.getLanguage(), holiday.getDescription(), holiday.getCountry(), holiday.getLocation(), holiday.getType(), holiday.getDate(), holiday.getDateYear(), holiday.getDateMonth(), holiday.getDateDay(), holiday.getWeekDay(),Integer.toString(holiday.getNumber()));
        return true;
    }
    
    /**
    * Sets the country for the input api and returns if country name is valid or not
    * @param country the country to set the input API
    * @return if the country is valid or not
    */
    public boolean countrySet(String country){
        if(country.length() != 2){
            return false;
        }
        inputApi.setCountry(country);
        return true;
    }

    /**
    * Gives the name of the holiday
    * @return the name of the holiday
    */

    public String getHolidayName(){
        return this.holiday.getName();
    }
    
    /**
    * Returns if the given date is already in cache or not
    * @param day The day of the holiday
    * @param year The year of the holiday
    * @param month The month of the holiday
    * @return if the date has already been accessed or not
    */
    public boolean dateExists(String day,String year, String month){
        String date = month+"/"+day+"/"+year;
        return cache.dateExists(date);
    }

    /**
    * Gets a new instance of the holidays name
    * @param day The day of the holiday
    * @param year The year of the holiday
    * @param month The month of the holiday
    * @return the name of the holiday
    */
    public String getNew(String day,String year, String month){
        String date = month+"/"+day+"/"+year;
        cache.delete(date);
        Holiday holiday = inputApi.getHoliday(day, year, month);
        if(holiday == null){
            return null;
        }
        this.holiday = holiday;
        cache.insert(holiday.getName(), holiday.getNameLocal(), holiday.getLanguage(), holiday.getDescription(), holiday.getCountry(), holiday.getLocation(), holiday.getType(), holiday.getDate(), holiday.getDateYear(), holiday.getDateMonth(), holiday.getDateDay(), holiday.getWeekDay(),Integer.toString(holiday.getNumber()));
        return holiday.getName();
    }

    /**
    * Gets the old instance of the holiday from the cache
    * @param day The day of the holiday
    * @param year The year of the holiday
    * @param month The month of the holiday
    * @return the name of the holiday
    */
    public String getExisting(String day,String year, String month){
        String date = month+"/"+day+"/"+year;
        return cache.dateHoliday(date);
    }

    private String QRcodeaMaker(String info){
        try{
            createQRCode.createQR(info,"src/main/resources/code.png");
        }
        catch(Exception e){
            System.out.println("Could not generate QR code :(");
            return null;
        }
        try{
            //[3]
            byte[] fileContent = FileUtils.readFileToByteArray(new File("src/main/resources/code.png"));
            String encodedString = Base64.getEncoder().encodeToString(fileContent);
            String image = "{\"image\":\"" + encodedString + "\"}";
            return image;
        }
        catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }
    
     /**
    * Uploads all the holidays as a qr code to the api
    * @param dates All the dates in the month where there is a holiday
    * @param year The year of the holiday
    * @param month The month of the holiday
    * @return the details about the image which was uploaded
    */
    public ImageUpload uploadImage(List<String> dates, String month, String year,Boolean asterix) {
        String allHolidays;
        if(asterix == null){
            allHolidays = "";
        }
        else if(!asterix){
            allHolidays = "";
        }
        else{
            allHolidays = "*";
        }
        for(int i = 0 ; i < dates.size() ; i++){
            allHolidays = allHolidays + " - " + dates.get(i) + "/" + month + "/" + year;
        }
        try{
            ImageUpload result = outputApi.postRequest("https://api.imgur.com/3/image",QRcodeaMaker(allHolidays));
            return result;
        }
        catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

     /**
    * Returns the number of holidays at that date
    * @return the number of holidays that day
    */
    public int getNumber(){
        return this.holiday.getNumber();
    }

     /**
    * Returns the number of holidays at that date from saved data
    * @param dates All the dates in the month where there is a holiday
    * @param year The year of the holiday
    * @param month The month of the holiday
    * @return the number of holidays that day from saved data
    */
    public int getNumberExisting(String day,String year, String month){
        String date = month+"/"+day+"/"+year;
        return Integer.parseInt(cache.dateNumber(date));
    }
}