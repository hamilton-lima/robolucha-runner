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
 * MainUserSetting
 */

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.JavaClientCodegen", date = "2019-01-02T18:17:16.002098-05:00[America/Toronto]")public class MainUserSetting {

  @SerializedName("createdAt")
  private String createdAt = null;

  @SerializedName("deletedAt")
  private String deletedAt = null;

  @SerializedName("id")
  private Integer id = null;

  @SerializedName("lastOption")
  private String lastOption = null;

  @SerializedName("updatedAt")
  private String updatedAt = null;

  @SerializedName("userID")
  private Integer userID = null;
  public MainUserSetting createdAt(String createdAt) {
    this.createdAt = createdAt;
    return this;
  }

  

  /**
  * Get createdAt
  * @return createdAt
  **/
  @Schema(description = "")
  public String getCreatedAt() {
    return createdAt;
  }
  public void setCreatedAt(String createdAt) {
    this.createdAt = createdAt;
  }
  public MainUserSetting deletedAt(String deletedAt) {
    this.deletedAt = deletedAt;
    return this;
  }

  

  /**
  * Get deletedAt
  * @return deletedAt
  **/
  @Schema(description = "")
  public String getDeletedAt() {
    return deletedAt;
  }
  public void setDeletedAt(String deletedAt) {
    this.deletedAt = deletedAt;
  }
  public MainUserSetting id(Integer id) {
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
  public MainUserSetting lastOption(String lastOption) {
    this.lastOption = lastOption;
    return this;
  }

  

  /**
  * Get lastOption
  * @return lastOption
  **/
  @Schema(description = "")
  public String getLastOption() {
    return lastOption;
  }
  public void setLastOption(String lastOption) {
    this.lastOption = lastOption;
  }
  public MainUserSetting updatedAt(String updatedAt) {
    this.updatedAt = updatedAt;
    return this;
  }

  

  /**
  * Get updatedAt
  * @return updatedAt
  **/
  @Schema(description = "")
  public String getUpdatedAt() {
    return updatedAt;
  }
  public void setUpdatedAt(String updatedAt) {
    this.updatedAt = updatedAt;
  }
  public MainUserSetting userID(Integer userID) {
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
    MainUserSetting mainUserSetting = (MainUserSetting) o;
    return Objects.equals(this.createdAt, mainUserSetting.createdAt) &&
        Objects.equals(this.deletedAt, mainUserSetting.deletedAt) &&
        Objects.equals(this.id, mainUserSetting.id) &&
        Objects.equals(this.lastOption, mainUserSetting.lastOption) &&
        Objects.equals(this.updatedAt, mainUserSetting.updatedAt) &&
        Objects.equals(this.userID, mainUserSetting.userID);
  }

  @Override
  public int hashCode() {
    return java.util.Objects.hash(createdAt, deletedAt, id, lastOption, updatedAt, userID);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class MainUserSetting {\n");
    
    sb.append("    createdAt: ").append(toIndentedString(createdAt)).append("\n");
    sb.append("    deletedAt: ").append(toIndentedString(deletedAt)).append("\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    lastOption: ").append(toIndentedString(lastOption)).append("\n");
    sb.append("    updatedAt: ").append(toIndentedString(updatedAt)).append("\n");
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
