package SCD2_2021_Exam;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import SCD2_2021_Exam.facade.ApiFacade;
import SCD2_2021_Exam.facade.Cache;
import SCD2_2021_Exam.model.Holiday;
import SCD2_2021_Exam.model.InputApi;
import SCD2_2021_Exam.model.OutputApi;

public class CacheTest {
    
    ApiFacade apiFacade;
    InputApi api;
    Holiday holiday;
    OutputApi outputApi;
    Cache cache;

    @Before
    public void init(){
        api = mock(InputApi.class);
        holiday = mock(Holiday.class);
        outputApi = mock(OutputApi.class);
        cache = mock(Cache.class);
        apiFacade = new ApiFacade(api,outputApi,cache);
        when(holiday.getName()).thenReturn("New Year's Day");
        when(holiday.getDate()).thenReturn("01/01/2021");
    }

    @Test
    public void dateExistsTest(){
        List<String> dates = new ArrayList<>(Arrays.asList("1","26"));
        apiFacade.countrySet("IN");
        when(cache.dateExists("01/01/2021")).thenReturn(false);
        assertFalse(apiFacade.dateExists("01", "01", "2021"));
    }

    @Test
    public void dateExistsTestTwo(){
        List<String> dates = new ArrayList<>(Arrays.asList());
        when(api.getHoliday("01", "2021", "01")).thenReturn(holiday);
        assertTrue(apiFacade.isHoliday("01", "2021", "01"));
        apiFacade.countrySet("IN");
        when(cache.dateExists("01/01/2021")).thenReturn(true);
        assertTrue(apiFacade.dateExists("01", "2021", "01"));
    }

    @Test
    public void getNewTest(){
        String country  = "IN";
        assertTrue(apiFacade.countrySet(country));
        when(api.getHoliday(anyString(),anyString(), anyString())).thenReturn(null);
        assertEquals(apiFacade.getNew("02", "2021", "01"),null);
    }

    @Test
    public void getNewTestTwo(){
        when(api.getHoliday("01", "2021", "01")).thenReturn(holiday);
        assertEquals(apiFacade.getNew("01", "2021", "01"),"New Year's Day");
    }

    @Test
    public void getOldTest(){
        String country  = "IN";
        assertTrue(apiFacade.countrySet(country));
        when(api.getHoliday(anyString(),anyString(), anyString())).thenReturn(null);
        assertFalse(apiFacade.isHoliday("02", "2021", "01"));
        when(cache.dateHoliday(anyString())).thenReturn(null);
        assertEquals(apiFacade.getExisting("02", "2021", "01"),null);
    }

    @Test
    public void getOldtestTwo(){
        when(api.getHoliday("01", "2021", "01")).thenReturn(holiday);
        assertTrue(apiFacade.isHoliday("01", "2021", "01"));
        when(cache.dateHoliday(anyString())).thenReturn("New Year's Day");
        assertEquals(apiFacade.getExisting("01", "2021", "01"),"New Year's Day");
    }

    @Test
    public void getDateValuesTest(){
        String country  = "IN";
        assertTrue(apiFacade.countrySet(country));
        when(cache.dateNumber(anyString())).thenReturn("1");
        assertEquals(apiFacade.getNumberExisting("01", "2021", "01"),1);
    }

    @Test
    public void getDateValuesTestTwo(){
        String country  = "IN";
        assertTrue(apiFacade.countrySet(country));
        when(cache.dateNumber(anyString())).thenReturn("0");
        assertEquals(apiFacade.getNumberExisting("01", "2021", "01"),0);
    }
}