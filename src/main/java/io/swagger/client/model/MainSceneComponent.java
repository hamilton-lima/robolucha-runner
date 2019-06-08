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
 * MainSceneComponent
 */

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.JavaClientCodegen", date = "2019-06-05T23:30:27.176Z[GMT]")public class MainSceneComponent {

  @SerializedName("blockMovement")
  private Boolean blockMovement = null;

  @SerializedName("codes")
  private List<MainCode> codes = null;

  @SerializedName("colider")
  private Boolean colider = null;

  @SerializedName("gameDefinition")
  private Integer gameDefinition = null;

  @SerializedName("height")
  private Integer height = null;

  @SerializedName("id")
  private Integer id = null;

  @SerializedName("name")
  private String name = null;

  @SerializedName("respawn")
  private Boolean respawn = null;

  @SerializedName("rotation")
  private Integer rotation = null;

  @SerializedName("showInRadar")
  private Boolean showInRadar = null;

  @SerializedName("width")
  private Integer width = null;

  @SerializedName("x")
  private Integer x = null;

  @SerializedName("y")
  private Integer y = null;
  public MainSceneComponent blockMovement(Boolean blockMovement) {
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
  public MainSceneComponent codes(List<MainCode> codes) {
    this.codes = codes;
    return this;
  }

  public MainSceneComponent addCodesItem(MainCode codesItem) {
    if (this.codes == null) {
      this.codes = new ArrayList<MainCode>();
    }
    this.codes.add(codesItem);
    return this;
  }

  /**
  * Get codes
  * @return codes
  **/
  @Schema(description = "")
  public List<MainCode> getCodes() {
    return codes;
  }
  public void setCodes(List<MainCode> codes) {
    this.codes = codes;
  }
  public MainSceneComponent colider(Boolean colider) {
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
  public MainSceneComponent gameDefinition(Integer gameDefinition) {
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
  public MainSceneComponent height(Integer height) {
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
  public MainSceneComponent id(Integer id) {
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
  public MainSceneComponent name(String name) {
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
  public MainSceneComponent respawn(Boolean respawn) {
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
  public MainSceneComponent rotation(Integer rotation) {
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
  public MainSceneComponent showInRadar(Boolean showInRadar) {
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
  public MainSceneComponent width(Integer width) {
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
  public MainSceneComponent x(Integer x) {
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
  public MainSceneComponent y(Integer y) {
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
    MainSceneComponent mainSceneComponent = (MainSceneComponent) o;
    return Objects.equals(this.blockMovement, mainSceneComponent.blockMovement) &&
        Objects.equals(this.codes, mainSceneComponent.codes) &&
        Objects.equals(this.colider, mainSceneComponent.colider) &&
        Objects.equals(this.gameDefinition, mainSceneComponent.gameDefinition) &&
        Objects.equals(this.height, mainSceneComponent.height) &&
        Objects.equals(this.id, mainSceneComponent.id) &&
        Objects.equals(this.name, mainSceneComponent.name) &&
        Objects.equals(this.respawn, mainSceneComponent.respawn) &&
        Objects.equals(this.rotation, mainSceneComponent.rotation) &&
        Objects.equals(this.showInRadar, mainSceneComponent.showInRadar) &&
        Objects.equals(this.width, mainSceneComponent.width) &&
        Objects.equals(this.x, mainSceneComponent.x) &&
        Objects.equals(this.y, mainSceneComponent.y);
  }

  @Override
  public int hashCode() {
    return java.util.Objects.hash(blockMovement, codes, colider, gameDefinition, height, id, name, respawn, rotation, showInRadar, width, x, y);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class MainSceneComponent {\n");
    
    sb.append("    blockMovement: ").append(toIndentedString(blockMovement)).append("\n");
    sb.append("    codes: ").append(toIndentedString(codes)).append("\n");
    sb.append("    colider: ").append(toIndentedString(colider)).append("\n");
    sb.append("    gameDefinition: ").append(toIndentedString(gameDefinition)).append("\n");
    sb.append("    height: ").append(toIndentedString(height)).append("\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    respawn: ").append(toIndentedString(respawn)).append("\n");
    sb.append("    rotation: ").append(toIndentedString(rotation)).append("\n");
    sb.append("    showInRadar: ").append(toIndentedString(showInRadar)).append("\n");
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
