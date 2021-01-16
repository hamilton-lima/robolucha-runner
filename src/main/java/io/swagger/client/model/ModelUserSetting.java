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
 * ModelUserSetting
 */

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.JavaClientCodegen", date = "2020-12-26T18:37:24.497003-05:00[America/Toronto]")public class ModelUserSetting {

  @SerializedName("id")
  private Integer id = null;

  @SerializedName("playedTutorial")
  private Boolean playedTutorial = null;

  @SerializedName("userID")
  private Integer userID = null;

  @SerializedName("visitedMainPage")
  private Boolean visitedMainPage = null;

  @SerializedName("visitedMaskPage")
  private Boolean visitedMaskPage = null;
  public ModelUserSetting id(Integer id) {
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
  public ModelUserSetting playedTutorial(Boolean playedTutorial) {
    this.playedTutorial = playedTutorial;
    return this;
  }

  

  /**
  * Get playedTutorial
  * @return playedTutorial
  **/
  @Schema(description = "")
  public Boolean isPlayedTutorial() {
    return playedTutorial;
  }
  public void setPlayedTutorial(Boolean playedTutorial) {
    this.playedTutorial = playedTutorial;
  }
  public ModelUserSetting userID(Integer userID) {
    this.userID = userID;
    return this;
  }

  

  /**
  * Get userID
  * @return userID
  **/
  @Schema(description = "")
  public Integer getUserID() {
    return userID;
  }
  public void setUserID(Integer userID) {
    this.userID = userID;
  }
  public ModelUserSetting visitedMainPage(Boolean visitedMainPage) {
    this.visitedMainPage = visitedMainPage;
    return this;
  }

  

  /**
  * Get visitedMainPage
  * @return visitedMainPage
  **/
  @Schema(description = "")
  public Boolean isVisitedMainPage() {
    return visitedMainPage;
  }
  public void setVisitedMainPage(Boolean visitedMainPage) {
    this.visitedMainPage = visitedMainPage;
  }
  public ModelUserSetting visitedMaskPage(Boolean visitedMaskPage) {
    this.visitedMaskPage = visitedMaskPage;
    return this;
  }

  

  /**
  * Get visitedMaskPage
  * @return visitedMaskPage
  **/
  @Schema(description = "")
  public Boolean isVisitedMaskPage() {
    return visitedMaskPage;
  }
  public void setVisitedMaskPage(Boolean visitedMaskPage) {
    this.visitedMaskPage = visitedMaskPage;
  }
  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ModelUserSetting modelUserSetting = (ModelUserSetting) o;
    return Objects.equals(this.id, modelUserSetting.id) &&
        Objects.equals(this.playedTutorial, modelUserSetting.playedTutorial) &&
        Objects.equals(this.userID, modelUserSetting.userID) &&
        Objects.equals(this.visitedMainPage, modelUserSetting.visitedMainPage) &&
        Objects.equals(this.visitedMaskPage, modelUserSetting.visitedMaskPage);
  }

  @Override
  public int hashCode() {
    return java.util.Objects.hash(id, playedTutorial, userID, visitedMainPage, visitedMaskPage);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ModelUserSetting {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    playedTutorial: ").append(toIndentedString(playedTutorial)).append("\n");
    sb.append("    userID: ").append(toIndentedString(userID)).append("\n");
    sb.append("    visitedMainPage: ").append(toIndentedString(visitedMainPage)).append("\n");
    sb.append("    visitedMaskPage: ").append(toIndentedString(visitedMaskPage)).append("\n");
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
