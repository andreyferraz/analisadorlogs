package com.example.analisadorlogs.controller;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import org.springframework.http.HttpHeaders;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.analisadorlogs.model.LogEntry;
import com.example.analisadorlogs.service.LogProcessorService;

@RestController
@RequestMapping("/logs")
public class LogController {

    @Autowired
    private LogProcessorService logProcessorService;

    @PostMapping("/upload")
    public ResponseEntity<?> uploadLogFile(@RequestParam("file") MultipartFile file) {
        try {
            List<LogEntry> logs = logProcessorService.parseLogFile(file.getInputStream());
            return ResponseEntity.ok(logs);
        } catch (IOException e) {
            return ResponseEntity.badRequest().body("Erro ao processar arquivo: " + e.getMessage());
        }
    }

    @GetMapping("/report")
    public ResponseEntity<byte[]> generateCSVReport(@RequestParam("level") String level) {
        try {
            List<LogEntry> logs = logProcessorService.filterLogsByLevel(logProcessorService.getAllLogs(), level);
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            logProcessorService.generateCSVReport(logs, outputStream);

            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\\\"report.csv\\\"")
                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .body(outputStream.toByteArray());
        } catch (IOException e) {
            return ResponseEntity.internalServerError().body(null);
        }
    }

    @GetMapping("/metrics")
    public ResponseEntity<List<LogEntry>> getMetrics(@RequestParam(value = "level", required = false) String level) {
        List<LogEntry> logs = logProcessorService.getAllLogs();
        if (level != null) {
            logs = logProcessorService.filterLogsByLevel(logs, level);
        }
        return ResponseEntity.ok(logs);
    }

    @PostMapping("/upload")
    public ResponseEntity<?> uploadAndProcessLogs(@RequestParam("file") MultipartFile file) {
        try {
            List<LogEntry> logs = logProcessorService.parseLogFile(file.getInputStream());
            return ResponseEntity.ok("Logs processados com sucesso! Total: " + logs.size());
        } catch (IOException e) {
            return ResponseEntity.badRequest().body("Erro ao processar arquivo: " + e.getMessage());
        }
    }

    @GetMapping("/filter")
    public ResponseEntity<List<LogEntry>> filterLogs(@RequestParam(value = "level", required = false) String level) {
        List<LogEntry> logs = logProcessorService.getAllLogs();
        if (level != null) {
            logs = logProcessorService.filterLogsByLevel(logs, level);
        }
        return ResponseEntity.ok(logs);
    }

}
