package SCD2_2021_Exam.model;

public interface InputApi {

    /**
     * This method for a given date gets the data if it is a holiday or null if it is not a holiday
     * @param day The current day
     * @param year The current year
     * @param month The current month
     * @return The holiday which data which was found or null if not a holiday
     */
    public Holiday getHoliday(String day,String year, String month);

    /**
     * @param country The country which the user is from
     */
    public void setCountry(String country);
}