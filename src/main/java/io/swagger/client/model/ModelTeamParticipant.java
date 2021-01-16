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
 * ModelTeamParticipant
 */

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.JavaClientCodegen", date = "2020-12-26T18:37:24.497003-05:00[America/Toronto]")public class ModelTeamParticipant {

  @SerializedName("id")
  private Integer id = null;

  @SerializedName("luchadorID")
  private Integer luchadorID = null;

  @SerializedName("teamID")
  private Integer teamID = null;
  public ModelTeamParticipant id(Integer id) {
    this.id = id;
    return this;
  }

  

  /**
  * Get id
  * @return id
  **/
  @Schema(description = "")
  public Integer getId() {
    return id;
  }
  public void setId(Integer id) {
    this.id = id;
  }
  public ModelTeamParticipant luchadorID(Integer luchadorID) {
    this.luchadorID = luchadorID;
    return this;
  }

  

  /**
  * Get luchadorID
  * @return luchadorID
  **/
  @Schema(description = "")
  public Integer getLuchadorID() {
    return luchadorID;
  }
  public void setLuchadorID(Integer luchadorID) {
    this.luchadorID = luchadorID;
  }
  public ModelTeamParticipant teamID(Integer teamID) {
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
    ModelTeamParticipant modelTeamParticipant = (ModelTeamParticipant) o;
    return Objects.equals(this.id, modelTeamParticipant.id) &&
        Objects.equals(this.luchadorID, modelTeamParticipant.luchadorID) &&
        Objects.equals(this.teamID, modelTeamParticipant.teamID);
  }

  @Override
  public int hashCode() {
    return java.util.Objects.hash(id, luchadorID, teamID);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ModelTeamParticipant {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    luchadorID: ").append(toIndentedString(luchadorID)).append("\n");
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