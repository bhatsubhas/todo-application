package todo.application.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import todo.application.exceptions.TodoApplicationException;

public class DateUtil
{
	private String datePattern;
	private DateFormat dateFormat;

	public DateUtil( String datePattern )
	{
		this.datePattern = datePattern;
		this.dateFormat = new SimpleDateFormat( datePattern );
	}

	public Date convertToDate( String dateString ) throws TodoApplicationException
	{
		try
		{
			return dateFormat.parse( dateString );
		}
		catch ( ParseException e )
		{
			throw new TodoApplicationException( String.format( "Date must be in '%s' format", datePattern ) );
		}
	}

	public String convertToString( Date date )
	{
		return dateFormat.format( date );
	}

	public Date getToday() throws TodoApplicationException
	{
		return convertToDate( convertToString( new Date() ) );
	}
}
