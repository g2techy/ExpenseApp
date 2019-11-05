package com.g2.tradingApp.utilities;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class CommonUtility {

    public static final String DATE_TIME_FORMAT = "MM/DD/YYYY";

    public static boolean isValidDate(String dateStr) {
        try {
            LocalDate.parse(dateStr, DateTimeFormatter.ofPattern(DATE_TIME_FORMAT));
        } catch (DateTimeParseException e) {
            return false;
        }
        return true;
    }
}
