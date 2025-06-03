package com.tucker.parser.obj;

//https://docs.oracle.com/javase/6/docs/api/java/net/HttpURLConnection.html
import java.net.HttpURLConnection;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LogEntry {

    private LocalDateTime timestamp;
    private String message;
    private LogLevel logLevel;

    //TODO potentially make this an object itself?
    private String LogSource;
    private String Threadname;
    private int LogCode;

    // Formatting options
    public DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(
        "yyyy-MM-dd HH:mm:ss.SSS"
    );

    public LogEntry(
        LocalDateTime timestamp,
        String message,
        LogLevel logLevel,
        String LogSource,
        String Threadname,
        int LogCode
    ) {
        this.timestamp = timestamp;
        this.message = message;
        this.logLevel = logLevel;
        this.LogSource = LogSource;
        this.Threadname = Threadname;
        this.LogCode = LogCode;
    }

    public DateTimeFormatter getDateTimeFormatter() {
        return dateFormatter;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public String getMessage() {
        return message;
    }

    public LogLevel getLogLevel() {
        return logLevel;
    }

    public String getLogSource() {
        return LogSource;
    }

    public String getThreadname() {
        return Threadname;
    }

    public int getLogCode() {
        return LogCode;
    }

    public String getLogLevelAsString() {
        return logLevel.name();
    }

    @Override
    public String toString() {
        return String.format(
            "[%s] [%s] %s: %s -%s -%s",
            timestamp.format(dateFormatter),
            LogCode,
            logLevel,
            message,
            LogSource,
            Threadname
        );
    }
}
