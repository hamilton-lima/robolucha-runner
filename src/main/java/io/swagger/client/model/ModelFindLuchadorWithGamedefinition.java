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
 * ModelFindLuchadorWithGamedefinition
 */

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.JavaClientCodegen", date = "2019-08-31T13:34:39.126Z[GMT]")public class ModelFindLuchadorWithGamedefinition {

  @SerializedName("gameDefinitionID")
  private Integer gameDefinitionID = null;

  @SerializedName("luchadorID")
  private Integer luchadorID = null;
  public ModelFindLuchadorWithGamedefinition gameDefinitionID(Integer gameDefinitionID) {
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
  public ModelFindLuchadorWithGamedefinition luchadorID(Integer luchadorID) {
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
  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ModelFindLuchadorWithGamedefinition modelFindLuchadorWithGamedefinition = (ModelFindLuchadorWithGamedefinition) o;
    return Objects.equals(this.gameDefinitionID, modelFindLuchadorWithGamedefinition.gameDefinitionID) &&
        Objects.equals(this.luchadorID, modelFindLuchadorWithGamedefinition.luchadorID);
  }

  @Override
  public int hashCode() {
    return java.util.Objects.hash(gameDefinitionID, luchadorID);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ModelFindLuchadorWithGamedefinition {\n");
    
    sb.append("    gameDefinitionID: ").append(toIndentedString(gameDefinitionID)).append("\n");
    sb.append("    luchadorID: ").append(toIndentedString(luchadorID)).append("\n");
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
