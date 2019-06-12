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
 * MainScoreList
 */

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.JavaClientCodegen", date = "2019-06-11T04:58:57.195Z[GMT]")public class MainScoreList {

  @SerializedName("scores")
  private List<MainMatchScore> scores = null;
  public MainScoreList scores(List<MainMatchScore> scores) {
    this.scores = scores;
    return this;
  }

  public MainScoreList addScoresItem(MainMatchScore scoresItem) {
    if (this.scores == null) {
      this.scores = new ArrayList<MainMatchScore>();
    }
    this.scores.add(scoresItem);
    return this;
  }

  /**
  * Get scores
  * @return scores
  **/
  @Schema(description = "")
  public List<MainMatchScore> getScores() {
    return scores;
  }
  public void setScores(List<MainMatchScore> scores) {
    this.scores = scores;
  }
  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    MainScoreList mainScoreList = (MainScoreList) o;
    return Objects.equals(this.scores, mainScoreList.scores);
  }

  @Override
  public int hashCode() {
    return java.util.Objects.hash(scores);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class MainScoreList {\n");
    
    sb.append("    scores: ").append(toIndentedString(scores)).append("\n");
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
