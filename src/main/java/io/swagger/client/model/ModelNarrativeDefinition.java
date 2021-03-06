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
 * ModelNarrativeDefinition
 */

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.JavaClientCodegen", date = "2020-12-26T18:37:24.497003-05:00[America/Toronto]")public class ModelNarrativeDefinition {

  @SerializedName("event")
  private String event = null;

  @SerializedName("gameDefinition")
  private Integer gameDefinition = null;

  @SerializedName("id")
  private Integer id = null;

  @SerializedName("media")
  private ModelMedia media = null;

  @SerializedName("sortOrder")
  private Integer sortOrder = null;

  @SerializedName("text")
  private String text = null;

  @SerializedName("type")
  private String type = null;
  public ModelNarrativeDefinition event(String event) {
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
  public ModelNarrativeDefinition gameDefinition(Integer gameDefinition) {
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
  public ModelNarrativeDefinition id(Integer id) {
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
  public ModelNarrativeDefinition media(ModelMedia media) {
    this.media = media;
    return this;
  }

  

  /**
  * Get media
  * @return media
  **/
  @Schema(description = "")
  public ModelMedia getMedia() {
    return media;
  }
  public void setMedia(ModelMedia media) {
    this.media = media;
  }
  public ModelNarrativeDefinition sortOrder(Integer sortOrder) {
    this.sortOrder = sortOrder;
    return this;
  }

  

  /**
  * Get sortOrder
  * @return sortOrder
  **/
  @Schema(description = "")
  public Integer getSortOrder() {
    return sortOrder;
  }
  public void setSortOrder(Integer sortOrder) {
    this.sortOrder = sortOrder;
  }
  public ModelNarrativeDefinition text(String text) {
    this.text = text;
    return this;
  }

  

  /**
  * Get text
  * @return text
  **/
  @Schema(description = "")
  public String getText() {
    return text;
  }
  public void setText(String text) {
    this.text = text;
  }
  public ModelNarrativeDefinition type(String type) {
    this.type = type;
    return this;
  }

  

  /**
  * Get type
  * @return type
  **/
  @Schema(description = "")
  public String getType() {
    return type;
  }
  public void setType(String type) {
    this.type = type;
  }
  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ModelNarrativeDefinition modelNarrativeDefinition = (ModelNarrativeDefinition) o;
    return Objects.equals(this.event, modelNarrativeDefinition.event) &&
        Objects.equals(this.gameDefinition, modelNarrativeDefinition.gameDefinition) &&
        Objects.equals(this.id, modelNarrativeDefinition.id) &&
        Objects.equals(this.media, modelNarrativeDefinition.media) &&
        Objects.equals(this.sortOrder, modelNarrativeDefinition.sortOrder) &&
        Objects.equals(this.text, modelNarrativeDefinition.text) &&
        Objects.equals(this.type, modelNarrativeDefinition.type);
  }

  @Override
  public int hashCode() {
    return java.util.Objects.hash(event, gameDefinition, id, media, sortOrder, text, type);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ModelNarrativeDefinition {\n");
    
    sb.append("    event: ").append(toIndentedString(event)).append("\n");
    sb.append("    gameDefinition: ").append(toIndentedString(gameDefinition)).append("\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    media: ").append(toIndentedString(media)).append("\n");
    sb.append("    sortOrder: ").append(toIndentedString(sortOrder)).append("\n");
    sb.append("    text: ").append(toIndentedString(text)).append("\n");
    sb.append("    type: ").append(toIndentedString(type)).append("\n");
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
