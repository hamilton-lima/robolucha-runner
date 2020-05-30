/*
 * Robolucha API
 * Robolucha API
 *
 * OpenAPI spec version: 1.0
 * 
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 */

package io.swagger.client.model;

import com.google.gson.annotations.SerializedName;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.Objects;

/**
 * ModelActiveMatch
 */

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.JavaClientCodegen", date = "2020-05-30T16:45:46.153-04:00[America/Toronto]")public class ModelActiveMatch {

  @SerializedName("description")
  private String description = null;

  @SerializedName("duration")
  private Integer duration = null;

  @SerializedName("label")
  private String label = null;

  @SerializedName("matchID")
  private Integer matchID = null;

  @SerializedName("name")
  private String name = null;

  @SerializedName("sortOrder")
  private Integer sortOrder = null;

  @SerializedName("timeStart")
  private String timeStart = null;

  @SerializedName("type")
  private String type = null;
  public ModelActiveMatch description(String description) {
    this.description = description;
    return this;
  }

  

  /**
  * Get description
  * @return description
  **/
  @Schema(description = "")
  public String getDescription() {
    return description;
  }
  public void setDescription(String description) {
    this.description = description;
  }
  public ModelActiveMatch duration(Integer duration) {
    this.duration = duration;
    return this;
  }

  

  /**
  * Get duration
  * @return duration
  **/
  @Schema(description = "")
  public Integer getDuration() {
    return duration;
  }
  public void setDuration(Integer duration) {
    this.duration = duration;
  }
  public ModelActiveMatch label(String label) {
    this.label = label;
    return this;
  }

  

  /**
  * Get label
  * @return label
  **/
  @Schema(description = "")
  public String getLabel() {
    return label;
  }
  public void setLabel(String label) {
    this.label = label;
  }
  public ModelActiveMatch matchID(Integer matchID) {
    this.matchID = matchID;
    return this;
  }

  

  /**
  * Get matchID
  * @return matchID
  **/
  @Schema(description = "")
  public Integer getMatchID() {
    return matchID;
  }
  public void setMatchID(Integer matchID) {
    this.matchID = matchID;
  }
  public ModelActiveMatch name(String name) {
    this.name = name;
    return this;
  }

  

  /**
  * Get name
  * @return name
  **/
  @Schema(description = "")
  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }
  public ModelActiveMatch sortOrder(Integer sortOrder) {
    this.sortOrder = sortOrder;
    return this;
  }

  

  /**
  * Get sortOrder
  * @return sortOrder
  **/
  @Schema(description = "")
  public Integer getSortOrder() {
    return sortOrder;
  }
  public void setSortOrder(Integer sortOrder) {
    this.sortOrder = sortOrder;
  }
  public ModelActiveMatch timeStart(String timeStart) {
    this.timeStart = timeStart;
    return this;
  }

  

  /**
  * Get timeStart
  * @return timeStart
  **/
  @Schema(description = "")
  public String getTimeStart() {
    return timeStart;
  }
  public void setTimeStart(String timeStart) {
    this.timeStart = timeStart;
  }
  public ModelActiveMatch type(String type) {
    this.type = type;
    return this;
  }

  

  /**
  * Get type
  * @return type
  **/
  @Schema(description = "")
  public String getType() {
    return type;
  }
  public void setType(String type) {
    this.type = type;
  }
  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ModelActiveMatch modelActiveMatch = (ModelActiveMatch) o;
    return Objects.equals(this.description, modelActiveMatch.description) &&
        Objects.equals(this.duration, modelActiveMatch.duration) &&
        Objects.equals(this.label, modelActiveMatch.label) &&
        Objects.equals(this.matchID, modelActiveMatch.matchID) &&
        Objects.equals(this.name, modelActiveMatch.name) &&
        Objects.equals(this.sortOrder, modelActiveMatch.sortOrder) &&
        Objects.equals(this.timeStart, modelActiveMatch.timeStart) &&
        Objects.equals(this.type, modelActiveMatch.type);
  }

  @Override
  public int hashCode() {
    return java.util.Objects.hash(description, duration, label, matchID, name, sortOrder, timeStart, type);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ModelActiveMatch {\n");
    
    sb.append("    description: ").append(toIndentedString(description)).append("\n");
    sb.append("    duration: ").append(toIndentedString(duration)).append("\n");
    sb.append("    label: ").append(toIndentedString(label)).append("\n");
    sb.append("    matchID: ").append(toIndentedString(matchID)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    sortOrder: ").append(toIndentedString(sortOrder)).append("\n");
    sb.append("    timeStart: ").append(toIndentedString(timeStart)).append("\n");
    sb.append("    type: ").append(toIndentedString(type)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }

}
