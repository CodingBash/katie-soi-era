package edu.ilstu.business.era.constants;

/**
 * Application constants
 * 
 * @author Basheer Becerra (ULID: bbecer2)
 *
 */
public class ApplicationConstants {

	/*
	 * UI environment folder
	 */
	public static final String PAGE_FOLDER = "prod/";
	
	/*
	 * Loudcloud date format
	 */
	public static final String DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSSZZZZ";

	/*
	 * Loudcloud API REST URI's
	 */
	public static final String SEARCH_CLASS_LIST = "https://katieschoolclba.loudcloudsystems.com:443/learningPlatform/restservice/v1/class/search";
	public static final String GET_ALL_CLASS_ANNOUNCEMENTS = "https://katieschoolclba.loudcloudsystems.com:443/learningPlatform/restservice/v1/class/{refId}/announcement";
	public static final String GET_CLASS_ANNOUNCEMENT = "https://katieschoolclba.loudcloudsystems.com:443/learningPlatform/restservice/v1/class/{refId}/announcement/{announcementId}";
	public static final String RETRIEVE_USER = "https://katieschoolclba.loudcloudsystems.com:443/learningPlatform/restservice/v1/user/{refId}";


}
