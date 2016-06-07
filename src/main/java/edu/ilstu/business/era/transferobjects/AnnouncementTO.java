package edu.ilstu.business.era.transferobjects;

import java.util.List;

import org.joda.time.DateTime;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * AnnouncementTO to unmarshall the REST JSON file
 * 
 * @author Basheer
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class AnnouncementTO {
	/**
	 * BU Announcement Description
	 */
	private String description;

	/**
	 * BU Announcement Title
	 */
	private String title;

	/**
	 * BU Announcement End Date
	 */
	private DateTime endDate;

	/**
	 * BU Announcement Start Date
	 */
	private DateTime startDate;

	/**
	 * BU Announcement Id
	 */
	private String buAnnouncementId;

	/**
	 * Is attachment present in BU Announcement?
	 */
	private boolean attachmentsPresent;

	/**
	 * BU Announcement is published or not
	 */
	private boolean published;

	/**
	 * BU Announcement activated or not
	 */
	private boolean deactivate;

	/**
	 * Can reply
	 */
	private boolean canReply;

	/**
	 * Is BU announcement visible in calendar
	 */
	private boolean calendarVisible;

	/**
	 * BU Announcement Codes
	 */
	private List<String> buCodes;

	/**
	 * BU Announcement titles
	 */
	private List<String> roleTitles;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public DateTime getEndDate() {
		return endDate;
	}

	public void setEndDate(DateTime endDate) {
		this.endDate = endDate;
	}

	public DateTime getStartDate() {
		return startDate;
	}

	public void setStartDate(DateTime startDate) {
		this.startDate = startDate;
	}

	public String getBuAnnouncementId() {
		return buAnnouncementId;
	}

	public void setBuAnnouncementId(String buAnnouncementId) {
		this.buAnnouncementId = buAnnouncementId;
	}

	public boolean isAttachmentsPresent() {
		return attachmentsPresent;
	}

	public void setAttachmentsPresent(boolean attachmentsPresent) {
		this.attachmentsPresent = attachmentsPresent;
	}

	public boolean isPublished() {
		return published;
	}

	public void setPublished(boolean published) {
		this.published = published;
	}

	public boolean isDeactivate() {
		return deactivate;
	}

	public void setDeactivate(boolean deactivate) {
		this.deactivate = deactivate;
	}

	public boolean isCanReply() {
		return canReply;
	}

	public void setCanReply(boolean canReply) {
		this.canReply = canReply;
	}

	public boolean isCalendarVisible() {
		return calendarVisible;
	}

	public void setCalendarVisible(boolean calendarVisible) {
		this.calendarVisible = calendarVisible;
	}

	public List<String> getBuCodes() {
		return buCodes;
	}

	public void setBuCodes(List<String> buCodes) {
		this.buCodes = buCodes;
	}

	public List<String> getRoleTitles() {
		return roleTitles;
	}

	public void setRoleTitles(List<String> roleTitles) {
		this.roleTitles = roleTitles;
	}

}
