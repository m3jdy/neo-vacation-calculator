package com.example.vacationcalculator.service;

public interface VacationCalculatorService {
    double calculateVacationPayBasic(double averageSalary, int vacationDays);
    double calculateVacationPayAdvanced (double averageSalary, int vacationDays, String startDate, String endDate);
}
