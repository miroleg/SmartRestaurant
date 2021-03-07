package org.java.smartrestaurant.util.converter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.expression.ParseException;
import org.springframework.format.Formatter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class DateFormatter implements Formatter<LocalDate> {
    private static final Logger logger = LoggerFactory.getLogger(DateFormatter.class);

    @Override
    public LocalDate parse(String text, Locale locale) throws ParseException {
        logger.info("Parse string to LocalDate");
        return LocalDate.parse(text, DateTimeFormatter.ISO_DATE);
    }

    @Override
    public String print(LocalDate object, Locale locale) {
        logger.info("Print LocalDate");
        return DateTimeFormatter.ISO_DATE.format(object);
    }
}



