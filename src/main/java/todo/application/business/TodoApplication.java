package todo.application.business;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import todo.application.data.access.TodoDataAccess;
import todo.application.data.entity.TodoTask;
import todo.application.exceptions.TodoApplicationException;

public class TodoApplication
{
	private static final String DATE_PATTERN = "dd/MM/yyyy";
	private static DateFormat dateFormat = new SimpleDateFormat( DATE_PATTERN );
	private TodoDataAccess dataAccess;

	public TodoApplication( TodoDataAccess dataAccess )
	{
		this.dataAccess = dataAccess;
	}

	public TodoTask createTodoTask( String taskName, String targetDateStr ) throws TodoApplicationException
	{
		try
		{
			Date targetDate = dateFormat.parse( targetDateStr );
			if ( targetDate.before( new Date() ) )
			{
				throw new TodoApplicationException( "Task cannot be created for past date" );
			}
			if ( taskName.isEmpty() )
			{
				throw new TodoApplicationException( "Task name cannot be empty" );
			}
			TodoTask todoTask = dataAccess.create();
			todoTask.setTaskName( taskName );
			todoTask.setTargetDate( targetDate );
			dataAccess.save( todoTask );
			return todoTask;
		}
		catch ( ParseException e )
		{
			throw new TodoApplicationException( String.format( "Target date should be in %s format", DATE_PATTERN ) );
		}
	}

	public TodoTask getTodoTask( long taskId ) throws TodoApplicationException
	{
		TodoTask todoTask = dataAccess.get( taskId );
		if ( todoTask == null )
		{
			throw new TodoApplicationException( String.format( "Todo task with id %d not found", taskId ) );
		}
		return todoTask;
	}

	public void updateTodoTask( long taskId, String completionDateStr ) throws TodoApplicationException
	{
		try
		{
			Date completionDate = dateFormat.parse( completionDateStr );
			TodoTask todoTask = dataAccess.get( taskId );
			if ( completionDate.before( todoTask.getTargetDate() ) )
			{
				throw new TodoApplicationException( "Completion date cannot be less than target date" );
			}
			todoTask.setCompletionDate( completionDate );
			dataAccess.update( todoTask );
		}
		catch ( ParseException e )
		{
			throw new TodoApplicationException( "Completion date should be in dd/MM/yyyy format" );
		}
	}
}
