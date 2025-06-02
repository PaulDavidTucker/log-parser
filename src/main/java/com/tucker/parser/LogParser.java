package com.tucker.parser;

import java.util.List;

public class LogParser {

    private static final String REGEX_LOG_PATTERN =
        "(\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2},\\d{3})\\s+" + // Timestamp
        "([A-Z]+)\\s+" + // Level (INFO, WARN, etc.)
        "(?:\\[([^\\]]+)\\]\\s+)?" + // Thread (optional)
        "(?:([\\w.]+)\\s+-\\s+)?" + // Logger name (optional)
        "(.*)"; // Message
}
