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
 * ModelUserDetails
 */

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.JavaClientCodegen", date = "2020-12-26T18:37:24.497003-05:00[America/Toronto]")public class ModelUserDetails {

  @SerializedName("classrooms")
  private List<ModelClassroom> classrooms = null;

  @SerializedName("level")
  private ModelUserLevel level = null;

  @SerializedName("roles")
  private List<String> roles = null;

  @SerializedName("settings")
  private ModelUserSetting settings = null;

  @SerializedName("user")
  private ModelUser user = null;
  public ModelUserDetails classrooms(List<ModelClassroom> classrooms) {
    this.classrooms = classrooms;
    return this;
  }

  public ModelUserDetails addClassroomsItem(ModelClassroom classroomsItem) {
    if (this.classrooms == null) {
      this.classrooms = new ArrayList<ModelClassroom>();
    }
    this.classrooms.add(classroomsItem);
    return this;
  }

  /**
  * Get classrooms
  * @return classrooms
  **/
  @Schema(description = "")
  public List<ModelClassroom> getClassrooms() {
    return classrooms;
  }
  public void setClassrooms(List<ModelClassroom> classrooms) {
    this.classrooms = classrooms;
  }
  public ModelUserDetails level(ModelUserLevel level) {
    this.level = level;
    return this;
  }

  

  /**
  * Get level
  * @return level
  **/
  @Schema(description = "")
  public ModelUserLevel getLevel() {
    return level;
  }
  public void setLevel(ModelUserLevel level) {
    this.level = level;
  }
  public ModelUserDetails roles(List<String> roles) {
    this.roles = roles;
    return this;
  }

  public ModelUserDetails addRolesItem(String rolesItem) {
    if (this.roles == null) {
      this.roles = new ArrayList<String>();
    }
    this.roles.add(rolesItem);
    return this;
  }

  /**
  * Get roles
  * @return roles
  **/
  @Schema(description = "")
  public List<String> getRoles() {
    return roles;
  }
  public void setRoles(List<String> roles) {
    this.roles = roles;
  }
  public ModelUserDetails settings(ModelUserSetting settings) {
    this.settings = settings;
    return this;
  }

  

  /**
  * Get settings
  * @return settings
  **/
  @Schema(description = "")
  public ModelUserSetting getSettings() {
    return settings;
  }
  public void setSettings(ModelUserSetting settings) {
    this.settings = settings;
  }
  public ModelUserDetails user(ModelUser user) {
    this.user = user;
    return this;
  }

  

  /**
  * Get user
  * @return user
  **/
  @Schema(description = "")
  public ModelUser getUser() {
    return user;
  }
  public void setUser(ModelUser user) {
    this.user = user;
  }
  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ModelUserDetails modelUserDetails = (ModelUserDetails) o;
    return Objects.equals(this.classrooms, modelUserDetails.classrooms) &&
        Objects.equals(this.level, modelUserDetails.level) &&
        Objects.equals(this.roles, modelUserDetails.roles) &&
        Objects.equals(this.settings, modelUserDetails.settings) &&
        Objects.equals(this.user, modelUserDetails.user);
  }

  @Override
  public int hashCode() {
    return java.util.Objects.hash(classrooms, level, roles, settings, user);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ModelUserDetails {\n");
    
    sb.append("    classrooms: ").append(toIndentedString(classrooms)).append("\n");
    sb.append("    level: ").append(toIndentedString(level)).append("\n");
    sb.append("    roles: ").append(toIndentedString(roles)).append("\n");
    sb.append("    settings: ").append(toIndentedString(settings)).append("\n");
    sb.append("    user: ").append(toIndentedString(user)).append("\n");
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
