package todo.application.exceptions;

public class TodoApplicationException extends Exception
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public TodoApplicationException()
	{
	}

	public TodoApplicationException( String message )
	{
		super( message );
	}

	public TodoApplicationException( Throwable cause )
	{
		super( cause );
	}

	public TodoApplicationException( String message, Throwable cause )
	{
		super( message, cause );
	}

	public TodoApplicationException( String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace )
	{
		super( message, cause, enableSuppression, writableStackTrace );
	}

}
