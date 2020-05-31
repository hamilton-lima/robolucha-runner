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
 * ModelConfig
 */

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.JavaClientCodegen", date = "2020-05-30T19:45:01.940-04:00[America/Toronto]")public class ModelConfig {

  @SerializedName("id")
  private Integer id = null;

  @SerializedName("key")
  private String key = null;

  @SerializedName("value")
  private String value = null;
  public ModelConfig id(Integer id) {
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
  public ModelConfig key(String key) {
    this.key = key;
    return this;
  }

  

  /**
  * Get key
  * @return key
  **/
  @Schema(description = "")
  public String getKey() {
    return key;
  }
  public void setKey(String key) {
    this.key = key;
  }
  public ModelConfig value(String value) {
    this.value = value;
    return this;
  }

  

  /**
  * Get value
  * @return value
  **/
  @Schema(description = "")
  public String getValue() {
    return value;
  }
  public void setValue(String value) {
    this.value = value;
  }
  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ModelConfig modelConfig = (ModelConfig) o;
    return Objects.equals(this.id, modelConfig.id) &&
        Objects.equals(this.key, modelConfig.key) &&
        Objects.equals(this.value, modelConfig.value);
  }

  @Override
  public int hashCode() {
    return java.util.Objects.hash(id, key, value);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ModelConfig {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    key: ").append(toIndentedString(key)).append("\n");
    sb.append("    value: ").append(toIndentedString(value)).append("\n");
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
