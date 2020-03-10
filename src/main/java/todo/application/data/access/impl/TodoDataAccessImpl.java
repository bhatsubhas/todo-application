package todo.application.data.access.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;
import org.hibernate.service.ServiceRegistry;

import java.util.List;

import todo.application.data.access.TodoDataAccess;
import todo.application.data.entity.TodoTask;

public class TodoDataAccessImpl implements TodoDataAccess
{
	private SessionFactory sessionFactory;

	public TodoDataAccessImpl()
	{
		ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().configure( "hibernate.cfg.xml" ).build();
		Metadata metadata = new MetadataSources( serviceRegistry ).buildMetadata();
		sessionFactory = metadata.getSessionFactoryBuilder().build();
	}

	@Override
	public TodoTask create()
	{
		return new TodoTask();
	}

	@Override
	public void save( TodoTask todoTask )
	{
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		session.save( todoTask );
		tx.commit();
		session.close();
	}

	@Override
	public void update( TodoTask todoTask )
	{
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		session.update( todoTask );
		tx.commit();
		session.close();
	}

	@Override
	public void delete( TodoTask todoTask )
	{
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		session.delete( todoTask );
		tx.commit();
		session.close();
	}

	@Override
	public TodoTask get( long taskId )
	{
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		TodoTask todoTask = session.find( TodoTask.class, taskId );
		tx.commit();
		session.close();
		return todoTask;
	}

	@Override
	@SuppressWarnings( "unchecked" )
	public List<TodoTask> getAll()
	{
		Session session = sessionFactory.openSession();
		Query<TodoTask> query = session.createQuery( "from TodoTask" );
		List<TodoTask> todoTasks = query.getResultList();
		session.close();
		return todoTasks;
	}

}
