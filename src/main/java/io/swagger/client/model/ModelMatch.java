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
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * ModelMatch
 */

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.JavaClientCodegen", date = "2020-05-30T16:45:46.153-04:00[America/Toronto]")public class ModelMatch {

  @SerializedName("availableMatchID")
  private Integer availableMatchID = null;

  @SerializedName("gameDefinition")
  private ModelGameDefinition gameDefinition = null;

  @SerializedName("gameDefinitionID")
  private Integer gameDefinitionID = null;

  @SerializedName("id")
  private Integer id = null;

  @SerializedName("lastTimeAlive")
  private String lastTimeAlive = null;

  @SerializedName("participants")
  private List<ModelGameComponent> participants = null;

  @SerializedName("timeEnd")
  private String timeEnd = null;

  @SerializedName("timeStart")
  private String timeStart = null;
  public ModelMatch availableMatchID(Integer availableMatchID) {
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
  public ModelMatch gameDefinition(ModelGameDefinition gameDefinition) {
    this.gameDefinition = gameDefinition;
    return this;
  }

  

  /**
  * Get gameDefinition
  * @return gameDefinition
  **/
  @Schema(description = "")
  public ModelGameDefinition getGameDefinition() {
    return gameDefinition;
  }
  public void setGameDefinition(ModelGameDefinition gameDefinition) {
    this.gameDefinition = gameDefinition;
  }
  public ModelMatch gameDefinitionID(Integer gameDefinitionID) {
    this.gameDefinitionID = gameDefinitionID;
    return this;
  }

  

  /**
  * Get gameDefinitionID
  * @return gameDefinitionID
  **/
  @Schema(description = "")
  public Integer getGameDefinitionID() {
    return gameDefinitionID;
  }
  public void setGameDefinitionID(Integer gameDefinitionID) {
    this.gameDefinitionID = gameDefinitionID;
  }
  public ModelMatch id(Integer id) {
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
  public ModelMatch lastTimeAlive(String lastTimeAlive) {
    this.lastTimeAlive = lastTimeAlive;
    return this;
  }

  

  /**
  * Get lastTimeAlive
  * @return lastTimeAlive
  **/
  @Schema(description = "")
  public String getLastTimeAlive() {
    return lastTimeAlive;
  }
  public void setLastTimeAlive(String lastTimeAlive) {
    this.lastTimeAlive = lastTimeAlive;
  }
  public ModelMatch participants(List<ModelGameComponent> participants) {
    this.participants = participants;
    return this;
  }

  public ModelMatch addParticipantsItem(ModelGameComponent participantsItem) {
    if (this.participants == null) {
      this.participants = new ArrayList<ModelGameComponent>();
    }
    this.participants.add(participantsItem);
    return this;
  }

  /**
  * Get participants
  * @return participants
  **/
  @Schema(description = "")
  public List<ModelGameComponent> getParticipants() {
    return participants;
  }
  public void setParticipants(List<ModelGameComponent> participants) {
    this.participants = participants;
  }
  public ModelMatch timeEnd(String timeEnd) {
    this.timeEnd = timeEnd;
    return this;
  }

  

  /**
  * Get timeEnd
  * @return timeEnd
  **/
  @Schema(description = "")
  public String getTimeEnd() {
    return timeEnd;
  }
  public void setTimeEnd(String timeEnd) {
    this.timeEnd = timeEnd;
  }
  public ModelMatch timeStart(String timeStart) {
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
  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ModelMatch modelMatch = (ModelMatch) o;
    return Objects.equals(this.availableMatchID, modelMatch.availableMatchID) &&
        Objects.equals(this.gameDefinition, modelMatch.gameDefinition) &&
        Objects.equals(this.gameDefinitionID, modelMatch.gameDefinitionID) &&
        Objects.equals(this.id, modelMatch.id) &&
        Objects.equals(this.lastTimeAlive, modelMatch.lastTimeAlive) &&
        Objects.equals(this.participants, modelMatch.participants) &&
        Objects.equals(this.timeEnd, modelMatch.timeEnd) &&
        Objects.equals(this.timeStart, modelMatch.timeStart);
  }

  @Override
  public int hashCode() {
    return java.util.Objects.hash(availableMatchID, gameDefinition, gameDefinitionID, id, lastTimeAlive, participants, timeEnd, timeStart);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ModelMatch {\n");
    
    sb.append("    availableMatchID: ").append(toIndentedString(availableMatchID)).append("\n");
    sb.append("    gameDefinition: ").append(toIndentedString(gameDefinition)).append("\n");
    sb.append("    gameDefinitionID: ").append(toIndentedString(gameDefinitionID)).append("\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    lastTimeAlive: ").append(toIndentedString(lastTimeAlive)).append("\n");
    sb.append("    participants: ").append(toIndentedString(participants)).append("\n");
    sb.append("    timeEnd: ").append(toIndentedString(timeEnd)).append("\n");
    sb.append("    timeStart: ").append(toIndentedString(timeStart)).append("\n");
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
