package com.example.analisadorlogs.service;

import com.example.analisadorlogs.model.LogEntry;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LogProcessorServiceTest {

    private final LogProcessorService service = new LogProcessorService();

    @Test
    void testParseLogFile() throws IOException {
        String logContent = """
                [2023-12-01 10:00:00] INFO - Application started
                [2023-12-01 10:01:00] ERROR - NullPointerException
                [2023-12-01 10:02:00] WARN - Disk space low
                """;
        ByteArrayInputStream inputStream = new ByteArrayInputStream(logContent.getBytes());

        List<LogEntry> logs = service.parseLogFile(inputStream);

        assertEquals(3, logs.size());
        assertEquals("INFO", logs.get(0).getLevel());
        assertEquals("ERROR", logs.get(1).getLevel());
        assertEquals("Disk space low", logs.get(2).getMessage());
    }

    @Test
    void testFilterLogsByLevel() {
        List<LogEntry> logs = List.of(
                new LogEntry("2023-12-01 10:00:00", "INFO", "Application started"),
                new LogEntry("2023-12-01 10:01:00", "ERROR", "NullPointerException"),
                new LogEntry("2023-12-01 10:02:00", "WARN", "Disk space low")
        );

        List<LogEntry> errorLogs = service.filterLogsByLevel(logs, "ERROR");

        assertEquals(1, errorLogs.size());
        assertEquals("NullPointerException", errorLogs.get(0).getMessage());
    }

    @Test
    void testGenerateCSVReport() throws IOException {
        List<LogEntry> logs = List.of(
                new LogEntry("2023-12-01 10:00:00", "INFO", "Application started"),
                new LogEntry("2023-12-01 10:01:00", "ERROR", "NullPointerException")
        );

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        service.generateCSVReport(logs, outputStream);

        String csvContent = outputStream.toString();
        String expectedHeader = "Timestamp,Level,Message";
        assertEquals(true, csvContent.contains(expectedHeader));
        assertEquals(true, csvContent.contains("NullPointerException"));
    }
}
