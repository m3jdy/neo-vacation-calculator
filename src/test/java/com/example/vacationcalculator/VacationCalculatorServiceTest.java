package com.example.vacationcalculator;

import com.example.vacationcalculator.service.HolidayService;
import com.example.vacationcalculator.service.VacationCalculatorServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class VacationCalculatorServiceTest {
    @Mock
    private HolidayService holidayService;

    @InjectMocks
    private VacationCalculatorServiceImpl vacationCalculatorService;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCalculateVacationPayBasic() {
        double averageSalary = 50000;
        int vacationDays = 14;
        double expected = 23890.78; // (50000/29.3) * 14

        double result = vacationCalculatorService.calculateVacationPayBasic(averageSalary,vacationDays);
        assertEquals(expected,result,0.01);
    }

    @Test
    void testCalculateVacationPayAdvanced() {
        double averageSalary = 50000;
        int vacationDays = 14;
        String startDate = "2023-06-01";
        String endDate = "2023-06-14";

        Map<String, Object> holiday1 = new HashMap<>();
        holiday1.put("date", "2023-06-12");
        holiday1.put("isWorking", 2);

        Map<String, Object> holiday2 = new HashMap<>();
        holiday2.put("date", "2023-06-13");
        holiday2.put("isWorking", 2);

        List<Map<String, Object>> holidays = Arrays.asList(holiday1, holiday2);

        when(holidayService.getHolidaysForYear(2023)).thenReturn(holidays);

        double expected = 21998.63; // (50000 / 29.3) * 12 (excluding 2 holidays)

        double result = vacationCalculatorService.calculateVacationPayAdvanced(averageSalary, vacationDays, startDate, endDate);
        assertEquals(expected, result, 0.01);
    }
}
