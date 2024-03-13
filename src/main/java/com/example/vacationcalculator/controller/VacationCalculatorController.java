package com.example.vacationcalculator.controller;

import com.example.vacationcalculator.service.VacationCalculatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VacationCalculatorController {

    @Autowired
    private VacationCalculatorService vacationCalculatorService;

    @GetMapping("/calculate")
    public double calculateVacationPay(
            @RequestParam double averageSalary,
            @RequestParam int vacationDays,
            @RequestParam(required = false) String startDate,
            @RequestParam(required = false) String endDate) {

        if (startDate == null || endDate == null) {
            return vacationCalculatorService.calculateVacationPayBasic(averageSalary, vacationDays);
        } else {
            return vacationCalculatorService.calculateVacationPayAdvanced(averageSalary, vacationDays, startDate, endDate);
        }
    }
}
