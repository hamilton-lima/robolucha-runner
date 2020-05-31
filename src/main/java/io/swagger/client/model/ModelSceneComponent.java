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
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * ModelSceneComponent
 */

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.JavaClientCodegen", date = "2020-05-31T15:22:14.057-04:00[America/Toronto]")public class ModelSceneComponent {

  @SerializedName("alpha")
  private BigDecimal alpha = null;

  @SerializedName("blockMovement")
  private Boolean blockMovement = null;

  @SerializedName("codes")
  private List<ModelCode> codes = null;

  @SerializedName("colider")
  private Boolean colider = null;

  @SerializedName("color")
  private String color = null;

  @SerializedName("gameDefinition")
  private Integer gameDefinition = null;

  @SerializedName("height")
  private Integer height = null;

  @SerializedName("id")
  private Integer id = null;

  @SerializedName("life")
  private Integer life = null;

  @SerializedName("respawn")
  private Boolean respawn = null;

  @SerializedName("rotation")
  private Integer rotation = null;

  @SerializedName("showInRadar")
  private Boolean showInRadar = null;

  @SerializedName("type")
  private String type = null;

  @SerializedName("width")
  private Integer width = null;

  @SerializedName("x")
  private Integer x = null;

  @SerializedName("y")
  private Integer y = null;
  public ModelSceneComponent alpha(BigDecimal alpha) {
    this.alpha = alpha;
    return this;
  }

  

  /**
  * Get alpha
  * @return alpha
  **/
  @Schema(description = "")
  public BigDecimal getAlpha() {
    return alpha;
  }
  public void setAlpha(BigDecimal alpha) {
    this.alpha = alpha;
  }
  public ModelSceneComponent blockMovement(Boolean blockMovement) {
    this.blockMovement = blockMovement;
    return this;
  }

  

  /**
  * Get blockMovement
  * @return blockMovement
  **/
  @Schema(description = "")
  public Boolean isBlockMovement() {
    return blockMovement;
  }
  public void setBlockMovement(Boolean blockMovement) {
    this.blockMovement = blockMovement;
  }
  public ModelSceneComponent codes(List<ModelCode> codes) {
    this.codes = codes;
    return this;
  }

  public ModelSceneComponent addCodesItem(ModelCode codesItem) {
    if (this.codes == null) {
      this.codes = new ArrayList<ModelCode>();
    }
    this.codes.add(codesItem);
    return this;
  }

  /**
  * Get codes
  * @return codes
  **/
  @Schema(description = "")
  public List<ModelCode> getCodes() {
    return codes;
  }
  public void setCodes(List<ModelCode> codes) {
    this.codes = codes;
  }
  public ModelSceneComponent colider(Boolean colider) {
    this.colider = colider;
    return this;
  }

  

  /**
  * Get colider
  * @return colider
  **/
  @Schema(description = "")
  public Boolean isColider() {
    return colider;
  }
  public void setColider(Boolean colider) {
    this.colider = colider;
  }
  public ModelSceneComponent color(String color) {
    this.color = color;
    return this;
  }

  

  /**
  * Get color
  * @return color
  **/
  @Schema(description = "")
  public String getColor() {
    return color;
  }
  public void setColor(String color) {
    this.color = color;
  }
  public ModelSceneComponent gameDefinition(Integer gameDefinition) {
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
  public ModelSceneComponent height(Integer height) {
    this.height = height;
    return this;
  }

  

  /**
  * Get height
  * @return height
  **/
  @Schema(description = "")
  public Integer getHeight() {
    return height;
  }
  public void setHeight(Integer height) {
    this.height = height;
  }
  public ModelSceneComponent id(Integer id) {
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
  public ModelSceneComponent life(Integer life) {
    this.life = life;
    return this;
  }

  

  /**
  * Get life
  * @return life
  **/
  @Schema(description = "")
  public Integer getLife() {
    return life;
  }
  public void setLife(Integer life) {
    this.life = life;
  }
  public ModelSceneComponent respawn(Boolean respawn) {
    this.respawn = respawn;
    return this;
  }

  

  /**
  * Get respawn
  * @return respawn
  **/
  @Schema(description = "")
  public Boolean isRespawn() {
    return respawn;
  }
  public void setRespawn(Boolean respawn) {
    this.respawn = respawn;
  }
  public ModelSceneComponent rotation(Integer rotation) {
    this.rotation = rotation;
    return this;
  }

  

  /**
  * Get rotation
  * @return rotation
  **/
  @Schema(description = "")
  public Integer getRotation() {
    return rotation;
  }
  public void setRotation(Integer rotation) {
    this.rotation = rotation;
  }
  public ModelSceneComponent showInRadar(Boolean showInRadar) {
    this.showInRadar = showInRadar;
    return this;
  }

  

  /**
  * Get showInRadar
  * @return showInRadar
  **/
  @Schema(description = "")
  public Boolean isShowInRadar() {
    return showInRadar;
  }
  public void setShowInRadar(Boolean showInRadar) {
    this.showInRadar = showInRadar;
  }
  public ModelSceneComponent type(String type) {
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
  public ModelSceneComponent width(Integer width) {
    this.width = width;
    return this;
  }

  

  /**
  * Get width
  * @return width
  **/
  @Schema(description = "")
  public Integer getWidth() {
    return width;
  }
  public void setWidth(Integer width) {
    this.width = width;
  }
  public ModelSceneComponent x(Integer x) {
    this.x = x;
    return this;
  }

  

  /**
  * Get x
  * @return x
  **/
  @Schema(description = "")
  public Integer getX() {
    return x;
  }
  public void setX(Integer x) {
    this.x = x;
  }
  public ModelSceneComponent y(Integer y) {
    this.y = y;
    return this;
  }

  

  /**
  * Get y
  * @return y
  **/
  @Schema(description = "")
  public Integer getY() {
    return y;
  }
  public void setY(Integer y) {
    this.y = y;
  }
  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ModelSceneComponent modelSceneComponent = (ModelSceneComponent) o;
    return Objects.equals(this.alpha, modelSceneComponent.alpha) &&
        Objects.equals(this.blockMovement, modelSceneComponent.blockMovement) &&
        Objects.equals(this.codes, modelSceneComponent.codes) &&
        Objects.equals(this.colider, modelSceneComponent.colider) &&
        Objects.equals(this.color, modelSceneComponent.color) &&
        Objects.equals(this.gameDefinition, modelSceneComponent.gameDefinition) &&
        Objects.equals(this.height, modelSceneComponent.height) &&
        Objects.equals(this.id, modelSceneComponent.id) &&
        Objects.equals(this.life, modelSceneComponent.life) &&
        Objects.equals(this.respawn, modelSceneComponent.respawn) &&
        Objects.equals(this.rotation, modelSceneComponent.rotation) &&
        Objects.equals(this.showInRadar, modelSceneComponent.showInRadar) &&
        Objects.equals(this.type, modelSceneComponent.type) &&
        Objects.equals(this.width, modelSceneComponent.width) &&
        Objects.equals(this.x, modelSceneComponent.x) &&
        Objects.equals(this.y, modelSceneComponent.y);
  }

  @Override
  public int hashCode() {
    return java.util.Objects.hash(alpha, blockMovement, codes, colider, color, gameDefinition, height, id, life, respawn, rotation, showInRadar, type, width, x, y);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ModelSceneComponent {\n");
    
    sb.append("    alpha: ").append(toIndentedString(alpha)).append("\n");
    sb.append("    blockMovement: ").append(toIndentedString(blockMovement)).append("\n");
    sb.append("    codes: ").append(toIndentedString(codes)).append("\n");
    sb.append("    colider: ").append(toIndentedString(colider)).append("\n");
    sb.append("    color: ").append(toIndentedString(color)).append("\n");
    sb.append("    gameDefinition: ").append(toIndentedString(gameDefinition)).append("\n");
    sb.append("    height: ").append(toIndentedString(height)).append("\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    life: ").append(toIndentedString(life)).append("\n");
    sb.append("    respawn: ").append(toIndentedString(respawn)).append("\n");
    sb.append("    rotation: ").append(toIndentedString(rotation)).append("\n");
    sb.append("    showInRadar: ").append(toIndentedString(showInRadar)).append("\n");
    sb.append("    type: ").append(toIndentedString(type)).append("\n");
    sb.append("    width: ").append(toIndentedString(width)).append("\n");
    sb.append("    x: ").append(toIndentedString(x)).append("\n");
    sb.append("    y: ").append(toIndentedString(y)).append("\n");
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
