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
 * ModelMatchScore
 */

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.JavaClientCodegen", date = "2020-12-26T18:37:24.497003-05:00[America/Toronto]")public class ModelMatchScore {

  @SerializedName("deaths")
  private Integer deaths = null;

  @SerializedName("id")
  private Integer id = null;

  @SerializedName("kills")
  private Integer kills = null;

  @SerializedName("luchadorID")
  private Integer luchadorID = null;

  @SerializedName("matchID")
  private Integer matchID = null;

  @SerializedName("score")
  private Integer score = null;
  public ModelMatchScore deaths(Integer deaths) {
    this.deaths = deaths;
    return this;
  }

  

  /**
  * Get deaths
  * @return deaths
  **/
  @Schema(description = "")
  public Integer getDeaths() {
    return deaths;
  }
  public void setDeaths(Integer deaths) {
    this.deaths = deaths;
  }
  public ModelMatchScore id(Integer id) {
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
  public ModelMatchScore kills(Integer kills) {
    this.kills = kills;
    return this;
  }

  

  /**
  * Get kills
  * @return kills
  **/
  @Schema(description = "")
  public Integer getKills() {
    return kills;
  }
  public void setKills(Integer kills) {
    this.kills = kills;
  }
  public ModelMatchScore luchadorID(Integer luchadorID) {
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
  public ModelMatchScore matchID(Integer matchID) {
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
  public ModelMatchScore score(Integer score) {
    this.score = score;
    return this;
  }

  

  /**
  * Get score
  * @return score
  **/
  @Schema(description = "")
  public Integer getScore() {
    return score;
  }
  public void setScore(Integer score) {
    this.score = score;
  }
  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ModelMatchScore modelMatchScore = (ModelMatchScore) o;
    return Objects.equals(this.deaths, modelMatchScore.deaths) &&
        Objects.equals(this.id, modelMatchScore.id) &&
        Objects.equals(this.kills, modelMatchScore.kills) &&
        Objects.equals(this.luchadorID, modelMatchScore.luchadorID) &&
        Objects.equals(this.matchID, modelMatchScore.matchID) &&
        Objects.equals(this.score, modelMatchScore.score);
  }

  @Override
  public int hashCode() {
    return java.util.Objects.hash(deaths, id, kills, luchadorID, matchID, score);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ModelMatchScore {\n");
    
    sb.append("    deaths: ").append(toIndentedString(deaths)).append("\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    kills: ").append(toIndentedString(kills)).append("\n");
    sb.append("    luchadorID: ").append(toIndentedString(luchadorID)).append("\n");
    sb.append("    matchID: ").append(toIndentedString(matchID)).append("\n");
    sb.append("    score: ").append(toIndentedString(score)).append("\n");
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
