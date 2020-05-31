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
 * ModelScoreList
 */

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.JavaClientCodegen", date = "2020-05-30T19:45:01.940-04:00[America/Toronto]")public class ModelScoreList {

  @SerializedName("scores")
  private List<ModelMatchScore> scores = null;
  public ModelScoreList scores(List<ModelMatchScore> scores) {
    this.scores = scores;
    return this;
  }

  public ModelScoreList addScoresItem(ModelMatchScore scoresItem) {
    if (this.scores == null) {
      this.scores = new ArrayList<ModelMatchScore>();
    }
    this.scores.add(scoresItem);
    return this;
  }

  /**
  * Get scores
  * @return scores
  **/
  @Schema(description = "")
  public List<ModelMatchScore> getScores() {
    return scores;
  }
  public void setScores(List<ModelMatchScore> scores) {
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
    ModelScoreList modelScoreList = (ModelScoreList) o;
    return Objects.equals(this.scores, modelScoreList.scores);
  }

  @Override
  public int hashCode() {
    return java.util.Objects.hash(scores);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ModelScoreList {\n");
    
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
