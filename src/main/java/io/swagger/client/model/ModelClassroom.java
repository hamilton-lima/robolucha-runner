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
 * ModelClassroom
 */

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.JavaClientCodegen", date = "2020-05-31T15:22:14.057-04:00[America/Toronto]")public class ModelClassroom {

  @SerializedName("accessCode")
  private String accessCode = null;

  @SerializedName("id")
  private Integer id = null;

  @SerializedName("name")
  private String name = null;

  @SerializedName("ownerID")
  private Integer ownerID = null;

  @SerializedName("students")
  private List<ModelStudent> students = null;
  public ModelClassroom accessCode(String accessCode) {
    this.accessCode = accessCode;
    return this;
  }

  

  /**
  * Get accessCode
  * @return accessCode
  **/
  @Schema(description = "")
  public String getAccessCode() {
    return accessCode;
  }
  public void setAccessCode(String accessCode) {
    this.accessCode = accessCode;
  }
  public ModelClassroom id(Integer id) {
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
  public ModelClassroom name(String name) {
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
  public ModelClassroom ownerID(Integer ownerID) {
    this.ownerID = ownerID;
    return this;
  }

  

  /**
  * Get ownerID
  * @return ownerID
  **/
  @Schema(description = "")
  public Integer getOwnerID() {
    return ownerID;
  }
  public void setOwnerID(Integer ownerID) {
    this.ownerID = ownerID;
  }
  public ModelClassroom students(List<ModelStudent> students) {
    this.students = students;
    return this;
  }

  public ModelClassroom addStudentsItem(ModelStudent studentsItem) {
    if (this.students == null) {
      this.students = new ArrayList<ModelStudent>();
    }
    this.students.add(studentsItem);
    return this;
  }

  /**
  * Get students
  * @return students
  **/
  @Schema(description = "")
  public List<ModelStudent> getStudents() {
    return students;
  }
  public void setStudents(List<ModelStudent> students) {
    this.students = students;
  }
  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ModelClassroom modelClassroom = (ModelClassroom) o;
    return Objects.equals(this.accessCode, modelClassroom.accessCode) &&
        Objects.equals(this.id, modelClassroom.id) &&
        Objects.equals(this.name, modelClassroom.name) &&
        Objects.equals(this.ownerID, modelClassroom.ownerID) &&
        Objects.equals(this.students, modelClassroom.students);
  }

  @Override
  public int hashCode() {
    return java.util.Objects.hash(accessCode, id, name, ownerID, students);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ModelClassroom {\n");
    
    sb.append("    accessCode: ").append(toIndentedString(accessCode)).append("\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    ownerID: ").append(toIndentedString(ownerID)).append("\n");
    sb.append("    students: ").append(toIndentedString(students)).append("\n");
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
