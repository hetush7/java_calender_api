package SCD2_2021_Exam.model;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.*;
import org.json.simple.*;
import org.json.simple.parser.*;

public class InputApiOnline implements InputApi {

    private String country;

    public InputApiOnline() {
    }

    public void setCountry(String country) {

        this.country = country;
    }

    private String getKey() {
        JSONParser parser = new JSONParser();
        Object obj;
        try {
            obj = parser.parse(new FileReader("src/main/resources/AuthToken.json"));
            JSONObject jsonObject = (JSONObject) obj;
            return (String)jsonObject.get("key");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private String makeAbstractRequest(String urlAddress) throws Exception {

        URL url = new URL(urlAddress);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        
        // By default it is GET request
        con.setRequestMethod("GET");
        
        //add request header
        con.setRequestProperty("User-Agent", "Mozilla/5.0");
        
        int responseCode = con.getResponseCode();

        
        // Reading response from input Stream
        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String output;
        StringBuffer response = new StringBuffer();
        
        while ((output = in.readLine()) != null) {
        response.append(output);
        }
        in.close();
        
        //printing result from response
        return response.toString();
    }

    public Holiday getHoliday(String day,String year, String month){
        String url = "https://holidays.abstractapi.com/v1/?api_key=" + this.getKey() + "&country=" + country + "&year=" + year + "&month=" + month + "&day=" + day;
        String result = null;
        try{
            result = makeAbstractRequest(url);
            System.out.println(result);
        }
        catch(Exception e){
            System.out.println("INvalid");
        }
        if(result.equalsIgnoreCase("[]")){
            return null;
        }
        try{
            JSONArray jsonArr = (JSONArray) new JSONParser().parse( result );
            JSONObject object = (JSONObject) jsonArr.get(0);
            Holiday holiday = new Holiday((String)object.get("name"),(String)object.get("name_local"),(String)object.get("language"),(String)object.get("description"),(String)object.get("country"),(String)object.get("location"),(String)object.get("type"),(String)object.get("date"),(String)object.get("date_year"),(String)object.get("date_month"),(String)object.get("date_day"),(String)object.get("week_day"));
            holiday.setHolidayNumber(jsonArr.size());
            return holiday;
        }
        catch(Exception e){

        }
        return null;
    }

}