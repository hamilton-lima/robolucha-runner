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
 * ModelActivity
 */

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.JavaClientCodegen", date = "2020-12-26T18:37:24.497003-05:00[America/Toronto]")public class ModelActivity {

  @SerializedName("description")
  private String description = null;

  @SerializedName("gameDefinition")
  private ModelGameDefinition gameDefinition = null;

  @SerializedName("gameDefinitionID")
  private Integer gameDefinitionID = null;

  @SerializedName("id")
  private Integer id = null;

  @SerializedName("name")
  private String name = null;

  @SerializedName("skills")
  private List<ModelSkill> skills = null;

  @SerializedName("sourceName")
  private String sourceName = null;

  @SerializedName("sourceURL")
  private String sourceURL = null;
  public ModelActivity description(String description) {
    this.description = description;
    return this;
  }

  

  /**
  * Get description
  * @return description
  **/
  @Schema(description = "")
  public String getDescription() {
    return description;
  }
  public void setDescription(String description) {
    this.description = description;
  }
  public ModelActivity gameDefinition(ModelGameDefinition gameDefinition) {
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
  public ModelActivity gameDefinitionID(Integer gameDefinitionID) {
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
  public ModelActivity id(Integer id) {
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
  public ModelActivity name(String name) {
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
  public ModelActivity skills(List<ModelSkill> skills) {
    this.skills = skills;
    return this;
  }

  public ModelActivity addSkillsItem(ModelSkill skillsItem) {
    if (this.skills == null) {
      this.skills = new ArrayList<ModelSkill>();
    }
    this.skills.add(skillsItem);
    return this;
  }

  /**
  * Get skills
  * @return skills
  **/
  @Schema(description = "")
  public List<ModelSkill> getSkills() {
    return skills;
  }
  public void setSkills(List<ModelSkill> skills) {
    this.skills = skills;
  }
  public ModelActivity sourceName(String sourceName) {
    this.sourceName = sourceName;
    return this;
  }

  

  /**
  * Get sourceName
  * @return sourceName
  **/
  @Schema(description = "")
  public String getSourceName() {
    return sourceName;
  }
  public void setSourceName(String sourceName) {
    this.sourceName = sourceName;
  }
  public ModelActivity sourceURL(String sourceURL) {
    this.sourceURL = sourceURL;
    return this;
  }

  

  /**
  * Get sourceURL
  * @return sourceURL
  **/
  @Schema(description = "")
  public String getSourceURL() {
    return sourceURL;
  }
  public void setSourceURL(String sourceURL) {
    this.sourceURL = sourceURL;
  }
  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ModelActivity modelActivity = (ModelActivity) o;
    return Objects.equals(this.description, modelActivity.description) &&
        Objects.equals(this.gameDefinition, modelActivity.gameDefinition) &&
        Objects.equals(this.gameDefinitionID, modelActivity.gameDefinitionID) &&
        Objects.equals(this.id, modelActivity.id) &&
        Objects.equals(this.name, modelActivity.name) &&
        Objects.equals(this.skills, modelActivity.skills) &&
        Objects.equals(this.sourceName, modelActivity.sourceName) &&
        Objects.equals(this.sourceURL, modelActivity.sourceURL);
  }

  @Override
  public int hashCode() {
    return java.util.Objects.hash(description, gameDefinition, gameDefinitionID, id, name, skills, sourceName, sourceURL);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ModelActivity {\n");
    
    sb.append("    description: ").append(toIndentedString(description)).append("\n");
    sb.append("    gameDefinition: ").append(toIndentedString(gameDefinition)).append("\n");
    sb.append("    gameDefinitionID: ").append(toIndentedString(gameDefinitionID)).append("\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    skills: ").append(toIndentedString(skills)).append("\n");
    sb.append("    sourceName: ").append(toIndentedString(sourceName)).append("\n");
    sb.append("    sourceURL: ").append(toIndentedString(sourceURL)).append("\n");
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
