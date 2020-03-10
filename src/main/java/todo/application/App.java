/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package todo.application;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;

import todo.application.business.TodoApplication;
import todo.application.data.access.impl.TodoDataAccessImpl;
import todo.application.data.entity.TodoTask;
import todo.application.exceptions.TodoApplicationException;

public class App
{
	private static final String DASH_LINE = "-------------------------------------------------------------";
	private static PrintStream console = System.out;
	private static PrintStream error = System.err;

	public static void main( String[] args ) throws TodoApplicationException
	{
		boolean isContinue = true;
		TodoDataAccessImpl dataAccess = new TodoDataAccessImpl();
		TodoApplication todoApp = new TodoApplication( dataAccess );

		console.println( DASH_LINE );
		console.println( "Welcome to Todo Application" );
		console.println( DASH_LINE );

		while ( isContinue )
		{
			console.println( "\n\n(view[v], add[a], mark[m], delete[d], quit[q])" );
			console.println( DASH_LINE );
			String userCommand = getInputFromConsole( "Enter your choice [view]:" );
			if ( isMatchingCommand( userCommand, "quit", "q" ) )
			{
				console.println( "Bye Bye!" );
				isContinue = false;
			}
			else if ( isMatchingCommand( userCommand, "add", "a" ) )
			{
				addTodoTask( todoApp );
			}
			else if ( isMatchingCommand( userCommand, "view", "v" ) || userCommand.length() == 0 )
			{
				viewTodoTask( todoApp );
			}
			else if ( isMatchingCommand( userCommand, "mark", "m" ) )
			{
				markTodoTask( todoApp );

			}
			else if ( isMatchingCommand( userCommand, "delete", "d" ) )
			{
				deleteTodoTask( todoApp );
			}
			else
			{
				console.println( String.format( "%s is not a valid command", userCommand ) );
			}
		}
	}

	protected static boolean isMatchingCommand( String userCommand, String longCmd, String shortCmd )
	{
		return userCommand.equals( longCmd ) || userCommand.equals( shortCmd );
	}

	protected static void addTodoTask( TodoApplication todoApp )
	{
		try
		{
			String taskName = getInputFromConsole( "Enter task name to add:" );
			String targetDateStr = getInputFromConsole( "Enter target date [format:dd/MM/yyyy]:" );
			TodoTask todoTask = todoApp.createTodoTask( taskName, targetDateStr );
			console.println( "Todo task created" );
			console.println( todoTask );
		}
		catch ( TodoApplicationException e )
		{
			error.println( e.getMessage() );
		}
	}

	protected static void deleteTodoTask( TodoApplication todoApp )
	{
		try
		{
			Long taskId = getTodoTaskId( "Enter task id to delete:" );
			//TODO Call business logic to delete a todo task
		}
		catch ( TodoApplicationException e )
		{
			error.println( e.getMessage() );
		}
	}

	protected static void markTodoTask( TodoApplication todoApp )
	{
		try
		{
			Long taskId = getTodoTaskId( "Enter task id to mark complete:" );
			String completionDateStr = getInputFromConsole( "Enter target date [format:dd/MM/yyyy]:" );
			//TODO Call business logic to mark a todo task as complete
		}
		catch ( TodoApplicationException e )
		{
			error.println( e.getMessage() );
		}
	}

	protected static void viewTodoTask( TodoApplication todoApp )
	{
		try
		{
			String taskIdStr = getInputFromConsole( "Enter task id to view:" );
			if ( taskIdStr.isEmpty() || taskIdStr.equals( "\n" ) )
			{
				//TODO Call business logic to view all todo tasks
			}
			else
			{
				Long taskId = Long.parseLong( taskIdStr );
				TodoTask todoTask = todoApp.getTodoTask( taskId );
				console.println(todoTask);
			}
		}
		catch ( NumberFormatException e )
		{
			error.println( "That was not a valid task Id" );
		}
		catch ( TodoApplicationException e )
		{
			error.println( e.getMessage() );
		}
	}

	protected static String getInputFromConsole( String message ) throws TodoApplicationException
	{
		try
		{
			BufferedReader consoleReader = new BufferedReader( new InputStreamReader( System.in ) );
			console.print( message );
			return consoleReader.readLine();
		}
		catch ( IOException e )
		{
			throw new TodoApplicationException( e );
		}
	}

	protected static long getTodoTaskId( String message ) throws TodoApplicationException
	{
		String taskIdStr = "";
		try
		{
			taskIdStr = getInputFromConsole( message );
			return Long.parseLong( taskIdStr );
		}
		catch ( NumberFormatException e )
		{
			throw new TodoApplicationException( String.format( "Task Id must be a number, %s is not a valid number", taskIdStr ) );
		}
	}
}
