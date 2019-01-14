package com.sample.springboot.util;

import java.time.Clock;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Utility class for Date and Time
 * 
 * @author fabianorosa
 * @since 1.0.0
 *
 */
public class DateTimeUtil {
	private static DateTimeUtil instance;
	
	// sample format: 2018-07-17T09:59:51.312Z
	public static final String ISO_8601_24H_FULL_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";
	private static DateTimeFormatter formatterDateTimeUTC = DateTimeFormatter.ofPattern(ISO_8601_24H_FULL_FORMAT);

	private DateTimeUtil() {	
	}
	
	/**
	 * Gets an instance of an DateTimeUtil util.
	 * 
	 * @return the instance
	 */
	public static synchronized DateTimeUtil getInstance() {
		if (instance == null) {
			instance = new DateTimeUtil();
		}
		return instance;
	}
		
	/**
	 * Format the LocalDateTime with the ISO_8601 mask 
	 * @return String formated with LocalDateTime
	 */
	public String getDateTimeNowUTCFormatted(LocalDateTime localDateTime) {
		return formatterDateTimeUTC.format(localDateTime);
	}
	
	/**
	 * Return the current local datetime in UTC
	 * 
	 * @return LocalDateTime in UTC
	 */
	public LocalDateTime getLocalDateTimeNowUTC() {
		return LocalDateTime.now(Clock.systemUTC());
	}
	
	/**
	 * Parse a timestamp with the ISO_8601 mask
	 * 
	 * @param timestamp Timestamp value
	 * @return LocalDateTime with the timestamp
	 * @throws DateTimeParseException Error when the string format is wrong
	 */
	public LocalDateTime getDateTimeNowUTCParsed(String timestamp) throws DateTimeParseException {
		ZonedDateTime zp = ZonedDateTime.parse(timestamp);
		return LocalDateTime.ofInstant(zp.toInstant(), ZoneOffset.UTC);
	}
	
	/**
	 * Return the long timestamp from LocalDateTime
	 * 
	 * @param timestamp Timestamp value
	 * @return long timestamp
	 */
	public Long getTimestampMillseconds(LocalDateTime timestamp) {
		return timestamp.toEpochSecond(ZoneOffset.UTC) * 1000;
	}
}
