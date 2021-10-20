package SCD2_2021_Exam.model;

public class OutputApiDummy implements OutputApi {
    
    public ImageUpload postRequest(String url,String image){
        ImageUpload img = new ImageUpload(true, "https://i.imgur.com/0nVBvTx.png");
        return img;
    }
}