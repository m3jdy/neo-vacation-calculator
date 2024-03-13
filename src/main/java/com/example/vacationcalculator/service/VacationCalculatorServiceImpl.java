package com.example.vacationcalculator.service;
import com.example.vacationcalculator.util.VacationCalculatorUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

@Service
@Component
public class VacationCalculatorServiceImpl implements VacationCalculatorService{

    @Autowired
    private HolidayService holidayService;

    @Override
    public double calculateVacationPayBasic(double averageSalary, int vacationDays) {
        return VacationCalculatorUtils.calculateVacationPayBasic(averageSalary, vacationDays);
    }

    @Override
    public double calculateVacationPayAdvanced(double averageSalary, int vacationDays, String startDate, String endDate) {
        //TODO: implement advanced calculation with HolidayService
        LocalDate start = LocalDate.parse(startDate, DateTimeFormatter.ISO_DATE);
        LocalDate end = LocalDate.parse(endDate, DateTimeFormatter.ISO_DATE);

        int year = start.getYear();
        List< Map<String,Object>> holidays = holidayService.getHolidaysForYear(year);

        long workingDays = Stream.iterate(start, date -> date.plusDays(1))
                .limit(ChronoUnit.DAYS.between(start, end.plusDays(1)) + 1)
                .filter(day -> isWorkingDay(day, holidays))
                .count();
        return 0;
    }

    private boolean isWorkingDay(LocalDate date, List<Map<String, Object>> holidays){
        if (date.getDayOfWeek().getValue() >= 6){
            return false;
        }

        for (Map<String, Object> holiday : holidays){
            LocalDate holidayDate = LocalDate.parse((String) holiday.get("date"), DateTimeFormatter.ISO_DATE);
            if (holidayDate.equals(date) && (int) holiday.get("isWorking") != 0){
                return false;
            }
        }
        return true;
    }
}
