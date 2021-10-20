package SCD2_2021_Exam;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import SCD2_2021_Exam.model.Holiday;
import SCD2_2021_Exam.model.ImageUpload;
import SCD2_2021_Exam.model.InputApi;
import SCD2_2021_Exam.model.OutputApi;
import SCD2_2021_Exam.facade.ApiFacade;
import SCD2_2021_Exam.facade.Cache;

public class OutputAPITest {
    ApiFacade apiFacade;
    InputApi api;
    Holiday holiday;
    OutputApi outputApi;
    Cache cache;
    ImageUpload imageUpload;

    @Before
    public void init(){
        api = mock(InputApi.class);
        holiday = mock(Holiday.class);
        outputApi = mock(OutputApi.class);
        cache = mock(Cache.class);
        imageUpload = mock(ImageUpload.class);
        apiFacade = new ApiFacade(api,outputApi,cache);
    }

    @Test
    public void uploadImageTest(){
        List<String> dates = new ArrayList<>(Arrays.asList("1","26"));
        when(outputApi.postRequest(anyString(),anyString())).thenReturn(imageUpload);
        when(imageUpload.getUrl()).thenReturn("https://i.imgur.com/0nVBvTx.png");
        when(imageUpload.getSuccess()).thenReturn(true);
        apiFacade.countrySet("IN");
        ImageUpload image = apiFacade.uploadImage(dates, "01", "2021",false);
        assertEquals(image.getUrl(),"https://i.imgur.com/0nVBvTx.png");
        assertEquals(image.getSuccess(),true);
    }

    @Test
    public void uploadImageTestTwo(){
        List<String> dates = new ArrayList<>(Arrays.asList());
        when(outputApi.postRequest(anyString(),anyString())).thenReturn(imageUpload);
        apiFacade.countrySet("IN");
        assertEquals(apiFacade.uploadImage(dates, "01", "2021",false),null);
    }
}