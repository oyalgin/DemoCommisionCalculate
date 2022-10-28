package com.fana.demosheduler.service;

import com.fana.demosheduler.DemoShedulerApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@Component
public class FileOperationServiceImpl implements FileOperationService {
    private static final Logger logger = LoggerFactory.getLogger(DemoShedulerApplication.class);

    private String path;

    public FileOperationServiceImpl(@Value("${filePath}") String path){
        this.path =path;
    }

    AtomicInteger currentLineNumber = new AtomicInteger(0);

    @Override
    public List<String> readFileByLine(String fileName, Integer numberOfLine) {
        List<String> listLines = new ArrayList<>();
        try (Stream<String> lines = Files.lines(Paths.get(path+fileName))) {
            listLines = lines.skip(currentLineNumber.getAndAdd(numberOfLine))
                    .limit(numberOfLine)
                    .collect(Collectors.toList());
        }
        catch(IOException e){
            logger.error(e.getStackTrace().toString());
        }

        return listLines;
    }

    @Override
    public void writeToFile(String fileName, List<String> lines) {
        Path file = Paths.get(path+fileName);
        try{
            if (!Files.exists(file, LinkOption.NOFOLLOW_LINKS))
                Files.createFile(file);

            Files.write(file, lines, StandardCharsets.UTF_8, StandardOpenOption.APPEND);

        }catch (IOException e){
            logger.error(e.getStackTrace().toString());
        }
    }
}
