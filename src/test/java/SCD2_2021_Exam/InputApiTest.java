package SCD2_2021_Exam;

import SCD2_2021_Exam.*;
import SCD2_2021_Exam.facade.ApiFacade;
import SCD2_2021_Exam.facade.Cache;
import SCD2_2021_Exam.model.Holiday;
import SCD2_2021_Exam.model.InputApi;
import SCD2_2021_Exam.model.OutputApi;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.*;
import static org.mockito.Mockito.*;
import java.lang.SecurityException;

import static org.junit.Assert.*;
import org.junit.Before;
import java.io.*;
import java.util.*;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class InputApiTest {
    ApiFacade apiFacade;
    InputApi api;
    Holiday holiday;
    OutputApi outputApi;
    Cache cache;

    @Before
    public void init() {
        api = mock(InputApi.class);
        holiday = mock(Holiday.class);
        outputApi = mock(OutputApi.class);
        cache = mock(Cache.class);
        apiFacade = new ApiFacade(api,outputApi,cache);
    }

    @Test
    public void countrySetTest(){
        String country  = "INDIA";
        assertFalse(apiFacade.countrySet(country));
    }

    @Test
    public void countrySetTestTwo(){
        String country  = "IN";
        assertTrue(apiFacade.countrySet(country));
    }

    @Test
    public void isHolidayTest(){
        String country  = "IN";
        assertTrue(apiFacade.countrySet(country));
        when(api.getHoliday(anyString(),anyString(), anyString())).thenReturn(null);
        assertFalse(apiFacade.isHoliday("02", "2021", "01"));
    }

    @Test
    public void isHolidayTestTwo(){
        when(api.getHoliday("01", "2021", "01")).thenReturn(holiday);
        assertTrue(apiFacade.isHoliday("01", "2021", "01"));
    }

    @Test
    public void getHolidayNameTest(){
        when(api.getHoliday("01", "2021", "01")).thenReturn(holiday);
        when(holiday.getName()).thenReturn("New Year's Eve");
        assertTrue(apiFacade.isHoliday("01", "2021", "01"));
        assertEquals(apiFacade.getHolidayName(),"New Year's Eve");
    }
    
    @Test
    public void getHolidaysNumber(){
        when(api.getHoliday("01", "2021", "01")).thenReturn(holiday);
        when(holiday.getName()).thenReturn("New Year's Eve");
        when(holiday.getNumber()).thenReturn(1);
        assertTrue(apiFacade.isHoliday("01", "2021", "01"));
        assertEquals(apiFacade.getHolidayName(),"New Year's Eve");
        assertEquals(apiFacade.getNumber(),1);

    }

    @Test
    public void getHolidaysNumberTwo(){
        when(api.getHoliday("01", "2021", "01")).thenReturn(holiday);
        when(holiday.getName()).thenReturn("New Year's Eve");
        when(holiday.getNumber()).thenReturn(3);
        assertTrue(apiFacade.isHoliday("01", "2021", "01"));
        assertEquals(apiFacade.getHolidayName(),"New Year's Eve");
        assertEquals(apiFacade.getNumber(),3);

    }
}