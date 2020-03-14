package todo.application.business;

import java.util.Date;
import java.util.List;

import todo.application.data.access.TodoDataAccess;
import todo.application.data.entity.TodoTask;
import todo.application.exceptions.TodoApplicationException;
import todo.application.util.DateUtil;

public class TodoApplication
{
	private TodoDataAccess dataAccess;
	private DateUtil dateUtil;

	public TodoApplication( TodoDataAccess dataAccess, DateUtil dateUtil )
	{
		this.dataAccess = dataAccess;
		this.dateUtil = dateUtil;
	}

	public TodoTask createTodoTask( String taskName, String targetDateStr ) throws TodoApplicationException
	{
		Date targetDate = dateUtil.convertToDate( targetDateStr );
		if ( targetDate.before( dateUtil.getToday() ) )
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
		Date completionDate = dateUtil.convertToDate( completionDateStr );
		TodoTask todoTask = getTodoTask( taskId );
		if ( completionDate.before( todoTask.getTargetDate() ) )
		{
			throw new TodoApplicationException( "Completion date cannot be less than target date" );
		}
		todoTask.setCompletionDate( completionDate );
		dataAccess.update( todoTask );
	}

	public void deleteTodoTask( long taskId ) throws TodoApplicationException
	{
		TodoTask todoTask = getTodoTask( taskId );
		if ( todoTask.getCompletionDate() == null )
		{
			throw new TodoApplicationException( "Only completed task can be deleted" );
		}
		dataAccess.delete( todoTask );

	}

	public List<TodoTask> getAllTodoTasks()
	{
		return dataAccess.getAll();
	}
}
