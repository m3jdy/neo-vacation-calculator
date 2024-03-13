package com.example.vacationcalculator.util;

public class VacationCalculatorUtils {
    public static double calculateVacationPayBasic(double averageSalary, double vacationDays){
        return (averageSalary / 29.3) * vacationDays;
    }
}
