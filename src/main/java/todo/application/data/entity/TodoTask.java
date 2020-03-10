package todo.application.data.entity;

import org.hibernate.annotations.GenericGenerator;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table( name = "TODOTASK" )
public class TodoTask
{
	@Id
	@GeneratedValue( generator = "increment" )
	@GenericGenerator( name = "increment", strategy = "increment" )
	private Long taskId;

	@Column( name = "task_name" )
	private String taskName;

	@Temporal( TemporalType.DATE )
	@Column( name = "target_date" )
	private Date targetDate;

	@Temporal( TemporalType.DATE )
	@Column( name = "completion_date" )
	private Date completionDate;

	public Long getTaskId()
	{
		return taskId;
	}

	public void setTaskId( Long taskId )
	{
		this.taskId = taskId;
	}

	public void setTaskName( String taskName )
	{
		this.taskName = taskName;
	}

	public void setTargetDate( Date targetDate )
	{
		this.targetDate = targetDate;
	}

	public String getTaskName()
	{
		return taskName;
	}

	public Date getTargetDate()
	{
		return targetDate;
	}

	public Date getCompletionDate()
	{
		return completionDate;
	}

	public void setCompletionDate( Date completionDate )
	{
		this.completionDate = completionDate;
	}

	@Override
	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		sb.append( "-------------------------------------------------------------\n" );
		sb.append( "|Id			:" ).append( taskId ).append( "\n" );
		sb.append( "|Task			:" ).append( taskName ).append( "\n" );
		sb.append( "|Target Date		:" ).append( targetDate ).append( "\n" );
		sb.append( "|Completion Date	:" ).append( completionDate == null ? "" : completionDate ).append( "\n" );
		sb.append( "-------------------------------------------------------------\n" );
		return sb.toString();
	}

}
