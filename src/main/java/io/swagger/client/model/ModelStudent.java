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
 * ModelStudent
 */

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.JavaClientCodegen", date = "2020-05-31T15:22:14.057-04:00[America/Toronto]")public class ModelStudent {

  @SerializedName("classrooms")
  private List<ModelClassroom> classrooms = null;

  @SerializedName("id")
  private Integer id = null;

  @SerializedName("userID")
  private Integer userID = null;
  public ModelStudent classrooms(List<ModelClassroom> classrooms) {
    this.classrooms = classrooms;
    return this;
  }

  public ModelStudent addClassroomsItem(ModelClassroom classroomsItem) {
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
  public ModelStudent id(Integer id) {
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
  public ModelStudent userID(Integer userID) {
    this.userID = userID;
    return this;
  }

  

  /**
  * Get userID
  * @return userID
  **/
  @Schema(description = "")
  public Integer getUserID() {
    return userID;
  }
  public void setUserID(Integer userID) {
    this.userID = userID;
  }
  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ModelStudent modelStudent = (ModelStudent) o;
    return Objects.equals(this.classrooms, modelStudent.classrooms) &&
        Objects.equals(this.id, modelStudent.id) &&
        Objects.equals(this.userID, modelStudent.userID);
  }

  @Override
  public int hashCode() {
    return java.util.Objects.hash(classrooms, id, userID);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ModelStudent {\n");
    
    sb.append("    classrooms: ").append(toIndentedString(classrooms)).append("\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    userID: ").append(toIndentedString(userID)).append("\n");
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
