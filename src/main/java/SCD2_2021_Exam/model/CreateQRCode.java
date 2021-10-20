package SCD2_2021_Exam.model;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.NotFoundException;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

public class CreateQRCode {
    
    /**
     * This takes in data about all the holiday dates in a month and converts into a barcode.<br><br>
     *
     * @param data The data to convert into the barcode
     * @param path The location where the new file will be added
     * @throws WriterException If unable to write.
     * @throws IOException If unable to open the file
     */
    //[4]
    public void createQR(String data, String path) throws WriterException, IOException
    {
 
        BitMatrix matrix = new MultiFormatWriter().encode(
            new String(data.getBytes("UTF-8"), "UTF-8"),
            BarcodeFormat.QR_CODE, 350, 350);
 
        MatrixToImageWriter.writeToFile(matrix,path.substring(path.lastIndexOf('.') + 1),new File(path));
    }
}