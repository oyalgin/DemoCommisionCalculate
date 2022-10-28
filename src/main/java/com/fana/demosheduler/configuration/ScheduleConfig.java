package com.fana.demosheduler.configuration;

import com.fana.demosheduler.CommissionCalculatorFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

import java.util.Date;

@Configuration
public class ScheduleConfig {
    @Autowired
    ThreadPoolTaskScheduler scheduler;
    @Autowired
    CommissionCalculatorFacade commissionCalculatorFacade;
    public void scheduleTask() {
        //create task which prepares commissions
        Runnable task = () -> {
            commissionCalculatorFacade.process();

        };

        scheduler.scheduleAtFixedRate(task, new Date(), 10000);
    }

}
