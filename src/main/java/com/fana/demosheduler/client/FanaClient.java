package com.fana.demosheduler.client;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

@Component
public class FanaClient {

    @Value("${api}")
    private String api;

    @Autowired
    private  RestTemplate restTemplate;

    public List<String> calculateCommissions(List<Integer> priceList){

        List<Future<String>> futureList = new ArrayList<>();
        ExecutorService executor = Executors.newFixedThreadPool(10);
        for(Integer price: priceList){
            Callable task = () -> { return this.getCommissionFee(price); };
            //each callable added executor, executor returns Future variable including commission service return
            Future<String> future = executor.submit(task);
            futureList.add(future);
        }

        List<String> commisionList = commissionList(futureList);
        executor.shutdown();

        return commisionList;

    }

    private  List<String> commissionList(List<Future<String>> futureList){

        List<String> commissions = new ArrayList<>();
        try {

            for(Future<String> f: futureList){
                commissions.add(String.valueOf(f.get()));
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        return commissions;

    }

    private String getCommissionFee(Integer price){

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.add("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
        HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);

        ResponseEntity<String> response =restTemplate.exchange(api, HttpMethod.GET, entity, String.class,price );

        JSONObject result = new JSONObject(response.getBody());

        return result.get("comission").toString();
    }

}
