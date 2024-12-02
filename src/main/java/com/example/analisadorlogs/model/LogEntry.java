package com.example.analisadorlogs.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LogEntry {
    private String timestamp;
    private String level; /// info, warn, error
    private String message;
}
