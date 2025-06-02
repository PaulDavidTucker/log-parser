package com.tucker.parser;

enum LogLevel {
    INFO,
    WARNING,
    ERROR,
}

public class LogEntry {

    private String timestamp;
    private String level;
    private String message;
    private LogLevel logLevel;

    //TODO potentially make this an object itself?
    private String LogSource;
    private String Threadname;
    private String ErrorCode;

    public LogEntry(
        String timestamp,
        String level,
        String message,
        LogLevel logLevel
    ) {
        this.timestamp = timestamp;
        this.level = level;
        this.message = message;
        this.logLevel = logLevel;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public String getLevel() {
        return level;
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

    public String getErrorCode() {
        return ErrorCode;
    }

    public String getLogLevelAsString() {
        return logLevel.name();
    }

    @Override
    public String toString() {
        return (
            "LogEntry{" +
            "timestamp='" +
            timestamp +
            '\'' +
            ", level='" +
            level +
            '\'' +
            ", message='" +
            message +
            '\'' +
            '}'
        );
    }
}
