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
 * ModelPlayRequest
 */

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.JavaClientCodegen", date = "2020-09-25T01:07:09.701808-04:00[America/Toronto]")public class ModelPlayRequest {

  @SerializedName("availableMatchID")
  private Integer availableMatchID = null;

  @SerializedName("teamID")
  private Integer teamID = null;
  public ModelPlayRequest availableMatchID(Integer availableMatchID) {
    this.availableMatchID = availableMatchID;
    return this;
  }

  

  /**
  * Get availableMatchID
  * @return availableMatchID
  **/
  @Schema(description = "")
  public Integer getAvailableMatchID() {
    return availableMatchID;
  }
  public void setAvailableMatchID(Integer availableMatchID) {
    this.availableMatchID = availableMatchID;
  }
  public ModelPlayRequest teamID(Integer teamID) {
    this.teamID = teamID;
    return this;
  }

  

  /**
  * Get teamID
  * @return teamID
  **/
  @Schema(description = "")
  public Integer getTeamID() {
    return teamID;
  }
  public void setTeamID(Integer teamID) {
    this.teamID = teamID;
  }
  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ModelPlayRequest modelPlayRequest = (ModelPlayRequest) o;
    return Objects.equals(this.availableMatchID, modelPlayRequest.availableMatchID) &&
        Objects.equals(this.teamID, modelPlayRequest.teamID);
  }

  @Override
  public int hashCode() {
    return java.util.Objects.hash(availableMatchID, teamID);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ModelPlayRequest {\n");
    
    sb.append("    availableMatchID: ").append(toIndentedString(availableMatchID)).append("\n");
    sb.append("    teamID: ").append(toIndentedString(teamID)).append("\n");
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
