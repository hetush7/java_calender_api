package SCD2_2021_Exam.model;


public class Holiday {
    private String name;
    private String name_local;
    private String language;
    private String description;
    private String country;
    private String location;
    private String type;
    private String date;
    private String date_year;
    private String date_month;
    private String date_day;
    private String week_day;
    private int numberHolidays;

    public Holiday(String name, String name_local, String language,String description,String country, String location,String type, String date, String date_year, String date_month, String date_day,String week_day){
        this.name = name;
        this.name_local = name_local;
        this.language = language;
        this.description = description;
        this.country = country;
        this.location = location;
        this.type = type;
        this.date = date;
        this.date_year = date_year;
        this.date_month = date_month;
        this.date_day = date_day;
        this.week_day = week_day;
        this.numberHolidays = 0;
        
    }

    /**
     * Simple accessor for the holiday date<br><br>
     *
     * @return The date of the holiday.
     */
    public String getDate(){
        return this.date;
    }

    /**
     * Simple accessor for the holidays name<br><br>
     *
     * @return The name of the holdai.
     */
    public String getName(){
        return this.name;
    }

    /**
     * Simple accessor for holidays local name<br><br>
     *
     * @return The local name of the holiday.
     */
    public String getNameLocal(){
        return this.name_local;
    }

    /**
     * Simple accessor for holidays language<br><br>
     *
     * @return The language the holiday is in.
     */
    public String getLanguage(){
        return this.language;
    }

    /**
     * Simple accessor for holidays description<br><br>
     *
     * @return The description of the holiday.
     */
    public String getDescription(){
        return this.description;
    }

    /**
     * Simple accessor for holidays country<br><br>
     *
     * @return The country of the holiday.
     */
    public String getCountry(){
        return this.country;
    }

    /**
     * Simple accessor for holidays location<br><br>
     *
     * @return The location of the holiday.
     */
    public String getLocation(){
        return this.location;
    }

    /**
     * Simple accessor for holidays type<br><br>
     *
     * @return The type of holiday.
     */
    public String getType(){
        return this.type;
    }

    /**
     * Simple accessor for holidays year<br><br>
     *
     * @return The year of the holiday.
     */
    public String getDateYear(){
        return this.date_year;
    }

    /**
     * Simple accessor for holidays month<br><br>
     *
     * @return The month of the holiday.
     */
    public String getDateMonth(){
        return this.date_month;
    }

    /**
     * Simple accessor for the day of the holiday<br><br>
     *
     * @return The day of the holiday.
     */
    public String getDateDay(){
        return this.date_day;
    }

    /**
     * Simple accessor for the day of the week of the holiday<br><br>
     *
     * @return The day of the week of the holiday.
     */
    public String getWeekDay(){
        return week_day;
    }

    /**
     * Simple setter for the number of holidays that day<br><br>
     */
    public void setHolidayNumber(int number){
        this.numberHolidays = number;
    }

    /**
     * Simple accessor for the number of holidays of that day<br><br>
     *
     * @return The number of holidays that day.
     */
    public int getNumber(){
        return this.numberHolidays;
    }
}