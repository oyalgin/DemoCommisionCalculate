package com.fana.demosheduler.service;

import org.springframework.stereotype.Service;

import java.util.List;


public interface FileOperationService {



    List<String> readFileByLine(String fileName,Integer numberOfLine);

    void writeToFile(String path,List<String> lines);

}
