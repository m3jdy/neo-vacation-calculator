package com.example.vacationcalculator.service;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
public class HolidayService {


    private final RestTemplate restTemplate;

    public HolidayService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<Map<String, Object>> getHolidaysForYear(int year) {
        String url = "http://basicdata.ru/api/json/calend/" + year;
        return restTemplate.getForObject(url, List.class);
    }
}
