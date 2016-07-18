package edu.ilstu.business.era.models;

import java.util.Map;

/**
 * Model that represents a user
 * 
 * @author Basheer
 *
 */
public class User {
	private String username;
	private String password;
	private String email;
	private Map<Event, Boolean> eventList;
	private Integer points;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Map<Event, Boolean> getEventList() {
		return eventList;
	}

	public void setEventList(Map<Event, Boolean> eventList) {
		this.eventList = eventList;
	}

	public Integer getPoints() {
		return points;
	}

	public void setPoints(Integer points) {
		this.points = points;
	}

}
