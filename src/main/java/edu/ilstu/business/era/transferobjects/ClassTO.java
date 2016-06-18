package edu.ilstu.business.era.transferobjects;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ClassTO {

	@JsonProperty("sectionRefId")
	private String sectionRefId;

	public String getSectionRefId() {
		return sectionRefId;
	}

	public void setSectionRefId(String sectionRefId) {
		this.sectionRefId = sectionRefId;
	}

}
