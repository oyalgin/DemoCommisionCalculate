package com.fana.demosheduler;


import com.fana.demosheduler.client.FanaClient;
import com.fana.demosheduler.service.FileOperationService;
import com.fana.demosheduler.service.FileOperationServiceImpl;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import java.io.IOException;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CommissionCalculatorFacadeTests {

    @Spy
    FileOperationService fileOperationService = new FileOperationServiceImpl("src/test/resources/");

    @Mock
    FanaClient fanaClient;



    @InjectMocks
    CommissionCalculatorFacade commissionCalculatorFacade;


    @Test
    public void when_OrderFilePlacedTestResource_Expect_CommissionFileCreated() throws IOException {

        when(fanaClient.calculateCommissions(Arrays.asList(100,200,300)))
                .thenReturn(Arrays.asList("10","20","30"));

        commissionCalculatorFacade.process();

        assertArrayEquals(Arrays.asList("10","20","30").toArray(), Files.readAllLines(Paths.get("src/test/resources/OrderCommission.txt")).toArray());

    }



    @After
    public void removeFile() throws IOException {
        FileChannel.open(Paths.get("src/test/resources/OrderCommission.txt"), StandardOpenOption.WRITE).truncate(0).close();
    }


}
