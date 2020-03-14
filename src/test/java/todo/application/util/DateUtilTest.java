package todo.application.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import todo.application.exceptions.TodoApplicationException;

public class DateUtilTest
{
	@Test
	@DisplayName( "Test to check String to Date Conversion when format is correct" )
	void testConvertToDateWithInputCorrectDatePattern() throws TodoApplicationException
	{
		DateUtil dateUtil = new DateUtil( "dd/MM/yyyy" );
		Date expectedDate = new GregorianCalendar( 2020, 01, 13 ).getTime();
		Date actualDate = dateUtil.convertToDate( "13/02/2020" );
		assertEquals( expectedDate, actualDate );

		dateUtil = new DateUtil( "MM/yyyy" );
		expectedDate = new GregorianCalendar( 2020, 01, 01 ).getTime();
		actualDate = dateUtil.convertToDate( "02/2020" );
		assertEquals( expectedDate, actualDate );
	}

	@Test
	@DisplayName( "Test to check String to Date Conversion when format is incorrect" )
	void testConvertToDateWithInputIncorrectDatePattern() throws TodoApplicationException
	{
		final DateUtil dateUtil = new DateUtil( "dd/MM/yyyy" );
		TodoApplicationException exception = assertThrows( TodoApplicationException.class, () -> dateUtil.convertToDate( "13/2020" ) );
		assertEquals( "Date must be in 'dd/MM/yyyy' format", exception.getMessage() );

		DateUtil dateUtil2 = new DateUtil( "MM/yyyy" );
		exception = assertThrows( TodoApplicationException.class, () -> dateUtil2.convertToDate( "02/" ) );
		assertEquals( "Date must be in 'MM/yyyy' format", exception.getMessage() );
	}

	@Test
	@DisplayName( "Test to check Date to String Conversion" )
	void testConvertToStringWithCorrectDate() throws TodoApplicationException
	{
		DateUtil dateUtil = new DateUtil( "dd/MM/yyyy" );
		Date testDate = new GregorianCalendar( 2020, 01, 29 ).getTime();
		String expectedDateStr = "29/02/2020";
		String actualDateStr = dateUtil.convertToString( testDate );
		assertEquals( expectedDateStr, actualDateStr );

		dateUtil = new DateUtil( "MM/yyyy" );
		testDate = new GregorianCalendar( 2020, 01, 29 ).getTime();
		expectedDateStr = "02/2020";
		actualDateStr = dateUtil.convertToString( testDate );
		assertEquals( expectedDateStr, actualDateStr );
	}

	@Test
	@DisplayName( "Test to check today's date is returned correctly" )
	void testGetToday() throws TodoApplicationException
	{
		Calendar calendarInstance = Calendar.getInstance();
		int day = calendarInstance.get( Calendar.DAY_OF_MONTH );
		int month = calendarInstance.get( Calendar.MONTH );
		int year = calendarInstance.get( Calendar.YEAR );
		
		Date expectedToday = new GregorianCalendar( year, month, day ).getTime();
		DateUtil dateUtil = new DateUtil( "dd/MM/yyyy" );
		Date actualToday = dateUtil.getToday();
		assertEquals( expectedToday, actualToday );

		dateUtil = new DateUtil( "MM/yyyy" );
		expectedToday = new GregorianCalendar( year, month, 1 ).getTime();
		actualToday = dateUtil.getToday();
		assertEquals( expectedToday, actualToday );
	}
}
