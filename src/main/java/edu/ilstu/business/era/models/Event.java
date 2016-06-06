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
public class Event {
	private long id;
	private User host;
	private String title;
	private String description;
	private String additionalInformation;
	private int points;
	private Location location;
	private DateTime date;
	private List<User> attendees;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public User getHost() {
		return host;
	}

	public void setHost(User host) {
		this.host = host;
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

	public String getAdditionalInformation() {
		return additionalInformation;
	}

	public void setAdditionalInformation(String additionalInformation) {
		this.additionalInformation = additionalInformation;
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

	public DateTime getDate() {
		return date;
	}

	public void setDate(DateTime date) {
		this.date = date;
	}

	public List<User> getAttendees() {
		return attendees;
	}

	public void setAttendees(List<User> attendees) {
		this.attendees = attendees;
	}
}
