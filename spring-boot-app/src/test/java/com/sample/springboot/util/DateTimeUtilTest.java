package com.sample.springboot.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class DateTimeUtilTest {

	@Test
	public void convertToStringDateTimeNowUTC() {
		LocalDateTime now = DateTimeUtil.getInstance().getLocalDateTimeNowUTC();		
		String formated = DateTimeUtil.getInstance().getDateTimeNowUTCFormatted(now);
		
		Assert.assertNotNull(formated);
	}
	
	@Test
	public void convertToDateTimeUTCFromString() {
		LocalDateTime now = DateTimeUtil.getInstance().getLocalDateTimeNowUTC();		
		String formated = DateTimeUtil.getInstance().getDateTimeNowUTCFormatted(now);
		
		LocalDateTime parsed = DateTimeUtil.getInstance().getDateTimeNowUTCParsed(formated);
		
		Assert.assertEquals(now, parsed);	
	}		
	
	@Test
	public void getTimestampMillseconds() {
		LocalDateTime now = DateTimeUtil.getInstance().getLocalDateTimeNowUTC();				
		Long miliseconds = DateTimeUtil.getInstance().getTimestampMillseconds(now);
		
		Assert.assertEquals(DateTimeUtil.getInstance().getTimestampMillseconds(now), miliseconds);	
	}	
	
	@Test
	public void parseStringToDateTime() {		
		String timestamp = "2018-07-17T09:59:51.312Z";		
		LocalDateTime parsed = DateTimeUtil.getInstance().getDateTimeNowUTCParsed(timestamp);
		
		Assert.assertNotNull(parsed);
	}	
	
	@Test(expected = DateTimeParseException.class)
	public void validateInvalidMask() {		
		String timestamp = "abcdXPTO-07-17T09:59:51.312Z";		
		
		Assert.assertNull(DateTimeUtil.getInstance().getDateTimeNowUTCParsed(timestamp));
	}	
}
