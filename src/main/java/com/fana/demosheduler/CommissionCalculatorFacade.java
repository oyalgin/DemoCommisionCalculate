package com.fana.demosheduler;

import com.fana.demosheduler.client.FanaClient;
import com.fana.demosheduler.service.FileOperationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CommissionCalculatorFacade {
    private static final Logger logger = LoggerFactory.getLogger(CommissionCalculatorFacade.class);

    @Autowired
    FileOperationService fileOperationService;
    @Autowired
    FanaClient fanaClient;

    @Scheduled(fixedRate = 10000)
    public void process() {
        logger.info("Calculation commission task started");

        //get 100 line from order file
        List<String> lines = fileOperationService.readFileByLine("Orders.txt", 100);

        //get price list from lines
        List<Integer> priceList = getPriceList(lines);

        //calculate commission
        List<String> commissions = fanaClient.calculateCommissions(priceList);

        //create commission file
        fileOperationService.writeToFile("OrderCommission.txt", commissions);

        logger.info("Calculation commission task finished");
    }

    private List<Integer> getPriceList(List<String> lines){
        //line includes order name and order price
        return lines.stream()
                .map(line -> Integer.valueOf(line.split("\\s+")[1]))
                .collect(Collectors.toList());
    }

}


