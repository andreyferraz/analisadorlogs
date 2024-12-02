package com.example.analisadorlogs.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.springframework.stereotype.Service;

import com.example.analisadorlogs.model.LogEntry;

@Service
public class LogProcessorService {

    public List<LogEntry> parseLogFile(InputStream inputStream) throws IOException {
        List<LogEntry> logs = new ArrayList<>();
        try(BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))){
            String line;
            while ((line = reader.readLine()) != null){
                if(line.matches("\\[.*\\] \\w+ - .*")){
                    String[] parts = line.split(" - ", 2);
                    String[] meta = parts[0].replace("[", "").replace("]", "").split(" ");
                    logs.add(new LogEntry(meta[0] + " " + meta[1], meta[2], parts[1]));
                }
            }
        }
        return logs;
    }

    public List<LogEntry> filterLogsByLevel(List<LogEntry> logs, String level){
        return logs.stream()
            .filter(log -> log.getLevel().equalsIgnoreCase(level))
            .collect(Collectors.toList());
    }

    public void generateCSVReport(List<LogEntry> logs, OutputStream outputStream) throws IOException{
        try(CSVPrinter printer = new CSVPrinter(new PrintWriter(outputStream), CSVFormat.DEFAULT)){
            printer.printRecord("Timestamp", "Level", "Message");
            for(LogEntry log : logs){
                printer.printRecord(log.getTimestamp(), log.getLevel(), log.getMessage());
            }
        }
    }
}
