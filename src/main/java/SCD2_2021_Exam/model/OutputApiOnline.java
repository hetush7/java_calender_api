package SCD2_2021_Exam.model;

import java.io.*;
import java.util.*;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

public class OutputApiOnline implements OutputApi{

    private String getKey() {
        JSONParser parser = new JSONParser();
        Object obj;
        try {
            obj = parser.parse(new FileReader("src/main/resources/OutputAuth.json"));
            JSONObject jsonObject = (JSONObject) obj;
            return (String)jsonObject.get("token");
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }
    
    
    public ImageUpload postRequest(String url,String image) {
        try {
            CloseableHttpClient httpclient = HttpClients.createDefault();
            HttpPost httppost = new HttpPost(url);
            
            StringEntity stringEntity = new StringEntity(image);
            
            httppost.setHeader("Authorization", "Client-ID " + getKey() ); 
            httppost.setHeader("Content-Type", "application/json");
            httppost.setHeader("Accept", "application/json");
            httppost.setEntity(stringEntity);

            HttpResponse httpresponse = httpclient.execute(httppost);


            Scanner sc = new Scanner(httpresponse.getEntity().getContent());
            StringBuilder output = new StringBuilder();
            //System.out.println("Status Code : " + httpresponse.getStatusLine());
            while(sc.hasNext()) {
                output = output.append(sc.nextLine());
            }
            sc.close();
            JSONParser parser = new JSONParser();
            JSONObject object = (JSONObject) parser.parse(output.toString());
            if(object.get("success").equals(true)){
                JSONObject objectData = (JSONObject)parser.parse(object.get("data").toString());
                return new ImageUpload(true, (String)objectData.get("link"));
            }
            return null;

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Invalid : " + e);
        }

        return null;
    }

}