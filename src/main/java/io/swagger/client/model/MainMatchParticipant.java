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
 * MainMatchParticipant
 */

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.JavaClientCodegen", date = "2019-04-07T19:09:12.702Z[GMT]")public class MainMatchParticipant {

  @SerializedName("luchadorID")
  private Integer luchadorID = null;

  @SerializedName("matchID")
  private Integer matchID = null;
  public MainMatchParticipant luchadorID(Integer luchadorID) {
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
  public MainMatchParticipant matchID(Integer matchID) {
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
  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    MainMatchParticipant mainMatchParticipant = (MainMatchParticipant) o;
    return Objects.equals(this.luchadorID, mainMatchParticipant.luchadorID) &&
        Objects.equals(this.matchID, mainMatchParticipant.matchID);
  }

  @Override
  public int hashCode() {
    return java.util.Objects.hash(luchadorID, matchID);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class MainMatchParticipant {\n");
    
    sb.append("    luchadorID: ").append(toIndentedString(luchadorID)).append("\n");
    sb.append("    matchID: ").append(toIndentedString(matchID)).append("\n");
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
