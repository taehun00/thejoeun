package com.pawject.dto.Apiweather;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.pawject.service.exec.SaveweatherService;

@Component
public class ApiScheduledTaskExec {
    @Autowired 
    private SaveweatherService    wservice;

    @Scheduled(cron = "0 0 6 * * *") // 매일 오전 6시
    public void runTask2() {
    	wservice.saveWeatherFromApi();
    }

}
