package org.java.smartrestaurant.util.converter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class DateTimeFormatterConfig implements WebMvcConfigurer {
    private static final Logger logger = LoggerFactory.getLogger(DateTimeFormatterConfig.class);
    @Override
    public void addFormatters(FormatterRegistry registry) {
        logger.info("Register Data and Time formatters");
        registry.addFormatter(new DateFormatter());
        registry.addFormatter(new TimeFormatter());
    }


}
