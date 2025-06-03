package com.tucker.parser;

import com.tucker.parser.obj.LogEntry;
import com.tucker.parser.obj.LogLevel;
import java.time.LocalDateTime;

public class Main {

    public static void main(String[] args) {
        LocalDateTime timestamp = LocalDateTime.now();
        LogLevel level = LogLevel.INFO;
        String message = "This is a test message!";

        LogEntry newEntry = new LogEntry(
            timestamp,
            message,
            level,
            "Source",
            "name",
            "name"
        );

        System.out.println(newEntry.toString());
    }
}
