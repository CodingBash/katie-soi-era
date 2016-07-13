package edu.ilstu.business.era.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

import edu.ilstu.business.era.exceptions.KatieActionFailedException;
import edu.ilstu.business.era.utilities.Utils;

/*
 * This class will query the local database for saving users, events, registering and un-registering RSVP and location check-in
 */
public class DBQuery {

	private Connection connection = null;

	private PreparedStatement prepStatement = null;

	public DBQuery() {
		connection = DBConnection.getDBConnection().getConnection();
	}

	/*
	 * To be called when the user responds to RSVP
	 * user - User login id - 'rsaripa'
	 * eventId - its the announcement ID
	 * datetime - the date time of the announcement. Use the datetime from the LoudCloud response.
	 * classId - the class refId 
	 */
	public boolean saveRSVP(String user, String eventId, String datetime, String classId) {
		boolean saved = false;
		ResultSet result = null;
		int rows = 0;
		try {
			String selectUser = "SELECT * FROM USER WHERE id=?";
			String insertUser = "INSERT INTO USER (id)  VALUES(?)";
			String insertEvent = "INSERT INTO event (eventid,date,classid) VALUES(?,?,?)";
			String insertUserEvent = "INSERT INTO user_event (user,event)  VALUES(?,?)";
			/*
			 * Check if the student user name exists in the user table.if not,
			 * add it
			 */
			prepStatement = connection.prepareStatement(selectUser);
			prepStatement.setString(1, user);

			result = prepStatement.executeQuery();
			result.last();
			if (result.getRow() == 0) {
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

		} catch (Exception e) {
			new KatieActionFailedException(e.getMessage());
		}
		/*
		 * return weather the records have been saved successfully or not. 
		 */
		return saved;
	}
	/*
	 * To be called when user checks in.
	 * user - User id - 'rsaripa'
	 * eventId - its the announcement ID
	 * location - string representation of latitude and longitude
	 */
	public boolean saveLocation(String user, String eventId, String location) {
		boolean saved = false;
		int rows = 0;
		try {
			String insertLocation = "INSERT INTO location (user,location,eventid) VALUES(?,?,?)";
			prepStatement = connection.prepareStatement(insertLocation);
			prepStatement.setString(1, user);
			prepStatement.setString(2, eventId);
			prepStatement.setString(3, location);
			rows = prepStatement.executeUpdate();
			if (rows != 0)
				saved = true;
			else
				saved = false;
		} catch (Exception e) {
			new KatieActionFailedException(e.getMessage());
		}
		/*
		 * return weather the records have been saved successfully or not. 
		 */
		return saved;
	}
	/*
	 * To be called to show the user, his/her registered events. 
	 * Use the eventId to get the information to display the information about the event
	 */
	public Map<String,String> getUserRSVP(String user) {

		Map<String,String> events = new HashMap<String,String>();
		try {
			String userEvents = "SELECT ue.event, e.classid FROM user_event AS ue, event AS e WHERE user=? "
					+ "AND ue.event=e.eventid";
			prepStatement = connection.prepareStatement(userEvents);
			prepStatement.setString(1, user);
			ResultSet result = prepStatement.executeQuery();
			if (result.next()) {
				events.put(result.getString(1),result.getString(2));
			}
		} catch (Exception e) {
			new KatieActionFailedException(e.getMessage());
		}
		/*
		 * return a map (event, class) of event ID's the student has registered to. 
		 */
		return events;
	}
	/*
	 * To be called to delete RSVP from the database.
	 */
	public boolean removeRSVP(String user,String eventId){
		boolean removed = false;
		int rows = 0;
		try {
			String deleteRSVP = "DELETE FROM user_event WHERE user=? AND event=?";
			prepStatement = connection.prepareStatement(deleteRSVP);
			prepStatement.setString(1, user);
			prepStatement.setString(2, eventId);
			rows = prepStatement.executeUpdate();
			if (rows != 0)
				removed = true;
			else
				removed = false;
		} catch (Exception e) {
			new KatieActionFailedException(e.getMessage());
		}
		/*
		 * return weather the records have been saved successfully or not. 
		 */
		return removed;
	}

	public static void main(String args[]) {

		DBQuery db = new DBQuery();
		// db.saveRSVP("rsaripa", "23b9ad0c-b206-4226-a372-302065671a87",
		// "2016-07-01T17:00:00.000+0000", "CLBA_CLASS_2");
		// db.saveLocation("rsaripa", "23b9ad0c-b206-4226-a372-302065671a87",
		// "40.512698, -88.996789");
		db.getUserRSVP("rsaripa");
	}

}
