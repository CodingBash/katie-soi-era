
package edu.ilstu.business.era.transferobjects;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")
public class UserTO {

	@SerializedName("password")
	@Expose
	private String password;
	@SerializedName("userName")
	@Expose
	private String userName;
	@SerializedName("userId")
	@Expose
	private String userId;
	@SerializedName("refId")
	@Expose
	private String refId;
	@SerializedName("timezoneKey")
	@Expose
	private String timezoneKey;
	@SerializedName("firstName")
	@Expose
	private String firstName;
	@SerializedName("lastName")
	@Expose
	private String lastName;
	@SerializedName("emailAddress")
	@Expose
	private String emailAddress;
	@SerializedName("buCodes")
	@Expose
	private List<String> buCodes = new ArrayList<String>();
	@SerializedName("assignedRoles")
	@Expose
	private List<String> assignedRoles = new ArrayList<String>();

	/**
	 * 
	 * @return The password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * 
	 * @param password
	 *            The password
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * 
	 * @return The userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * 
	 * @param userName
	 *            The userName
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * 
	 * @return The userId
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * 
	 * @param userId
	 *            The userId
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * 
	 * @return The refId
	 */
	public String getRefId() {
		return refId;
	}

	/**
	 * 
	 * @param refId
	 *            The refId
	 */
	public void setRefId(String refId) {
		this.refId = refId;
	}

	/**
	 * 
	 * @return The timezoneKey
	 */
	public String getTimezoneKey() {
		return timezoneKey;
	}

	/**
	 * 
	 * @param timezoneKey
	 *            The timezoneKey
	 */
	public void setTimezoneKey(String timezoneKey) {
		this.timezoneKey = timezoneKey;
	}

	/**
	 * 
	 * @return The firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * 
	 * @param firstName
	 *            The firstName
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * 
	 * @return The lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * 
	 * @param lastName
	 *            The lastName
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * 
	 * @return The emailAddress
	 */
	public String getEmailAddress() {
		return emailAddress;
	}

	/**
	 * 
	 * @param emailAddress
	 *            The emailAddress
	 */
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	/**
	 * 
	 * @return The buCodes
	 */
	public List<String> getBuCodes() {
		return buCodes;
	}

	/**
	 * 
	 * @param buCodes
	 *            The buCodes
	 */
	public void setBuCodes(List<String> buCodes) {
		this.buCodes = buCodes;
	}

	/**
	 * 
	 * @return The assignedRoles
	 */
	public List<String> getAssignedRoles() {
		return assignedRoles;
	}

	/**
	 * 
	 * @param assignedRoles
	 *            The assignedRoles
	 */
	public void setAssignedRoles(List<String> assignedRoles) {
		this.assignedRoles = assignedRoles;
	}

}
