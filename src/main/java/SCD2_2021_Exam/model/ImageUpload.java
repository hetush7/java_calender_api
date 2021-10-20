package SCD2_2021_Exam.model;

public class ImageUpload {
    private boolean success;
    private String url;

    public ImageUpload(boolean success, String url){
        this.success = success;
        this.url = url;
    }

    /**
     * Simple accessor for image upload success status<br><br>
     *
     * @return The status of the image - if it was uploaded or not.
     */
    public boolean getSuccess(){
        return this.success;
    }

    /**
     * Simple accessor for images upload url<br><br>
     *
     * @return The url of the image.
     */
    public String getUrl(){
        return this.url;
    }
}