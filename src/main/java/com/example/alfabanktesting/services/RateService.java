package com.example.alfabanktesting.services;

import com.example.alfabanktesting.models.Rate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "Rates", url = "${rates.api.url}")
public interface RateService {

    //    list of latest currency exchange rates
    @GetMapping("latest.json?app_id=${rates.api.id}")
    Rate getLatestRates();

    //    list of exchange rates for any date, starting from 1st January 1999.
    @GetMapping("historical/{date}.json?app_id=${rates.api.id}")
    Rate getHistoricalRates(@PathVariable("date") String anyDate);
}
