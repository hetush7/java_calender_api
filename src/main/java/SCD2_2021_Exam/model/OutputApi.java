package SCD2_2021_Exam.model;

public interface OutputApi {
     /**
     * This uploads a given image and returns the Url where it can be accessed
     * @param url The location where the post request should be make
     * @param image The image encrypted as a base64 String
     * @return The image date - which has url and success
     */
    public ImageUpload postRequest(String url,String image);
}