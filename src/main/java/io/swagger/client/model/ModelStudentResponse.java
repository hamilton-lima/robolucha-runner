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
 * ModelStudentResponse
 */

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.JavaClientCodegen", date = "2020-09-25T01:07:09.701808-04:00[America/Toronto]")public class ModelStudentResponse {

  @SerializedName("studentID")
  private Integer studentID = null;

  @SerializedName("userID")
  private Integer userID = null;

  @SerializedName("username")
  private String username = null;
  public ModelStudentResponse studentID(Integer studentID) {
    this.studentID = studentID;
    return this;
  }

  

  /**
  * Get studentID
  * @return studentID
  **/
  @Schema(description = "")
  public Integer getStudentID() {
    return studentID;
  }
  public void setStudentID(Integer studentID) {
    this.studentID = studentID;
  }
  public ModelStudentResponse userID(Integer userID) {
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
  public ModelStudentResponse username(String username) {
    this.username = username;
    return this;
  }

  

  /**
  * Get username
  * @return username
  **/
  @Schema(description = "")
  public String getUsername() {
    return username;
  }
  public void setUsername(String username) {
    this.username = username;
  }
  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ModelStudentResponse modelStudentResponse = (ModelStudentResponse) o;
    return Objects.equals(this.studentID, modelStudentResponse.studentID) &&
        Objects.equals(this.userID, modelStudentResponse.userID) &&
        Objects.equals(this.username, modelStudentResponse.username);
  }

  @Override
  public int hashCode() {
    return java.util.Objects.hash(studentID, userID, username);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ModelStudentResponse {\n");
    
    sb.append("    studentID: ").append(toIndentedString(studentID)).append("\n");
    sb.append("    userID: ").append(toIndentedString(userID)).append("\n");
    sb.append("    username: ").append(toIndentedString(username)).append("\n");
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
