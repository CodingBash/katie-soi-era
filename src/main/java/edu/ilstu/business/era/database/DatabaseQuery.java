package edu.ilstu.business.era.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import edu.ilstu.business.era.exceptions.KatieActionFailedException;
import edu.ilstu.business.era.transferobjects.EventDatabaseTO;
import edu.ilstu.business.era.utilities.Utils;

@Component
public class DatabaseQuery
{

	@Autowired
	private DataSource dataSource;

	public void saveRSVP(String user, String eventId, String datetime, String classId) throws KatieActionFailedException
	{
		Connection connection = null;
		PreparedStatement prepStatement = null;
		boolean saved = false;
		ResultSet result = null;
		int rows = 0;
		try
		{
			String selectUser = "SELECT * FROM USER WHERE id=?";
			String insertUser = "INSERT INTO USER (id)  VALUES(?)";
			String insertEvent = "INSERT INTO event (eventid,date,classid) VALUES(?,?,?)";
			String insertUserEvent = "INSERT INTO user_event (user,event)  VALUES(?,?)";
			/*
			 * Check if the student user name exists in the user table.if not,
			 * add it
			 */
			connection = dataSource.getConnection();
			prepStatement = connection.prepareStatement(selectUser);
			prepStatement.setString(1, user);

			result = prepStatement.executeQuery();
			result.last();
			if (result.getRow() == 0)
			{
				prepStatement = connection.prepareStatement(insertUser);
				prepStatement.setString(1, user);
				rows = prepStatement.executeUpdate();
				if (rows != 0)
					saved = true;
			} else
				saved = true;
			/*
			 * Add the announcement to the database. We require the eventId and
			 * the respective classRefId
			 */
			prepStatement = connection.prepareStatement(insertEvent);
			prepStatement.setString(1, eventId);
			prepStatement.setLong(2, Utils.getDate(datetime));
			prepStatement.setString(3, classId);
			rows = prepStatement.executeUpdate();
			if (rows != 0)
				saved = true;
			else
				saved = false;
			/*
			 * Add the user and the announcement to the database.
			 */
			prepStatement = connection.prepareStatement(insertUserEvent);
			prepStatement.setString(1, user);
			prepStatement.setString(2, eventId);
			rows = prepStatement.executeUpdate();
			if (rows != 0)
				saved = true;
			else
				saved = false;

		} catch (Exception e)
		{
			throw new KatieActionFailedException(e.getMessage());
		}

		if (!saved)
		{
			throw new KatieActionFailedException("RSVP not saved");
		}
	}

	public void removeRSVP(String user, String eventId) throws KatieActionFailedException
	{
		Connection connection = null;
		PreparedStatement prepStatement = null;
		boolean removed = false;
		int rows = 0;
		try
		{

			String deleteRSVP = "DELETE FROM user_event WHERE user=? AND event=?";

			connection = dataSource.getConnection();
			prepStatement = connection.prepareStatement(deleteRSVP);
			prepStatement.setString(1, user);
			prepStatement.setString(2, eventId);
			rows = prepStatement.executeUpdate();
			if (rows != 0)
				removed = true;
			else
				removed = false;
		} catch (Exception e)
		{
			throw new KatieActionFailedException(e.getMessage());
		}

		if (!removed)
		{
			throw new KatieActionFailedException("RSVP not removed");
		}
	}

	/*
	 * To be called to show the user, his/her registered events. Use the eventId
	 * to get the information to display the information about the event
	 */
	public List<EventDatabaseTO> getUserRSVP(String user) throws KatieActionFailedException
	{
		Connection connection = null;
		PreparedStatement prepStatement = null;
		List<EventDatabaseTO> events = new ArrayList<EventDatabaseTO>();
		try
		{
			String userEvents = "SELECT ue.event, e.classid FROM user_event AS ue, event AS e WHERE user=? "
					+ "AND ue.event=e.eventid";

			connection = dataSource.getConnection();
			prepStatement = connection.prepareStatement(userEvents);
			prepStatement.setString(1, user);
			ResultSet result = prepStatement.executeQuery();
			while (result.next())
			{
				EventDatabaseTO event = new EventDatabaseTO();
				event.setEventId(result.getString(1));
				event.setClassId(result.getString(2));
				events.add(event);
			}
		} catch (Exception e)
		{
			throw new KatieActionFailedException(e.getMessage());
		}
		/*
		 * return a map (event, class) of event ID's the student has registered
		 * to.
		 */
		return events;
	}
}
