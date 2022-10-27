package com.fana.demosheduler.service;

import com.fana.demosheduler.DemoShedulerApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    AtomicInteger currentLineNumber = new AtomicInteger(0);

    @Override
    public List<String> readFileByLine(String path, Integer numberOfLine) {
        List<String> listLines = new ArrayList<>();
        try (Stream<String> lines = Files.lines(Paths.get(path))) {
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
    public void writeToFile(String path, List<String> lines) {
        Path file = Paths.get(path);
        try{
            if (!Files.exists(file, LinkOption.NOFOLLOW_LINKS))
                Files.createFile(file);

            Files.write(file, lines, StandardCharsets.UTF_8, StandardOpenOption.APPEND);

        }catch (IOException e){
            logger.error(e.getStackTrace().toString());
        }
    }
}
