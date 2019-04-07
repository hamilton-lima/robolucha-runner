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
 * MainMatch
 */

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.JavaClientCodegen", date = "2019-04-07T21:57:22.207Z[GMT]")public class MainMatch {

  @SerializedName("arenaHeight")
  private Integer arenaHeight = null;

  @SerializedName("arenaWidth")
  private Integer arenaWidth = null;

  @SerializedName("buletSpeed")
  private Integer buletSpeed = null;

  @SerializedName("bulletSize")
  private Integer bulletSize = null;

  @SerializedName("createdAt")
  private String createdAt = null;

  @SerializedName("deletedAt")
  private String deletedAt = null;

  @SerializedName("duration")
  private Integer duration = null;

  @SerializedName("fps")
  private Integer fps = null;

  @SerializedName("id")
  private Integer id = null;

  @SerializedName("lastTimeAlive")
  private String lastTimeAlive = null;

  @SerializedName("luchadorSize")
  private Integer luchadorSize = null;

  @SerializedName("maxParticipants")
  private Integer maxParticipants = null;

  @SerializedName("minParticipants")
  private Integer minParticipants = null;

  @SerializedName("participants")
  private List<MainLuchador> participants = null;

  @SerializedName("timeEnd")
  private String timeEnd = null;

  @SerializedName("timeStart")
  private String timeStart = null;

  @SerializedName("updatedAt")
  private String updatedAt = null;
  public MainMatch arenaHeight(Integer arenaHeight) {
    this.arenaHeight = arenaHeight;
    return this;
  }

  

  /**
  * Get arenaHeight
  * @return arenaHeight
  **/
  @Schema(description = "")
  public Integer getArenaHeight() {
    return arenaHeight;
  }
  public void setArenaHeight(Integer arenaHeight) {
    this.arenaHeight = arenaHeight;
  }
  public MainMatch arenaWidth(Integer arenaWidth) {
    this.arenaWidth = arenaWidth;
    return this;
  }

  

  /**
  * Get arenaWidth
  * @return arenaWidth
  **/
  @Schema(description = "")
  public Integer getArenaWidth() {
    return arenaWidth;
  }
  public void setArenaWidth(Integer arenaWidth) {
    this.arenaWidth = arenaWidth;
  }
  public MainMatch buletSpeed(Integer buletSpeed) {
    this.buletSpeed = buletSpeed;
    return this;
  }

  

  /**
  * Get buletSpeed
  * @return buletSpeed
  **/
  @Schema(description = "")
  public Integer getBuletSpeed() {
    return buletSpeed;
  }
  public void setBuletSpeed(Integer buletSpeed) {
    this.buletSpeed = buletSpeed;
  }
  public MainMatch bulletSize(Integer bulletSize) {
    this.bulletSize = bulletSize;
    return this;
  }

  

  /**
  * Get bulletSize
  * @return bulletSize
  **/
  @Schema(description = "")
  public Integer getBulletSize() {
    return bulletSize;
  }
  public void setBulletSize(Integer bulletSize) {
    this.bulletSize = bulletSize;
  }
  public MainMatch createdAt(String createdAt) {
    this.createdAt = createdAt;
    return this;
  }

  

  /**
  * Get createdAt
  * @return createdAt
  **/
  @Schema(description = "")
  public String getCreatedAt() {
    return createdAt;
  }
  public void setCreatedAt(String createdAt) {
    this.createdAt = createdAt;
  }
  public MainMatch deletedAt(String deletedAt) {
    this.deletedAt = deletedAt;
    return this;
  }

  

  /**
  * Get deletedAt
  * @return deletedAt
  **/
  @Schema(description = "")
  public String getDeletedAt() {
    return deletedAt;
  }
  public void setDeletedAt(String deletedAt) {
    this.deletedAt = deletedAt;
  }
  public MainMatch duration(Integer duration) {
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
  public MainMatch fps(Integer fps) {
    this.fps = fps;
    return this;
  }

  

  /**
  * Get fps
  * @return fps
  **/
  @Schema(description = "")
  public Integer getFps() {
    return fps;
  }
  public void setFps(Integer fps) {
    this.fps = fps;
  }
  public MainMatch id(Integer id) {
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
  public MainMatch lastTimeAlive(String lastTimeAlive) {
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
  public MainMatch luchadorSize(Integer luchadorSize) {
    this.luchadorSize = luchadorSize;
    return this;
  }

  

  /**
  * Get luchadorSize
  * @return luchadorSize
  **/
  @Schema(description = "")
  public Integer getLuchadorSize() {
    return luchadorSize;
  }
  public void setLuchadorSize(Integer luchadorSize) {
    this.luchadorSize = luchadorSize;
  }
  public MainMatch maxParticipants(Integer maxParticipants) {
    this.maxParticipants = maxParticipants;
    return this;
  }

  

  /**
  * Get maxParticipants
  * @return maxParticipants
  **/
  @Schema(description = "")
  public Integer getMaxParticipants() {
    return maxParticipants;
  }
  public void setMaxParticipants(Integer maxParticipants) {
    this.maxParticipants = maxParticipants;
  }
  public MainMatch minParticipants(Integer minParticipants) {
    this.minParticipants = minParticipants;
    return this;
  }

  

  /**
  * Get minParticipants
  * @return minParticipants
  **/
  @Schema(description = "")
  public Integer getMinParticipants() {
    return minParticipants;
  }
  public void setMinParticipants(Integer minParticipants) {
    this.minParticipants = minParticipants;
  }
  public MainMatch participants(List<MainLuchador> participants) {
    this.participants = participants;
    return this;
  }

  public MainMatch addParticipantsItem(MainLuchador participantsItem) {
    if (this.participants == null) {
      this.participants = new ArrayList<MainLuchador>();
    }
    this.participants.add(participantsItem);
    return this;
  }

  /**
  * Get participants
  * @return participants
  **/
  @Schema(description = "")
  public List<MainLuchador> getParticipants() {
    return participants;
  }
  public void setParticipants(List<MainLuchador> participants) {
    this.participants = participants;
  }
  public MainMatch timeEnd(String timeEnd) {
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
  public MainMatch timeStart(String timeStart) {
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
  public MainMatch updatedAt(String updatedAt) {
    this.updatedAt = updatedAt;
    return this;
  }

  

  /**
  * Get updatedAt
  * @return updatedAt
  **/
  @Schema(description = "")
  public String getUpdatedAt() {
    return updatedAt;
  }
  public void setUpdatedAt(String updatedAt) {
    this.updatedAt = updatedAt;
  }
  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    MainMatch mainMatch = (MainMatch) o;
    return Objects.equals(this.arenaHeight, mainMatch.arenaHeight) &&
        Objects.equals(this.arenaWidth, mainMatch.arenaWidth) &&
        Objects.equals(this.buletSpeed, mainMatch.buletSpeed) &&
        Objects.equals(this.bulletSize, mainMatch.bulletSize) &&
        Objects.equals(this.createdAt, mainMatch.createdAt) &&
        Objects.equals(this.deletedAt, mainMatch.deletedAt) &&
        Objects.equals(this.duration, mainMatch.duration) &&
        Objects.equals(this.fps, mainMatch.fps) &&
        Objects.equals(this.id, mainMatch.id) &&
        Objects.equals(this.lastTimeAlive, mainMatch.lastTimeAlive) &&
        Objects.equals(this.luchadorSize, mainMatch.luchadorSize) &&
        Objects.equals(this.maxParticipants, mainMatch.maxParticipants) &&
        Objects.equals(this.minParticipants, mainMatch.minParticipants) &&
        Objects.equals(this.participants, mainMatch.participants) &&
        Objects.equals(this.timeEnd, mainMatch.timeEnd) &&
        Objects.equals(this.timeStart, mainMatch.timeStart) &&
        Objects.equals(this.updatedAt, mainMatch.updatedAt);
  }

  @Override
  public int hashCode() {
    return java.util.Objects.hash(arenaHeight, arenaWidth, buletSpeed, bulletSize, createdAt, deletedAt, duration, fps, id, lastTimeAlive, luchadorSize, maxParticipants, minParticipants, participants, timeEnd, timeStart, updatedAt);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class MainMatch {\n");
    
    sb.append("    arenaHeight: ").append(toIndentedString(arenaHeight)).append("\n");
    sb.append("    arenaWidth: ").append(toIndentedString(arenaWidth)).append("\n");
    sb.append("    buletSpeed: ").append(toIndentedString(buletSpeed)).append("\n");
    sb.append("    bulletSize: ").append(toIndentedString(bulletSize)).append("\n");
    sb.append("    createdAt: ").append(toIndentedString(createdAt)).append("\n");
    sb.append("    deletedAt: ").append(toIndentedString(deletedAt)).append("\n");
    sb.append("    duration: ").append(toIndentedString(duration)).append("\n");
    sb.append("    fps: ").append(toIndentedString(fps)).append("\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    lastTimeAlive: ").append(toIndentedString(lastTimeAlive)).append("\n");
    sb.append("    luchadorSize: ").append(toIndentedString(luchadorSize)).append("\n");
    sb.append("    maxParticipants: ").append(toIndentedString(maxParticipants)).append("\n");
    sb.append("    minParticipants: ").append(toIndentedString(minParticipants)).append("\n");
    sb.append("    participants: ").append(toIndentedString(participants)).append("\n");
    sb.append("    timeEnd: ").append(toIndentedString(timeEnd)).append("\n");
    sb.append("    timeStart: ").append(toIndentedString(timeStart)).append("\n");
    sb.append("    updatedAt: ").append(toIndentedString(updatedAt)).append("\n");
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
