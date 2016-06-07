package edu.ilstu.business.era.models;

import java.util.Date;
import java.util.List;

import org.joda.time.DateTime;

/**
 * Model to represent an event
 * 
 * @author Basheer
 *
 */
// TODO: Add field for list of attachments
public class Event {
	private String eventId;
	private String title;
	private String description;
	private int points;
	private Location location;
	private DateTime startDate;
	private DateTime endDate;
	private List<User> attendees;

	public String getEventId() {
		return eventId;
	}

	public void setEventId(String eventId) {
		this.eventId = eventId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public DateTime getStartDate() {
		return startDate;
	}

	public void setStartDate(DateTime startDate) {
		this.startDate = startDate;
	}

	public DateTime getEndDate() {
		return endDate;
	}

	public void setEndDate(DateTime endDate) {
		this.endDate = endDate;
	}

	public List<User> getAttendees() {
		return attendees;
	}

	public void setAttendees(List<User> attendees) {
		this.attendees = attendees;
	}
}
