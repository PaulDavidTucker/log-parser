package com.tucker.parser;

import com.tucker.parser.obj.*;
import java.util.List;
import java.util.Optional;
import java.util.regex.*;

public class LogParser {

    private static final String REGEX_STRING_PATTERN =
        "(\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2},\\d{3})\\s+" + // Timestamp
        "([A-Z]+)\\s+" + // Level (INFO, WARN, etc.)
        "(?:\\[([^\\]]+)\\]\\s+)?" + // Thread (optional)
        "(?:([\\w.]+)\\s+-\\s+)?" + // Logger name (optional)
        "(.*)"; // Message

    private Pattern pattern = Pattern.compile(REGEX_STRING_PATTERN);

    public Optional<LogEntry> parseLine(String LineIn) {
        Matcher matcher = pattern.matcher(LineIn);
        if (matcher.matches()) {
            try {
                LocalDateTime timestamp = LocalDateTime.parse(
                    matcher.group(1),
                    LogEntry.dateFormatter;
                );
                String level = matcher.group(2);
                // String threadName = matcher.group(3); // If present
                // String loggerName = matcher.group(4); // If present
                String message = matcher.group(5);

                LogEntry entry = new LogEntry(timestamp, level, message);

                // Attempt to extract error code (example)
                Pattern errorCodePattern = Pattern.compile(
                    ".*ErrorCode: (\\w+).*"
                );
                Matcher ecMatcher = errorCodePattern.matcher(message);
                if (ecMatcher.find()) {
                    entry.setErrorCode(ecMatcher.group(1));
                }

                return Optional.of(entry);
            } catch (Exception e) {
                System.err.println(
                    "Failed to parse log line at " +
                    LineIn +
                    " With Exception: " +
                    e.getMessage()
                );
            }
        }

        return Optional.empty();
    }
}
