package com.interzonedev.herokuspringdemo;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TestUtils {
    /**
     * Formats the specified date according to the specified format.
     * 
     * @param date - The date to format.
     * @param dateFormat - The format for the date.
     * 
     * @return Returns the specified date according to the specified format.
     */
    public static String formatDate(Date date, String dateFormat) {
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        String dateString = sdf.format(date);
        return dateString;
    }

    /**
     * Parses the specified date string using the specified format into a {@code Date} in the default {@code TimeZone}.
     * 
     * @param dateString - The date string to be parsed.
     * @param dateFormat - The format of the date to be parsed.
     * 
     * @return Returns a {@code Date} object parsed from the specified date string using the specified format in the
     *         default {@code TimeZone} .
     * 
     * @throws ParseException Thrown if the specified input can not be parsed as a date.
     */
    public static Date parseDateInDefaultTimeZone(String dateString, String dateFormat) throws ParseException {
        DateFormat formatter = new SimpleDateFormat(dateFormat);
        Date date = (Date) formatter.parse(dateString);
        return date;
    }

    /**
     * Gets a {@code Date} object set to midnight on January 1, 1970 in the default {@code TimeZone}.
     * 
     * @return Returns a {@code Date} object set to midnight on January 1, 1970 in the default {@code TimeZone}.
     * 
     * @throws ParseException
     */
    public static Date getEpoch() throws ParseException {
        return parseDateInDefaultTimeZone("1970-01-01 00:00:00", "yyyy-MM-dd HH:mm:ss");
    }
}
