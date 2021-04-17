package com.anthem.utils;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;

public class ResponseUtils {

    private static ResponseUtils instance;

    private static String TIMESTAMP_FORMAT_RFC3339 = "yyyy-MM-dd'T'HH:mm:ss'Z'";
    private static String TIMESTAMP_FORMAT_RFC3339_MILS = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";

    private static DateTimeFormatter dtFormatter = DateTimeFormatter.ofPattern(TIMESTAMP_FORMAT_RFC3339).withZone(ZoneId.of("Z"));
    private static DateTimeFormatter dtFormatterMils = DateTimeFormatter.ofPattern(TIMESTAMP_FORMAT_RFC3339_MILS).withZone(ZoneId.of("Z"));

    private static DateTimeFormatter[] sdFormatters = new DateTimeFormatter[]{dtFormatter, dtFormatterMils};


    public static String formatToRFC3339Time(long creationTime) {

        try {
            return dtFormatter.format(Instant.ofEpochMilli(creationTime));
        } catch (DateTimeException e) {
            throw new RuntimeException("Failed to format " + creationTime);
        }
    }

    public static String formatToRFC3339TimeMillis(long creationTime) {

        try {
            return dtFormatterMils.format(Instant.ofEpochMilli(creationTime));
        } catch (DateTimeException e) {
            throw new RuntimeException("Failed to format " + creationTime);
        }
    }


    public static Date parse(String rfc3339DateTimeStr) {
        for (DateTimeFormatter formatter : sdFormatters) {
            try {
                ZonedDateTime zonedDateTime = ZonedDateTime.parse(rfc3339DateTimeStr, formatter);
                return Date.from(zonedDateTime.toInstant());
            } catch (DateTimeParseException e) {
            }
        }
        throw new RuntimeException("Failed to parse " + rfc3339DateTimeStr);
    }

    public static LocalDate convertToLocalDateViaSqlDate(Date dateToConvert) {
        return new java.sql.Date(dateToConvert.getTime()).toLocalDate();
    }
}
