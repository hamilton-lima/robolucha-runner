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
 * ModelPageEventRequest
 */

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.JavaClientCodegen", date = "2020-05-30T16:45:46.153-04:00[America/Toronto]")public class ModelPageEventRequest {

  @SerializedName("AppName")
  private String appName = null;

  @SerializedName("AppVersion")
  private String appVersion = null;

  @SerializedName("action")
  private String action = null;

  @SerializedName("componentID")
  private String componentID = null;

  @SerializedName("page")
  private String page = null;
  public ModelPageEventRequest appName(String appName) {
    this.appName = appName;
    return this;
  }

  

  /**
  * Get appName
  * @return appName
  **/
  @Schema(description = "")
  public String getAppName() {
    return appName;
  }
  public void setAppName(String appName) {
    this.appName = appName;
  }
  public ModelPageEventRequest appVersion(String appVersion) {
    this.appVersion = appVersion;
    return this;
  }

  

  /**
  * Get appVersion
  * @return appVersion
  **/
  @Schema(description = "")
  public String getAppVersion() {
    return appVersion;
  }
  public void setAppVersion(String appVersion) {
    this.appVersion = appVersion;
  }
  public ModelPageEventRequest action(String action) {
    this.action = action;
    return this;
  }

  

  /**
  * Get action
  * @return action
  **/
  @Schema(description = "")
  public String getAction() {
    return action;
  }
  public void setAction(String action) {
    this.action = action;
  }
  public ModelPageEventRequest componentID(String componentID) {
    this.componentID = componentID;
    return this;
  }

  

  /**
  * Get componentID
  * @return componentID
  **/
  @Schema(description = "")
  public String getComponentID() {
    return componentID;
  }
  public void setComponentID(String componentID) {
    this.componentID = componentID;
  }
  public ModelPageEventRequest page(String page) {
    this.page = page;
    return this;
  }

  

  /**
  * Get page
  * @return page
  **/
  @Schema(description = "")
  public String getPage() {
    return page;
  }
  public void setPage(String page) {
    this.page = page;
  }
  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ModelPageEventRequest modelPageEventRequest = (ModelPageEventRequest) o;
    return Objects.equals(this.appName, modelPageEventRequest.appName) &&
        Objects.equals(this.appVersion, modelPageEventRequest.appVersion) &&
        Objects.equals(this.action, modelPageEventRequest.action) &&
        Objects.equals(this.componentID, modelPageEventRequest.componentID) &&
        Objects.equals(this.page, modelPageEventRequest.page);
  }

  @Override
  public int hashCode() {
    return java.util.Objects.hash(appName, appVersion, action, componentID, page);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ModelPageEventRequest {\n");
    
    sb.append("    appName: ").append(toIndentedString(appName)).append("\n");
    sb.append("    appVersion: ").append(toIndentedString(appVersion)).append("\n");
    sb.append("    action: ").append(toIndentedString(action)).append("\n");
    sb.append("    componentID: ").append(toIndentedString(componentID)).append("\n");
    sb.append("    page: ").append(toIndentedString(page)).append("\n");
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
