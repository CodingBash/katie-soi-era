package edu.ilstu.business.era.models;

import java.util.Date;

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
	private String classId;
	private String title;
	private int points;
	private String description;
	private Date startDate;
	private Date endDate;

	public String getEventId() {
		return eventId;
	}

	public void setEventId(String eventId) {
		this.eventId = eventId;
	}

	public String getClassId() {
		return classId;
	}

	public void setClassId(String classId) {
		this.classId = classId;
	}
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(DateTime startDate) {
		this.startDate = startDate.toDate();
	}

	public Date getEndDate() {
		return endDate; 
	}

	public void setEndDate(DateTime endDate) {
		this.endDate = endDate.toDate();
	}

}
