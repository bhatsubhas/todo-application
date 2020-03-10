package todo.application.business;

import todo.application.data.access.TodoDataAccess;

public class TodoApplication
{
	private TodoDataAccess dataAccess;

	public TodoApplication(TodoDataAccess dataAccess)
	{
		this.dataAccess = dataAccess;
	}
}
