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
 * MainGameDefinition
 */

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.JavaClientCodegen", date = "2019-06-11T01:49:26.007Z[GMT]")public class MainGameDefinition {

  @SerializedName("arenaHeight")
  private Integer arenaHeight = null;

  @SerializedName("arenaWidth")
  private Integer arenaWidth = null;

  @SerializedName("buletSpeed")
  private Integer buletSpeed = null;

  @SerializedName("bulletSize")
  private Integer bulletSize = null;

  @SerializedName("codes")
  private List<MainCode> codes = null;

  @SerializedName("description")
  private String description = null;

  @SerializedName("duration")
  private Integer duration = null;

  @SerializedName("energy")
  private Integer energy = null;

  @SerializedName("fireEnergyCost")
  private Integer fireEnergyCost = null;

  @SerializedName("fps")
  private Integer fps = null;

  @SerializedName("gameComponents")
  private List<MainGameComponent> gameComponents = null;

  @SerializedName("id")
  private Integer id = null;

  @SerializedName("increaseSpeedEnergyCost")
  private Integer increaseSpeedEnergyCost = null;

  @SerializedName("increaseSpeedPercentage")
  private Integer increaseSpeedPercentage = null;

  @SerializedName("label")
  private String label = null;

  @SerializedName("life")
  private Integer life = null;

  @SerializedName("luchadorSize")
  private Integer luchadorSize = null;

  @SerializedName("maxFireAmount")
  private Integer maxFireAmount = null;

  @SerializedName("maxFireCooldown")
  private Integer maxFireCooldown = null;

  @SerializedName("maxFireDamage")
  private Integer maxFireDamage = null;

  @SerializedName("maxParticipants")
  private Integer maxParticipants = null;

  @SerializedName("minFireAmount")
  private Integer minFireAmount = null;

  @SerializedName("minFireDamage")
  private Integer minFireDamage = null;

  @SerializedName("minParticipants")
  private Integer minParticipants = null;

  @SerializedName("moveSpeed")
  private Integer moveSpeed = null;

  @SerializedName("name")
  private String name = null;

  @SerializedName("punchAngle")
  private Integer punchAngle = null;

  @SerializedName("punchCoolDown")
  private Integer punchCoolDown = null;

  @SerializedName("punchDamage")
  private Integer punchDamage = null;

  @SerializedName("radarAngle")
  private Integer radarAngle = null;

  @SerializedName("radarRadius")
  private Integer radarRadius = null;

  @SerializedName("recycledLuchadorEnergyRestore")
  private Integer recycledLuchadorEnergyRestore = null;

  @SerializedName("respawnCooldown")
  private Integer respawnCooldown = null;

  @SerializedName("respawnX")
  private Integer respawnX = null;

  @SerializedName("respawnY")
  private Integer respawnY = null;

  @SerializedName("restoreEnergyperSecond")
  private Integer restoreEnergyperSecond = null;

  @SerializedName("sceneComponents")
  private List<MainSceneComponent> sceneComponents = null;

  @SerializedName("sortOrder")
  private Integer sortOrder = null;

  @SerializedName("suggestedCodes")
  private List<MainCode> suggestedCodes = null;

  @SerializedName("turnGunSpeed")
  private Integer turnGunSpeed = null;

  @SerializedName("turnSpeed")
  private Integer turnSpeed = null;

  @SerializedName("type")
  private String type = null;
  public MainGameDefinition arenaHeight(Integer arenaHeight) {
    this.arenaHeight = arenaHeight;
    return this;
  }

  

  /**
  * Get arenaHeight
  * @return arenaHeight
  **/
  @Schema(description = "")
  public Integer getArenaHeight() {
    return arenaHeight;
  }
  public void setArenaHeight(Integer arenaHeight) {
    this.arenaHeight = arenaHeight;
  }
  public MainGameDefinition arenaWidth(Integer arenaWidth) {
    this.arenaWidth = arenaWidth;
    return this;
  }

  

  /**
  * Get arenaWidth
  * @return arenaWidth
  **/
  @Schema(description = "")
  public Integer getArenaWidth() {
    return arenaWidth;
  }
  public void setArenaWidth(Integer arenaWidth) {
    this.arenaWidth = arenaWidth;
  }
  public MainGameDefinition buletSpeed(Integer buletSpeed) {
    this.buletSpeed = buletSpeed;
    return this;
  }

  

  /**
  * Get buletSpeed
  * @return buletSpeed
  **/
  @Schema(description = "")
  public Integer getBuletSpeed() {
    return buletSpeed;
  }
  public void setBuletSpeed(Integer buletSpeed) {
    this.buletSpeed = buletSpeed;
  }
  public MainGameDefinition bulletSize(Integer bulletSize) {
    this.bulletSize = bulletSize;
    return this;
  }

  

  /**
  * Get bulletSize
  * @return bulletSize
  **/
  @Schema(description = "")
  public Integer getBulletSize() {
    return bulletSize;
  }
  public void setBulletSize(Integer bulletSize) {
    this.bulletSize = bulletSize;
  }
  public MainGameDefinition codes(List<MainCode> codes) {
    this.codes = codes;
    return this;
  }

  public MainGameDefinition addCodesItem(MainCode codesItem) {
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
  public MainGameDefinition description(String description) {
    this.description = description;
    return this;
  }

  

  /**
  * Get description
  * @return description
  **/
  @Schema(description = "")
  public String getDescription() {
    return description;
  }
  public void setDescription(String description) {
    this.description = description;
  }
  public MainGameDefinition duration(Integer duration) {
    this.duration = duration;
    return this;
  }

  

  /**
  * Get duration
  * @return duration
  **/
  @Schema(description = "")
  public Integer getDuration() {
    return duration;
  }
  public void setDuration(Integer duration) {
    this.duration = duration;
  }
  public MainGameDefinition energy(Integer energy) {
    this.energy = energy;
    return this;
  }

  

  /**
  * Get energy
  * @return energy
  **/
  @Schema(description = "")
  public Integer getEnergy() {
    return energy;
  }
  public void setEnergy(Integer energy) {
    this.energy = energy;
  }
  public MainGameDefinition fireEnergyCost(Integer fireEnergyCost) {
    this.fireEnergyCost = fireEnergyCost;
    return this;
  }

  

  /**
  * Get fireEnergyCost
  * @return fireEnergyCost
  **/
  @Schema(description = "")
  public Integer getFireEnergyCost() {
    return fireEnergyCost;
  }
  public void setFireEnergyCost(Integer fireEnergyCost) {
    this.fireEnergyCost = fireEnergyCost;
  }
  public MainGameDefinition fps(Integer fps) {
    this.fps = fps;
    return this;
  }

  

  /**
  * Get fps
  * @return fps
  **/
  @Schema(description = "")
  public Integer getFps() {
    return fps;
  }
  public void setFps(Integer fps) {
    this.fps = fps;
  }
  public MainGameDefinition gameComponents(List<MainGameComponent> gameComponents) {
    this.gameComponents = gameComponents;
    return this;
  }

  public MainGameDefinition addGameComponentsItem(MainGameComponent gameComponentsItem) {
    if (this.gameComponents == null) {
      this.gameComponents = new ArrayList<MainGameComponent>();
    }
    this.gameComponents.add(gameComponentsItem);
    return this;
  }

  /**
  * Get gameComponents
  * @return gameComponents
  **/
  @Schema(description = "")
  public List<MainGameComponent> getGameComponents() {
    return gameComponents;
  }
  public void setGameComponents(List<MainGameComponent> gameComponents) {
    this.gameComponents = gameComponents;
  }
  public MainGameDefinition id(Integer id) {
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
  public MainGameDefinition increaseSpeedEnergyCost(Integer increaseSpeedEnergyCost) {
    this.increaseSpeedEnergyCost = increaseSpeedEnergyCost;
    return this;
  }

  

  /**
  * Get increaseSpeedEnergyCost
  * @return increaseSpeedEnergyCost
  **/
  @Schema(description = "")
  public Integer getIncreaseSpeedEnergyCost() {
    return increaseSpeedEnergyCost;
  }
  public void setIncreaseSpeedEnergyCost(Integer increaseSpeedEnergyCost) {
    this.increaseSpeedEnergyCost = increaseSpeedEnergyCost;
  }
  public MainGameDefinition increaseSpeedPercentage(Integer increaseSpeedPercentage) {
    this.increaseSpeedPercentage = increaseSpeedPercentage;
    return this;
  }

  

  /**
  * Get increaseSpeedPercentage
  * @return increaseSpeedPercentage
  **/
  @Schema(description = "")
  public Integer getIncreaseSpeedPercentage() {
    return increaseSpeedPercentage;
  }
  public void setIncreaseSpeedPercentage(Integer increaseSpeedPercentage) {
    this.increaseSpeedPercentage = increaseSpeedPercentage;
  }
  public MainGameDefinition label(String label) {
    this.label = label;
    return this;
  }

  

  /**
  * Get label
  * @return label
  **/
  @Schema(description = "")
  public String getLabel() {
    return label;
  }
  public void setLabel(String label) {
    this.label = label;
  }
  public MainGameDefinition life(Integer life) {
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
  public MainGameDefinition luchadorSize(Integer luchadorSize) {
    this.luchadorSize = luchadorSize;
    return this;
  }

  

  /**
  * Get luchadorSize
  * @return luchadorSize
  **/
  @Schema(description = "")
  public Integer getLuchadorSize() {
    return luchadorSize;
  }
  public void setLuchadorSize(Integer luchadorSize) {
    this.luchadorSize = luchadorSize;
  }
  public MainGameDefinition maxFireAmount(Integer maxFireAmount) {
    this.maxFireAmount = maxFireAmount;
    return this;
  }

  

  /**
  * Get maxFireAmount
  * @return maxFireAmount
  **/
  @Schema(description = "")
  public Integer getMaxFireAmount() {
    return maxFireAmount;
  }
  public void setMaxFireAmount(Integer maxFireAmount) {
    this.maxFireAmount = maxFireAmount;
  }
  public MainGameDefinition maxFireCooldown(Integer maxFireCooldown) {
    this.maxFireCooldown = maxFireCooldown;
    return this;
  }

  

  /**
  * Get maxFireCooldown
  * @return maxFireCooldown
  **/
  @Schema(description = "")
  public Integer getMaxFireCooldown() {
    return maxFireCooldown;
  }
  public void setMaxFireCooldown(Integer maxFireCooldown) {
    this.maxFireCooldown = maxFireCooldown;
  }
  public MainGameDefinition maxFireDamage(Integer maxFireDamage) {
    this.maxFireDamage = maxFireDamage;
    return this;
  }

  

  /**
  * Get maxFireDamage
  * @return maxFireDamage
  **/
  @Schema(description = "")
  public Integer getMaxFireDamage() {
    return maxFireDamage;
  }
  public void setMaxFireDamage(Integer maxFireDamage) {
    this.maxFireDamage = maxFireDamage;
  }
  public MainGameDefinition maxParticipants(Integer maxParticipants) {
    this.maxParticipants = maxParticipants;
    return this;
  }

  

  /**
  * Get maxParticipants
  * @return maxParticipants
  **/
  @Schema(description = "")
  public Integer getMaxParticipants() {
    return maxParticipants;
  }
  public void setMaxParticipants(Integer maxParticipants) {
    this.maxParticipants = maxParticipants;
  }
  public MainGameDefinition minFireAmount(Integer minFireAmount) {
    this.minFireAmount = minFireAmount;
    return this;
  }

  

  /**
  * Get minFireAmount
  * @return minFireAmount
  **/
  @Schema(description = "")
  public Integer getMinFireAmount() {
    return minFireAmount;
  }
  public void setMinFireAmount(Integer minFireAmount) {
    this.minFireAmount = minFireAmount;
  }
  public MainGameDefinition minFireDamage(Integer minFireDamage) {
    this.minFireDamage = minFireDamage;
    return this;
  }

  

  /**
  * Get minFireDamage
  * @return minFireDamage
  **/
  @Schema(description = "")
  public Integer getMinFireDamage() {
    return minFireDamage;
  }
  public void setMinFireDamage(Integer minFireDamage) {
    this.minFireDamage = minFireDamage;
  }
  public MainGameDefinition minParticipants(Integer minParticipants) {
    this.minParticipants = minParticipants;
    return this;
  }

  

  /**
  * Get minParticipants
  * @return minParticipants
  **/
  @Schema(description = "")
  public Integer getMinParticipants() {
    return minParticipants;
  }
  public void setMinParticipants(Integer minParticipants) {
    this.minParticipants = minParticipants;
  }
  public MainGameDefinition moveSpeed(Integer moveSpeed) {
    this.moveSpeed = moveSpeed;
    return this;
  }

  

  /**
  * Get moveSpeed
  * @return moveSpeed
  **/
  @Schema(description = "")
  public Integer getMoveSpeed() {
    return moveSpeed;
  }
  public void setMoveSpeed(Integer moveSpeed) {
    this.moveSpeed = moveSpeed;
  }
  public MainGameDefinition name(String name) {
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
  public MainGameDefinition punchAngle(Integer punchAngle) {
    this.punchAngle = punchAngle;
    return this;
  }

  

  /**
  * Get punchAngle
  * @return punchAngle
  **/
  @Schema(description = "")
  public Integer getPunchAngle() {
    return punchAngle;
  }
  public void setPunchAngle(Integer punchAngle) {
    this.punchAngle = punchAngle;
  }
  public MainGameDefinition punchCoolDown(Integer punchCoolDown) {
    this.punchCoolDown = punchCoolDown;
    return this;
  }

  

  /**
  * Get punchCoolDown
  * @return punchCoolDown
  **/
  @Schema(description = "")
  public Integer getPunchCoolDown() {
    return punchCoolDown;
  }
  public void setPunchCoolDown(Integer punchCoolDown) {
    this.punchCoolDown = punchCoolDown;
  }
  public MainGameDefinition punchDamage(Integer punchDamage) {
    this.punchDamage = punchDamage;
    return this;
  }

  

  /**
  * Get punchDamage
  * @return punchDamage
  **/
  @Schema(description = "")
  public Integer getPunchDamage() {
    return punchDamage;
  }
  public void setPunchDamage(Integer punchDamage) {
    this.punchDamage = punchDamage;
  }
  public MainGameDefinition radarAngle(Integer radarAngle) {
    this.radarAngle = radarAngle;
    return this;
  }

  

  /**
  * Get radarAngle
  * @return radarAngle
  **/
  @Schema(description = "")
  public Integer getRadarAngle() {
    return radarAngle;
  }
  public void setRadarAngle(Integer radarAngle) {
    this.radarAngle = radarAngle;
  }
  public MainGameDefinition radarRadius(Integer radarRadius) {
    this.radarRadius = radarRadius;
    return this;
  }

  

  /**
  * Get radarRadius
  * @return radarRadius
  **/
  @Schema(description = "")
  public Integer getRadarRadius() {
    return radarRadius;
  }
  public void setRadarRadius(Integer radarRadius) {
    this.radarRadius = radarRadius;
  }
  public MainGameDefinition recycledLuchadorEnergyRestore(Integer recycledLuchadorEnergyRestore) {
    this.recycledLuchadorEnergyRestore = recycledLuchadorEnergyRestore;
    return this;
  }

  

  /**
  * Get recycledLuchadorEnergyRestore
  * @return recycledLuchadorEnergyRestore
  **/
  @Schema(description = "")
  public Integer getRecycledLuchadorEnergyRestore() {
    return recycledLuchadorEnergyRestore;
  }
  public void setRecycledLuchadorEnergyRestore(Integer recycledLuchadorEnergyRestore) {
    this.recycledLuchadorEnergyRestore = recycledLuchadorEnergyRestore;
  }
  public MainGameDefinition respawnCooldown(Integer respawnCooldown) {
    this.respawnCooldown = respawnCooldown;
    return this;
  }

  

  /**
  * Get respawnCooldown
  * @return respawnCooldown
  **/
  @Schema(description = "")
  public Integer getRespawnCooldown() {
    return respawnCooldown;
  }
  public void setRespawnCooldown(Integer respawnCooldown) {
    this.respawnCooldown = respawnCooldown;
  }
  public MainGameDefinition respawnX(Integer respawnX) {
    this.respawnX = respawnX;
    return this;
  }

  

  /**
  * Get respawnX
  * @return respawnX
  **/
  @Schema(description = "")
  public Integer getRespawnX() {
    return respawnX;
  }
  public void setRespawnX(Integer respawnX) {
    this.respawnX = respawnX;
  }
  public MainGameDefinition respawnY(Integer respawnY) {
    this.respawnY = respawnY;
    return this;
  }

  

  /**
  * Get respawnY
  * @return respawnY
  **/
  @Schema(description = "")
  public Integer getRespawnY() {
    return respawnY;
  }
  public void setRespawnY(Integer respawnY) {
    this.respawnY = respawnY;
  }
  public MainGameDefinition restoreEnergyperSecond(Integer restoreEnergyperSecond) {
    this.restoreEnergyperSecond = restoreEnergyperSecond;
    return this;
  }

  

  /**
  * Get restoreEnergyperSecond
  * @return restoreEnergyperSecond
  **/
  @Schema(description = "")
  public Integer getRestoreEnergyperSecond() {
    return restoreEnergyperSecond;
  }
  public void setRestoreEnergyperSecond(Integer restoreEnergyperSecond) {
    this.restoreEnergyperSecond = restoreEnergyperSecond;
  }
  public MainGameDefinition sceneComponents(List<MainSceneComponent> sceneComponents) {
    this.sceneComponents = sceneComponents;
    return this;
  }

  public MainGameDefinition addSceneComponentsItem(MainSceneComponent sceneComponentsItem) {
    if (this.sceneComponents == null) {
      this.sceneComponents = new ArrayList<MainSceneComponent>();
    }
    this.sceneComponents.add(sceneComponentsItem);
    return this;
  }

  /**
  * Get sceneComponents
  * @return sceneComponents
  **/
  @Schema(description = "")
  public List<MainSceneComponent> getSceneComponents() {
    return sceneComponents;
  }
  public void setSceneComponents(List<MainSceneComponent> sceneComponents) {
    this.sceneComponents = sceneComponents;
  }
  public MainGameDefinition sortOrder(Integer sortOrder) {
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
  public MainGameDefinition suggestedCodes(List<MainCode> suggestedCodes) {
    this.suggestedCodes = suggestedCodes;
    return this;
  }

  public MainGameDefinition addSuggestedCodesItem(MainCode suggestedCodesItem) {
    if (this.suggestedCodes == null) {
      this.suggestedCodes = new ArrayList<MainCode>();
    }
    this.suggestedCodes.add(suggestedCodesItem);
    return this;
  }

  /**
  * Get suggestedCodes
  * @return suggestedCodes
  **/
  @Schema(description = "")
  public List<MainCode> getSuggestedCodes() {
    return suggestedCodes;
  }
  public void setSuggestedCodes(List<MainCode> suggestedCodes) {
    this.suggestedCodes = suggestedCodes;
  }
  public MainGameDefinition turnGunSpeed(Integer turnGunSpeed) {
    this.turnGunSpeed = turnGunSpeed;
    return this;
  }

  

  /**
  * Get turnGunSpeed
  * @return turnGunSpeed
  **/
  @Schema(description = "")
  public Integer getTurnGunSpeed() {
    return turnGunSpeed;
  }
  public void setTurnGunSpeed(Integer turnGunSpeed) {
    this.turnGunSpeed = turnGunSpeed;
  }
  public MainGameDefinition turnSpeed(Integer turnSpeed) {
    this.turnSpeed = turnSpeed;
    return this;
  }

  

  /**
  * Get turnSpeed
  * @return turnSpeed
  **/
  @Schema(description = "")
  public Integer getTurnSpeed() {
    return turnSpeed;
  }
  public void setTurnSpeed(Integer turnSpeed) {
    this.turnSpeed = turnSpeed;
  }
  public MainGameDefinition type(String type) {
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
    MainGameDefinition mainGameDefinition = (MainGameDefinition) o;
    return Objects.equals(this.arenaHeight, mainGameDefinition.arenaHeight) &&
        Objects.equals(this.arenaWidth, mainGameDefinition.arenaWidth) &&
        Objects.equals(this.buletSpeed, mainGameDefinition.buletSpeed) &&
        Objects.equals(this.bulletSize, mainGameDefinition.bulletSize) &&
        Objects.equals(this.codes, mainGameDefinition.codes) &&
        Objects.equals(this.description, mainGameDefinition.description) &&
        Objects.equals(this.duration, mainGameDefinition.duration) &&
        Objects.equals(this.energy, mainGameDefinition.energy) &&
        Objects.equals(this.fireEnergyCost, mainGameDefinition.fireEnergyCost) &&
        Objects.equals(this.fps, mainGameDefinition.fps) &&
        Objects.equals(this.gameComponents, mainGameDefinition.gameComponents) &&
        Objects.equals(this.id, mainGameDefinition.id) &&
        Objects.equals(this.increaseSpeedEnergyCost, mainGameDefinition.increaseSpeedEnergyCost) &&
        Objects.equals(this.increaseSpeedPercentage, mainGameDefinition.increaseSpeedPercentage) &&
        Objects.equals(this.label, mainGameDefinition.label) &&
        Objects.equals(this.life, mainGameDefinition.life) &&
        Objects.equals(this.luchadorSize, mainGameDefinition.luchadorSize) &&
        Objects.equals(this.maxFireAmount, mainGameDefinition.maxFireAmount) &&
        Objects.equals(this.maxFireCooldown, mainGameDefinition.maxFireCooldown) &&
        Objects.equals(this.maxFireDamage, mainGameDefinition.maxFireDamage) &&
        Objects.equals(this.maxParticipants, mainGameDefinition.maxParticipants) &&
        Objects.equals(this.minFireAmount, mainGameDefinition.minFireAmount) &&
        Objects.equals(this.minFireDamage, mainGameDefinition.minFireDamage) &&
        Objects.equals(this.minParticipants, mainGameDefinition.minParticipants) &&
        Objects.equals(this.moveSpeed, mainGameDefinition.moveSpeed) &&
        Objects.equals(this.name, mainGameDefinition.name) &&
        Objects.equals(this.punchAngle, mainGameDefinition.punchAngle) &&
        Objects.equals(this.punchCoolDown, mainGameDefinition.punchCoolDown) &&
        Objects.equals(this.punchDamage, mainGameDefinition.punchDamage) &&
        Objects.equals(this.radarAngle, mainGameDefinition.radarAngle) &&
        Objects.equals(this.radarRadius, mainGameDefinition.radarRadius) &&
        Objects.equals(this.recycledLuchadorEnergyRestore, mainGameDefinition.recycledLuchadorEnergyRestore) &&
        Objects.equals(this.respawnCooldown, mainGameDefinition.respawnCooldown) &&
        Objects.equals(this.respawnX, mainGameDefinition.respawnX) &&
        Objects.equals(this.respawnY, mainGameDefinition.respawnY) &&
        Objects.equals(this.restoreEnergyperSecond, mainGameDefinition.restoreEnergyperSecond) &&
        Objects.equals(this.sceneComponents, mainGameDefinition.sceneComponents) &&
        Objects.equals(this.sortOrder, mainGameDefinition.sortOrder) &&
        Objects.equals(this.suggestedCodes, mainGameDefinition.suggestedCodes) &&
        Objects.equals(this.turnGunSpeed, mainGameDefinition.turnGunSpeed) &&
        Objects.equals(this.turnSpeed, mainGameDefinition.turnSpeed) &&
        Objects.equals(this.type, mainGameDefinition.type);
  }

  @Override
  public int hashCode() {
    return java.util.Objects.hash(arenaHeight, arenaWidth, buletSpeed, bulletSize, codes, description, duration, energy, fireEnergyCost, fps, gameComponents, id, increaseSpeedEnergyCost, increaseSpeedPercentage, label, life, luchadorSize, maxFireAmount, maxFireCooldown, maxFireDamage, maxParticipants, minFireAmount, minFireDamage, minParticipants, moveSpeed, name, punchAngle, punchCoolDown, punchDamage, radarAngle, radarRadius, recycledLuchadorEnergyRestore, respawnCooldown, respawnX, respawnY, restoreEnergyperSecond, sceneComponents, sortOrder, suggestedCodes, turnGunSpeed, turnSpeed, type);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class MainGameDefinition {\n");
    
    sb.append("    arenaHeight: ").append(toIndentedString(arenaHeight)).append("\n");
    sb.append("    arenaWidth: ").append(toIndentedString(arenaWidth)).append("\n");
    sb.append("    buletSpeed: ").append(toIndentedString(buletSpeed)).append("\n");
    sb.append("    bulletSize: ").append(toIndentedString(bulletSize)).append("\n");
    sb.append("    codes: ").append(toIndentedString(codes)).append("\n");
    sb.append("    description: ").append(toIndentedString(description)).append("\n");
    sb.append("    duration: ").append(toIndentedString(duration)).append("\n");
    sb.append("    energy: ").append(toIndentedString(energy)).append("\n");
    sb.append("    fireEnergyCost: ").append(toIndentedString(fireEnergyCost)).append("\n");
    sb.append("    fps: ").append(toIndentedString(fps)).append("\n");
    sb.append("    gameComponents: ").append(toIndentedString(gameComponents)).append("\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    increaseSpeedEnergyCost: ").append(toIndentedString(increaseSpeedEnergyCost)).append("\n");
    sb.append("    increaseSpeedPercentage: ").append(toIndentedString(increaseSpeedPercentage)).append("\n");
    sb.append("    label: ").append(toIndentedString(label)).append("\n");
    sb.append("    life: ").append(toIndentedString(life)).append("\n");
    sb.append("    luchadorSize: ").append(toIndentedString(luchadorSize)).append("\n");
    sb.append("    maxFireAmount: ").append(toIndentedString(maxFireAmount)).append("\n");
    sb.append("    maxFireCooldown: ").append(toIndentedString(maxFireCooldown)).append("\n");
    sb.append("    maxFireDamage: ").append(toIndentedString(maxFireDamage)).append("\n");
    sb.append("    maxParticipants: ").append(toIndentedString(maxParticipants)).append("\n");
    sb.append("    minFireAmount: ").append(toIndentedString(minFireAmount)).append("\n");
    sb.append("    minFireDamage: ").append(toIndentedString(minFireDamage)).append("\n");
    sb.append("    minParticipants: ").append(toIndentedString(minParticipants)).append("\n");
    sb.append("    moveSpeed: ").append(toIndentedString(moveSpeed)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    punchAngle: ").append(toIndentedString(punchAngle)).append("\n");
    sb.append("    punchCoolDown: ").append(toIndentedString(punchCoolDown)).append("\n");
    sb.append("    punchDamage: ").append(toIndentedString(punchDamage)).append("\n");
    sb.append("    radarAngle: ").append(toIndentedString(radarAngle)).append("\n");
    sb.append("    radarRadius: ").append(toIndentedString(radarRadius)).append("\n");
    sb.append("    recycledLuchadorEnergyRestore: ").append(toIndentedString(recycledLuchadorEnergyRestore)).append("\n");
    sb.append("    respawnCooldown: ").append(toIndentedString(respawnCooldown)).append("\n");
    sb.append("    respawnX: ").append(toIndentedString(respawnX)).append("\n");
    sb.append("    respawnY: ").append(toIndentedString(respawnY)).append("\n");
    sb.append("    restoreEnergyperSecond: ").append(toIndentedString(restoreEnergyperSecond)).append("\n");
    sb.append("    sceneComponents: ").append(toIndentedString(sceneComponents)).append("\n");
    sb.append("    sortOrder: ").append(toIndentedString(sortOrder)).append("\n");
    sb.append("    suggestedCodes: ").append(toIndentedString(suggestedCodes)).append("\n");
    sb.append("    turnGunSpeed: ").append(toIndentedString(turnGunSpeed)).append("\n");
    sb.append("    turnSpeed: ").append(toIndentedString(turnSpeed)).append("\n");
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
