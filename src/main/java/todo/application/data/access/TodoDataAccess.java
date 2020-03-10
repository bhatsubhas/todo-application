package todo.application.data.access;

import java.util.List;

import todo.application.data.entity.TodoTask;

public interface TodoDataAccess
{

	TodoTask create();

	void save( TodoTask todoTask );

	void update( TodoTask todoTask );

	void delete( TodoTask todoTask );

	TodoTask get( long taskId );

	List<TodoTask> getAll();

}