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
 * ModelLevelGroup
 */

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.JavaClientCodegen", date = "2020-12-26T18:37:24.497003-05:00[America/Toronto]")public class ModelLevelGroup {

  @SerializedName("description")
  private String description = null;

  @SerializedName("id")
  private Integer id = null;

  @SerializedName("minLevel")
  private Integer minLevel = null;

  @SerializedName("name")
  private String name = null;
  public ModelLevelGroup description(String description) {
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
  public ModelLevelGroup id(Integer id) {
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
  public ModelLevelGroup minLevel(Integer minLevel) {
    this.minLevel = minLevel;
    return this;
  }

  

  /**
  * Get minLevel
  * @return minLevel
  **/
  @Schema(description = "")
  public Integer getMinLevel() {
    return minLevel;
  }
  public void setMinLevel(Integer minLevel) {
    this.minLevel = minLevel;
  }
  public ModelLevelGroup name(String name) {
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
    ModelLevelGroup modelLevelGroup = (ModelLevelGroup) o;
    return Objects.equals(this.description, modelLevelGroup.description) &&
        Objects.equals(this.id, modelLevelGroup.id) &&
        Objects.equals(this.minLevel, modelLevelGroup.minLevel) &&
        Objects.equals(this.name, modelLevelGroup.name);
  }

  @Override
  public int hashCode() {
    return java.util.Objects.hash(description, id, minLevel, name);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ModelLevelGroup {\n");
    
    sb.append("    description: ").append(toIndentedString(description)).append("\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    minLevel: ").append(toIndentedString(minLevel)).append("\n");
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
