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
 * ModelAvailableMatch
 */

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.JavaClientCodegen", date = "2020-12-26T18:37:24.497003-05:00[America/Toronto]")public class ModelAvailableMatch {

  @SerializedName("classroomID")
  private Integer classroomID = null;

  @SerializedName("gameDefinition")
  private ModelGameDefinition gameDefinition = null;

  @SerializedName("gameDefinitionID")
  private Integer gameDefinitionID = null;

  @SerializedName("id")
  private Integer id = null;

  @SerializedName("name")
  private String name = null;
  public ModelAvailableMatch classroomID(Integer classroomID) {
    this.classroomID = classroomID;
    return this;
  }

  

  /**
  * Get classroomID
  * @return classroomID
  **/
  @Schema(description = "")
  public Integer getClassroomID() {
    return classroomID;
  }
  public void setClassroomID(Integer classroomID) {
    this.classroomID = classroomID;
  }
  public ModelAvailableMatch gameDefinition(ModelGameDefinition gameDefinition) {
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
  public ModelAvailableMatch gameDefinitionID(Integer gameDefinitionID) {
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
  public ModelAvailableMatch id(Integer id) {
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
  public ModelAvailableMatch name(String name) {
    this.name = name;
    return this;
  }

  

  /**
  * Get name
  * @return name
  **/
  @Schema(description = "")
  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }
  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ModelAvailableMatch modelAvailableMatch = (ModelAvailableMatch) o;
    return Objects.equals(this.classroomID, modelAvailableMatch.classroomID) &&
        Objects.equals(this.gameDefinition, modelAvailableMatch.gameDefinition) &&
        Objects.equals(this.gameDefinitionID, modelAvailableMatch.gameDefinitionID) &&
        Objects.equals(this.id, modelAvailableMatch.id) &&
        Objects.equals(this.name, modelAvailableMatch.name);
  }

  @Override
  public int hashCode() {
    return java.util.Objects.hash(classroomID, gameDefinition, gameDefinitionID, id, name);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ModelAvailableMatch {\n");
    
    sb.append("    classroomID: ").append(toIndentedString(classroomID)).append("\n");
    sb.append("    gameDefinition: ").append(toIndentedString(gameDefinition)).append("\n");
    sb.append("    gameDefinitionID: ").append(toIndentedString(gameDefinitionID)).append("\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
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
