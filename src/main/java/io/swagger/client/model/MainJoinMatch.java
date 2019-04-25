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
 * MainJoinMatch
 */

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.JavaClientCodegen", date = "2019-04-25T15:38:30.114990-03:00[America/Sao_Paulo]")public class MainJoinMatch {

  @SerializedName("luchadorID")
  private Integer luchadorID = null;

  @SerializedName("matchID")
  private Integer matchID = null;
  public MainJoinMatch luchadorID(Integer luchadorID) {
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
  public MainJoinMatch matchID(Integer matchID) {
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
    MainJoinMatch mainJoinMatch = (MainJoinMatch) o;
    return Objects.equals(this.luchadorID, mainJoinMatch.luchadorID) &&
        Objects.equals(this.matchID, mainJoinMatch.matchID);
  }

  @Override
  public int hashCode() {
    return java.util.Objects.hash(luchadorID, matchID);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class MainJoinMatch {\n");
    
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
