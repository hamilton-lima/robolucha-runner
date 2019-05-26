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
 * MainLuchador
 */

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.JavaClientCodegen", date = "2019-05-26T20:42:11.396Z[GMT]")public class MainLuchador {

  @SerializedName("codes")
  private List<MainCode> codes = null;

  @SerializedName("configs")
  private List<MainConfig> configs = null;

  @SerializedName("id")
  private Integer id = null;

  @SerializedName("name")
  private String name = null;

  @SerializedName("userID")
  private Integer userID = null;
  public MainLuchador codes(List<MainCode> codes) {
    this.codes = codes;
    return this;
  }

  public MainLuchador addCodesItem(MainCode codesItem) {
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
  public MainLuchador configs(List<MainConfig> configs) {
    this.configs = configs;
    return this;
  }

  public MainLuchador addConfigsItem(MainConfig configsItem) {
    if (this.configs == null) {
      this.configs = new ArrayList<MainConfig>();
    }
    this.configs.add(configsItem);
    return this;
  }

  /**
  * Get configs
  * @return configs
  **/
  @Schema(description = "")
  public List<MainConfig> getConfigs() {
    return configs;
  }
  public void setConfigs(List<MainConfig> configs) {
    this.configs = configs;
  }
  public MainLuchador id(Integer id) {
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
  public MainLuchador name(String name) {
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
  public MainLuchador userID(Integer userID) {
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
    MainLuchador mainLuchador = (MainLuchador) o;
    return Objects.equals(this.codes, mainLuchador.codes) &&
        Objects.equals(this.configs, mainLuchador.configs) &&
        Objects.equals(this.id, mainLuchador.id) &&
        Objects.equals(this.name, mainLuchador.name) &&
        Objects.equals(this.userID, mainLuchador.userID);
  }

  @Override
  public int hashCode() {
    return java.util.Objects.hash(codes, configs, id, name, userID);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class MainLuchador {\n");
    
    sb.append("    codes: ").append(toIndentedString(codes)).append("\n");
    sb.append("    configs: ").append(toIndentedString(configs)).append("\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
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
