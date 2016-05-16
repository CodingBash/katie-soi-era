package edu.ilstu.business.era.models;

import java.util.Date;
import java.util.List;

/**
 * Model to represent an event
 * 
 * @author Basheer
 *
 */
public class Event {
	private User host;
	private String eventName;
	private String eventDescription;
	private String additionalInformation;
	private Location location;
	private Date date;
	private List<User> attendees;

	public User getHost() {
		return host;
	}

	public void setHost(User host) {
		this.host = host;
	}

	public String getEventName() {
		return eventName;
	}

	public void setEventName(String eventName) {
		this.eventName = eventName;
	}

	public String getEventDescription() {
		return eventDescription;
	}

	public void setEventDescription(String eventDescription) {
		this.eventDescription = eventDescription;
	}

	public String getAdditionalInformation() {
		return additionalInformation;
	}

	public void setAdditionalInformation(String additionalInformation) {
		this.additionalInformation = additionalInformation;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public List<User> getAttendees() {
		return attendees;
	}

	public void setAttendees(List<User> attendees) {
		this.attendees = attendees;
	}
}
