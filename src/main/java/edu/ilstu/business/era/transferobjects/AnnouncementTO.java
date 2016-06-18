
package edu.ilstu.business.era.transferobjects;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")
public class AnnouncementTO {

    @SerializedName("startDate")
    @Expose
    private String startDate;
    @SerializedName("calendarVisible")
    @Expose
    private Boolean calendarVisible;
    @SerializedName("deactive")
    @Expose
    private Boolean deactive;
    @SerializedName("classId")
    @Expose
    private String classId;
    @SerializedName("forAllInstructors")
    @Expose
    private Boolean forAllInstructors;
    @SerializedName("endDate")
    @Expose
    private String endDate;
    @SerializedName("canReply")
    @Expose
    private Boolean canReply;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("classAnnouncementId")
    @Expose
    private String classAnnouncementId;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("attachmentsPresent")
    @Expose
    private Boolean attachmentsPresent;
    @SerializedName("published")
    @Expose
    private Boolean published;

    /**
     * 
     * @return
     *     The startDate
     */
    public String getStartDate() {
        return startDate;
    }

    /**
     * 
     * @param startDate
     *     The startDate
     */
    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    /**
     * 
     * @return
     *     The calendarVisible
     */
    public Boolean getCalendarVisible() {
        return calendarVisible;
    }

    /**
     * 
     * @param calendarVisible
     *     The calendarVisible
     */
    public void setCalendarVisible(Boolean calendarVisible) {
        this.calendarVisible = calendarVisible;
    }

    /**
     * 
     * @return
     *     The deactive
     */
    public Boolean getDeactive() {
        return deactive;
    }

    /**
     * 
     * @param deactive
     *     The deactive
     */
    public void setDeactive(Boolean deactive) {
        this.deactive = deactive;
    }

    /**
     * 
     * @return
     *     The classId
     */
    public String getClassId() {
        return classId;
    }

    /**
     * 
     * @param classId
     *     The classId
     */
    public void setClassId(String classId) {
        this.classId = classId;
    }

    /**
     * 
     * @return
     *     The forAllInstructors
     */
    public Boolean getForAllInstructors() {
        return forAllInstructors;
    }

    /**
     * 
     * @param forAllInstructors
     *     The forAllInstructors
     */
    public void setForAllInstructors(Boolean forAllInstructors) {
        this.forAllInstructors = forAllInstructors;
    }

    /**
     * 
     * @return
     *     The endDate
     */
    public String getEndDate() {
        return endDate;
    }

    /**
     * 
     * @param endDate
     *     The endDate
     */
    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    /**
     * 
     * @return
     *     The canReply
     */
    public Boolean getCanReply() {
        return canReply;
    }

    /**
     * 
     * @param canReply
     *     The canReply
     */
    public void setCanReply(Boolean canReply) {
        this.canReply = canReply;
    }

    /**
     * 
     * @return
     *     The title
     */
    public String getTitle() {
        return title;
    }

    /**
     * 
     * @param title
     *     The title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * 
     * @return
     *     The classAnnouncementId
     */
    public String getClassAnnouncementId() {
        return classAnnouncementId;
    }

    /**
     * 
     * @param classAnnouncementId
     *     The classAnnouncementId
     */
    public void setClassAnnouncementId(String classAnnouncementId) {
        this.classAnnouncementId = classAnnouncementId;
    }

    /**
     * 
     * @return
     *     The description
     */
    public String getDescription() {
        return description;
    }

    /**
     * 
     * @param description
     *     The description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * 
     * @return
     *     The attachmentsPresent
     */
    public Boolean getAttachmentsPresent() {
        return attachmentsPresent;
    }

    /**
     * 
     * @param attachmentsPresent
     *     The attachmentsPresent
     */
    public void setAttachmentsPresent(Boolean attachmentsPresent) {
        this.attachmentsPresent = attachmentsPresent;
    }

    /**
     * 
     * @return
     *     The published
     */
    public Boolean getPublished() {
        return published;
    }

    /**
     * 
     * @param published
     *     The published
     */
    public void setPublished(Boolean published) {
        this.published = published;
    }

}
