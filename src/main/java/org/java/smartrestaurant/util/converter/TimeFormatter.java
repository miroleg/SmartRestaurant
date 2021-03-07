package org.java.smartrestaurant.util.converter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.format.Formatter;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import static org.java.smartrestaurant.util.DateTimeUtil.parseLocalTime;

public class TimeFormatter implements Formatter<LocalTime> {
    private static final Logger logger = LoggerFactory.getLogger(TimeFormatter.class);

    @Override
    public LocalTime parse(String text, Locale locale) {
        logger.info("Parse string to LocalTime");
        return parseLocalTime(text);
    }

    @Override
    public String print(LocalTime lt, Locale locale) {
        logger.info("Print LocaleTime");
        return lt.format(DateTimeFormatter.ISO_LOCAL_TIME);
    }
}
