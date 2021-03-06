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
 * ModelCode
 */

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.JavaClientCodegen", date = "2020-12-26T18:37:24.497003-05:00[America/Toronto]")public class ModelCode {

  @SerializedName("blockly")
  private String blockly = null;

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

  @SerializedName("version")
  private Integer version = null;
  public ModelCode blockly(String blockly) {
    this.blockly = blockly;
    return this;
  }

  

  /**
  * Get blockly
  * @return blockly
  **/
  @Schema(description = "")
  public String getBlockly() {
    return blockly;
  }
  public void setBlockly(String blockly) {
    this.blockly = blockly;
  }
  public ModelCode event(String event) {
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
  public ModelCode exception(String exception) {
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
  public ModelCode gameDefinition(Integer gameDefinition) {
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
  public ModelCode id(Integer id) {
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
  public ModelCode script(String script) {
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
  public ModelCode version(Integer version) {
    this.version = version;
    return this;
  }

  

  /**
  * Get version
  * @return version
  **/
  @Schema(description = "")
  public Integer getVersion() {
    return version;
  }
  public void setVersion(Integer version) {
    this.version = version;
  }
  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ModelCode modelCode = (ModelCode) o;
    return Objects.equals(this.blockly, modelCode.blockly) &&
        Objects.equals(this.event, modelCode.event) &&
        Objects.equals(this.exception, modelCode.exception) &&
        Objects.equals(this.gameDefinition, modelCode.gameDefinition) &&
        Objects.equals(this.id, modelCode.id) &&
        Objects.equals(this.script, modelCode.script) &&
        Objects.equals(this.version, modelCode.version);
  }

  @Override
  public int hashCode() {
    return java.util.Objects.hash(blockly, event, exception, gameDefinition, id, script, version);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ModelCode {\n");
    
    sb.append("    blockly: ").append(toIndentedString(blockly)).append("\n");
    sb.append("    event: ").append(toIndentedString(event)).append("\n");
    sb.append("    exception: ").append(toIndentedString(exception)).append("\n");
    sb.append("    gameDefinition: ").append(toIndentedString(gameDefinition)).append("\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    script: ").append(toIndentedString(script)).append("\n");
    sb.append("    version: ").append(toIndentedString(version)).append("\n");
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
