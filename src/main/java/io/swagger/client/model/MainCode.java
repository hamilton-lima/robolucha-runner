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
 * MainCode
 */

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.JavaClientCodegen", date = "2019-06-02T00:29:17.029Z[GMT]")public class MainCode {

  @SerializedName("event")
  private String event = null;

  @SerializedName("exception")
  private String exception = null;

  @SerializedName("gameDefinition")
  private Integer gameDefinition = null;

  @SerializedName("id")
  private Integer id = null;

  @SerializedName("script")
  private String script = null;
  public MainCode event(String event) {
    this.event = event;
    return this;
  }

  

  /**
  * Get event
  * @return event
  **/
  @Schema(description = "")
  public String getEvent() {
    return event;
  }
  public void setEvent(String event) {
    this.event = event;
  }
  public MainCode exception(String exception) {
    this.exception = exception;
    return this;
  }

  

  /**
  * Get exception
  * @return exception
  **/
  @Schema(description = "")
  public String getException() {
    return exception;
  }
  public void setException(String exception) {
    this.exception = exception;
  }
  public MainCode gameDefinition(Integer gameDefinition) {
    this.gameDefinition = gameDefinition;
    return this;
  }

  

  /**
  * Get gameDefinition
  * @return gameDefinition
  **/
  @Schema(description = "")
  public Integer getGameDefinition() {
    return gameDefinition;
  }
  public void setGameDefinition(Integer gameDefinition) {
    this.gameDefinition = gameDefinition;
  }
  public MainCode id(Integer id) {
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
  public MainCode script(String script) {
    this.script = script;
    return this;
  }

  

  /**
  * Get script
  * @return script
  **/
  @Schema(description = "")
  public String getScript() {
    return script;
  }
  public void setScript(String script) {
    this.script = script;
  }
  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    MainCode mainCode = (MainCode) o;
    return Objects.equals(this.event, mainCode.event) &&
        Objects.equals(this.exception, mainCode.exception) &&
        Objects.equals(this.gameDefinition, mainCode.gameDefinition) &&
        Objects.equals(this.id, mainCode.id) &&
        Objects.equals(this.script, mainCode.script);
  }

  @Override
  public int hashCode() {
    return java.util.Objects.hash(event, exception, gameDefinition, id, script);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class MainCode {\n");
    
    sb.append("    event: ").append(toIndentedString(event)).append("\n");
    sb.append("    exception: ").append(toIndentedString(exception)).append("\n");
    sb.append("    gameDefinition: ").append(toIndentedString(gameDefinition)).append("\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    script: ").append(toIndentedString(script)).append("\n");
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
