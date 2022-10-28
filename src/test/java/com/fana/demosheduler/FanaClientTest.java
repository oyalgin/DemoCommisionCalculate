package com.fana.demosheduler;


import com.fana.demosheduler.client.FanaClient;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.*;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class FanaClientTest {
    @Mock
    RestTemplate restTemplate;
    @Spy
    private HttpEntity<String> httpEntity = getHttpEntity();

    @Spy
    private ExecutorService executor = Executors.newFixedThreadPool(10);

    @Spy
    @InjectMocks
    FanaClient fanaClient;

    @Before
    public void setUp() {
        ReflectionTestUtils.setField(fanaClient, "api", "https://api-rate.fanatest.workers.dev/?price={price}");
    }


    @Test
    public void when_PriceIs100_Expect_CommissionWillBe10() throws ExecutionException, InterruptedException {
        when(restTemplate.exchange("https://api-rate.fanatest.workers.dev/?price={price}", HttpMethod.GET, httpEntity, String.class,100 ))
                .thenReturn(new ResponseEntity<String>("{\"comission\":10}", HttpStatus.ACCEPTED));

        List<String> commissions = fanaClient.calculateCommissions(Arrays.asList(100));

        assertEquals(Arrays.asList("10"), commissions);

    }

    @Test
    public void when_PriceListHaving10Records_Expect_HitApi10times() throws ExecutionException, InterruptedException {
        when(restTemplate.exchange("https://api-rate.fanatest.workers.dev/?price={price}", HttpMethod.GET, httpEntity, String.class,100 ))
                .thenReturn(new ResponseEntity<String>("{\"comission\":10}", HttpStatus.ACCEPTED));

        List<String> commissions = fanaClient.calculateCommissions(Arrays.asList(100,100,100,100,100,100,100,100,100,100));

        Mockito.verify(restTemplate, Mockito.times(10))
                .exchange("https://api-rate.fanatest.workers.dev/?price={price}", HttpMethod.GET, httpEntity, String.class,100 );

    }

    public HttpEntity<String> getHttpEntity(){
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.add("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
        return new HttpEntity<String>("parameters", headers);
    }


}

