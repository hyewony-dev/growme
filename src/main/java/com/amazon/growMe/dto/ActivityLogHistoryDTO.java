
package com.amazon.growMe.dto;

import java.util.LinkedHashMap;
import java.util.Map;
import javax.annotation.processing.Generated;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "activityName",
    "earnedPoints",
    "note",
    "loggedAt"
})
@Generated("jsonschema2pojo")
public class ActivityLogHistoryDTO {

    @JsonProperty("activityName")
    private String activityName;
    @JsonProperty("earnedPoints")
    private Integer earnedPoints;
    @JsonProperty("note")
    private String note;
    @JsonProperty("loggedAt")
    private String loggedAt;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("activityName")
    public String getActivityName() {
        return activityName;
    }

    @JsonProperty("activityName")
    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public ActivityLogHistoryDTO withActivityName(String activityName) {
        this.activityName = activityName;
        return this;
    }

    @JsonProperty("earnedPoints")
    public Integer getEarnedPoints() {
        return earnedPoints;
    }

    @JsonProperty("earnedPoints")
    public void setEarnedPoints(Integer earnedPoints) {
        this.earnedPoints = earnedPoints;
    }

    public ActivityLogHistoryDTO withEarnedPoints(Integer earnedPoints) {
        this.earnedPoints = earnedPoints;
        return this;
    }

    @JsonProperty("note")
    public String getNote() {
        return note;
    }

    @JsonProperty("note")
    public void setNote(String note) {
        this.note = note;
    }

    public ActivityLogHistoryDTO withNote(String note) {
        this.note = note;
        return this;
    }

    @JsonProperty("loggedAt")
    public String getLoggedAt() {
        return loggedAt;
    }

    @JsonProperty("loggedAt")
    public void setLoggedAt(String loggedAt) {
        this.loggedAt = loggedAt;
    }

    public ActivityLogHistoryDTO withLoggedAt(String loggedAt) {
        this.loggedAt = loggedAt;
        return this;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    public ActivityLogHistoryDTO withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(ActivityLogHistoryDTO.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("activityName");
        sb.append('=');
        sb.append(((this.activityName == null)?"<null>":this.activityName));
        sb.append(',');
        sb.append("earnedPoints");
        sb.append('=');
        sb.append(((this.earnedPoints == null)?"<null>":this.earnedPoints));
        sb.append(',');
        sb.append("note");
        sb.append('=');
        sb.append(((this.note == null)?"<null>":this.note));
        sb.append(',');
        sb.append("loggedAt");
        sb.append('=');
        sb.append(((this.loggedAt == null)?"<null>":this.loggedAt));
        sb.append(',');
        sb.append("additionalProperties");
        sb.append('=');
        sb.append(((this.additionalProperties == null)?"<null>":this.additionalProperties));
        sb.append(',');
        if (sb.charAt((sb.length()- 1)) == ',') {
            sb.setCharAt((sb.length()- 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }

}
