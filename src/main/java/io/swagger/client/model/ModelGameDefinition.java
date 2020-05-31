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
 * ModelGameDefinition
 */

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.JavaClientCodegen", date = "2020-05-30T19:45:01.940-04:00[America/Toronto]")public class ModelGameDefinition {

  @SerializedName("arenaHeight")
  private Integer arenaHeight = null;

  @SerializedName("arenaWidth")
  private Integer arenaWidth = null;

  @SerializedName("buletSpeed")
  private Integer buletSpeed = null;

  @SerializedName("bulletSize")
  private Integer bulletSize = null;

  @SerializedName("codes")
  private List<ModelCode> codes = null;

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
  private List<ModelGameComponent> gameComponents = null;

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

  @SerializedName("maxLevel")
  private Integer maxLevel = null;

  @SerializedName("maxParticipants")
  private Integer maxParticipants = null;

  @SerializedName("minFireAmount")
  private Integer minFireAmount = null;

  @SerializedName("minFireDamage")
  private Integer minFireDamage = null;

  @SerializedName("minLevel")
  private Integer minLevel = null;

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

  @SerializedName("respawnAngle")
  private Integer respawnAngle = null;

  @SerializedName("respawnCooldown")
  private Integer respawnCooldown = null;

  @SerializedName("respawnGunAngle")
  private Integer respawnGunAngle = null;

  @SerializedName("respawnX")
  private Integer respawnX = null;

  @SerializedName("respawnY")
  private Integer respawnY = null;

  @SerializedName("restoreEnergyperSecond")
  private Integer restoreEnergyperSecond = null;

  @SerializedName("sceneComponents")
  private List<ModelSceneComponent> sceneComponents = null;

  @SerializedName("sortOrder")
  private Integer sortOrder = null;

  @SerializedName("suggestedCodes")
  private List<ModelCode> suggestedCodes = null;

  @SerializedName("turnGunSpeed")
  private Integer turnGunSpeed = null;

  @SerializedName("turnSpeed")
  private Integer turnSpeed = null;

  @SerializedName("type")
  private String type = null;

  @SerializedName("unblockLevel")
  private Integer unblockLevel = null;
  public ModelGameDefinition arenaHeight(Integer arenaHeight) {
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
  public ModelGameDefinition arenaWidth(Integer arenaWidth) {
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
  public ModelGameDefinition buletSpeed(Integer buletSpeed) {
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
  public ModelGameDefinition bulletSize(Integer bulletSize) {
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
  public ModelGameDefinition codes(List<ModelCode> codes) {
    this.codes = codes;
    return this;
  }

  public ModelGameDefinition addCodesItem(ModelCode codesItem) {
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
  public ModelGameDefinition description(String description) {
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
  public ModelGameDefinition duration(Integer duration) {
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
  public ModelGameDefinition energy(Integer energy) {
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
  public ModelGameDefinition fireEnergyCost(Integer fireEnergyCost) {
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
  public ModelGameDefinition fps(Integer fps) {
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
  public ModelGameDefinition gameComponents(List<ModelGameComponent> gameComponents) {
    this.gameComponents = gameComponents;
    return this;
  }

  public ModelGameDefinition addGameComponentsItem(ModelGameComponent gameComponentsItem) {
    if (this.gameComponents == null) {
      this.gameComponents = new ArrayList<ModelGameComponent>();
    }
    this.gameComponents.add(gameComponentsItem);
    return this;
  }

  /**
  * Get gameComponents
  * @return gameComponents
  **/
  @Schema(description = "")
  public List<ModelGameComponent> getGameComponents() {
    return gameComponents;
  }
  public void setGameComponents(List<ModelGameComponent> gameComponents) {
    this.gameComponents = gameComponents;
  }
  public ModelGameDefinition id(Integer id) {
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
  public ModelGameDefinition increaseSpeedEnergyCost(Integer increaseSpeedEnergyCost) {
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
  public ModelGameDefinition increaseSpeedPercentage(Integer increaseSpeedPercentage) {
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
  public ModelGameDefinition label(String label) {
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
  public ModelGameDefinition life(Integer life) {
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
  public ModelGameDefinition luchadorSize(Integer luchadorSize) {
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
  public ModelGameDefinition maxFireAmount(Integer maxFireAmount) {
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
  public ModelGameDefinition maxFireCooldown(Integer maxFireCooldown) {
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
  public ModelGameDefinition maxFireDamage(Integer maxFireDamage) {
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
  public ModelGameDefinition maxLevel(Integer maxLevel) {
    this.maxLevel = maxLevel;
    return this;
  }

  

  /**
  * Get maxLevel
  * @return maxLevel
  **/
  @Schema(description = "")
  public Integer getMaxLevel() {
    return maxLevel;
  }
  public void setMaxLevel(Integer maxLevel) {
    this.maxLevel = maxLevel;
  }
  public ModelGameDefinition maxParticipants(Integer maxParticipants) {
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
  public ModelGameDefinition minFireAmount(Integer minFireAmount) {
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
  public ModelGameDefinition minFireDamage(Integer minFireDamage) {
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
  public ModelGameDefinition minLevel(Integer minLevel) {
    this.minLevel = minLevel;
    return this;
  }

  

  /**
  * Get minLevel
  * @return minLevel
  **/
  @Schema(description = "")
  public Integer getMinLevel() {
    return minLevel;
  }
  public void setMinLevel(Integer minLevel) {
    this.minLevel = minLevel;
  }
  public ModelGameDefinition minParticipants(Integer minParticipants) {
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
  public ModelGameDefinition moveSpeed(Integer moveSpeed) {
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
  public ModelGameDefinition name(String name) {
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
  public ModelGameDefinition punchAngle(Integer punchAngle) {
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
  public ModelGameDefinition punchCoolDown(Integer punchCoolDown) {
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
  public ModelGameDefinition punchDamage(Integer punchDamage) {
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
  public ModelGameDefinition radarAngle(Integer radarAngle) {
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
  public ModelGameDefinition radarRadius(Integer radarRadius) {
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
  public ModelGameDefinition recycledLuchadorEnergyRestore(Integer recycledLuchadorEnergyRestore) {
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
  public ModelGameDefinition respawnAngle(Integer respawnAngle) {
    this.respawnAngle = respawnAngle;
    return this;
  }

  

  /**
  * Get respawnAngle
  * @return respawnAngle
  **/
  @Schema(description = "")
  public Integer getRespawnAngle() {
    return respawnAngle;
  }
  public void setRespawnAngle(Integer respawnAngle) {
    this.respawnAngle = respawnAngle;
  }
  public ModelGameDefinition respawnCooldown(Integer respawnCooldown) {
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
  public ModelGameDefinition respawnGunAngle(Integer respawnGunAngle) {
    this.respawnGunAngle = respawnGunAngle;
    return this;
  }

  

  /**
  * Get respawnGunAngle
  * @return respawnGunAngle
  **/
  @Schema(description = "")
  public Integer getRespawnGunAngle() {
    return respawnGunAngle;
  }
  public void setRespawnGunAngle(Integer respawnGunAngle) {
    this.respawnGunAngle = respawnGunAngle;
  }
  public ModelGameDefinition respawnX(Integer respawnX) {
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
  public ModelGameDefinition respawnY(Integer respawnY) {
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
  public ModelGameDefinition restoreEnergyperSecond(Integer restoreEnergyperSecond) {
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
  public ModelGameDefinition sceneComponents(List<ModelSceneComponent> sceneComponents) {
    this.sceneComponents = sceneComponents;
    return this;
  }

  public ModelGameDefinition addSceneComponentsItem(ModelSceneComponent sceneComponentsItem) {
    if (this.sceneComponents == null) {
      this.sceneComponents = new ArrayList<ModelSceneComponent>();
    }
    this.sceneComponents.add(sceneComponentsItem);
    return this;
  }

  /**
  * Get sceneComponents
  * @return sceneComponents
  **/
  @Schema(description = "")
  public List<ModelSceneComponent> getSceneComponents() {
    return sceneComponents;
  }
  public void setSceneComponents(List<ModelSceneComponent> sceneComponents) {
    this.sceneComponents = sceneComponents;
  }
  public ModelGameDefinition sortOrder(Integer sortOrder) {
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
  public ModelGameDefinition suggestedCodes(List<ModelCode> suggestedCodes) {
    this.suggestedCodes = suggestedCodes;
    return this;
  }

  public ModelGameDefinition addSuggestedCodesItem(ModelCode suggestedCodesItem) {
    if (this.suggestedCodes == null) {
      this.suggestedCodes = new ArrayList<ModelCode>();
    }
    this.suggestedCodes.add(suggestedCodesItem);
    return this;
  }

  /**
  * Get suggestedCodes
  * @return suggestedCodes
  **/
  @Schema(description = "")
  public List<ModelCode> getSuggestedCodes() {
    return suggestedCodes;
  }
  public void setSuggestedCodes(List<ModelCode> suggestedCodes) {
    this.suggestedCodes = suggestedCodes;
  }
  public ModelGameDefinition turnGunSpeed(Integer turnGunSpeed) {
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
  public ModelGameDefinition turnSpeed(Integer turnSpeed) {
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
  public ModelGameDefinition type(String type) {
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
  public ModelGameDefinition unblockLevel(Integer unblockLevel) {
    this.unblockLevel = unblockLevel;
    return this;
  }

  

  /**
  * Get unblockLevel
  * @return unblockLevel
  **/
  @Schema(description = "")
  public Integer getUnblockLevel() {
    return unblockLevel;
  }
  public void setUnblockLevel(Integer unblockLevel) {
    this.unblockLevel = unblockLevel;
  }
  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ModelGameDefinition modelGameDefinition = (ModelGameDefinition) o;
    return Objects.equals(this.arenaHeight, modelGameDefinition.arenaHeight) &&
        Objects.equals(this.arenaWidth, modelGameDefinition.arenaWidth) &&
        Objects.equals(this.buletSpeed, modelGameDefinition.buletSpeed) &&
        Objects.equals(this.bulletSize, modelGameDefinition.bulletSize) &&
        Objects.equals(this.codes, modelGameDefinition.codes) &&
        Objects.equals(this.description, modelGameDefinition.description) &&
        Objects.equals(this.duration, modelGameDefinition.duration) &&
        Objects.equals(this.energy, modelGameDefinition.energy) &&
        Objects.equals(this.fireEnergyCost, modelGameDefinition.fireEnergyCost) &&
        Objects.equals(this.fps, modelGameDefinition.fps) &&
        Objects.equals(this.gameComponents, modelGameDefinition.gameComponents) &&
        Objects.equals(this.id, modelGameDefinition.id) &&
        Objects.equals(this.increaseSpeedEnergyCost, modelGameDefinition.increaseSpeedEnergyCost) &&
        Objects.equals(this.increaseSpeedPercentage, modelGameDefinition.increaseSpeedPercentage) &&
        Objects.equals(this.label, modelGameDefinition.label) &&
        Objects.equals(this.life, modelGameDefinition.life) &&
        Objects.equals(this.luchadorSize, modelGameDefinition.luchadorSize) &&
        Objects.equals(this.maxFireAmount, modelGameDefinition.maxFireAmount) &&
        Objects.equals(this.maxFireCooldown, modelGameDefinition.maxFireCooldown) &&
        Objects.equals(this.maxFireDamage, modelGameDefinition.maxFireDamage) &&
        Objects.equals(this.maxLevel, modelGameDefinition.maxLevel) &&
        Objects.equals(this.maxParticipants, modelGameDefinition.maxParticipants) &&
        Objects.equals(this.minFireAmount, modelGameDefinition.minFireAmount) &&
        Objects.equals(this.minFireDamage, modelGameDefinition.minFireDamage) &&
        Objects.equals(this.minLevel, modelGameDefinition.minLevel) &&
        Objects.equals(this.minParticipants, modelGameDefinition.minParticipants) &&
        Objects.equals(this.moveSpeed, modelGameDefinition.moveSpeed) &&
        Objects.equals(this.name, modelGameDefinition.name) &&
        Objects.equals(this.punchAngle, modelGameDefinition.punchAngle) &&
        Objects.equals(this.punchCoolDown, modelGameDefinition.punchCoolDown) &&
        Objects.equals(this.punchDamage, modelGameDefinition.punchDamage) &&
        Objects.equals(this.radarAngle, modelGameDefinition.radarAngle) &&
        Objects.equals(this.radarRadius, modelGameDefinition.radarRadius) &&
        Objects.equals(this.recycledLuchadorEnergyRestore, modelGameDefinition.recycledLuchadorEnergyRestore) &&
        Objects.equals(this.respawnAngle, modelGameDefinition.respawnAngle) &&
        Objects.equals(this.respawnCooldown, modelGameDefinition.respawnCooldown) &&
        Objects.equals(this.respawnGunAngle, modelGameDefinition.respawnGunAngle) &&
        Objects.equals(this.respawnX, modelGameDefinition.respawnX) &&
        Objects.equals(this.respawnY, modelGameDefinition.respawnY) &&
        Objects.equals(this.restoreEnergyperSecond, modelGameDefinition.restoreEnergyperSecond) &&
        Objects.equals(this.sceneComponents, modelGameDefinition.sceneComponents) &&
        Objects.equals(this.sortOrder, modelGameDefinition.sortOrder) &&
        Objects.equals(this.suggestedCodes, modelGameDefinition.suggestedCodes) &&
        Objects.equals(this.turnGunSpeed, modelGameDefinition.turnGunSpeed) &&
        Objects.equals(this.turnSpeed, modelGameDefinition.turnSpeed) &&
        Objects.equals(this.type, modelGameDefinition.type) &&
        Objects.equals(this.unblockLevel, modelGameDefinition.unblockLevel);
  }

  @Override
  public int hashCode() {
    return java.util.Objects.hash(arenaHeight, arenaWidth, buletSpeed, bulletSize, codes, description, duration, energy, fireEnergyCost, fps, gameComponents, id, increaseSpeedEnergyCost, increaseSpeedPercentage, label, life, luchadorSize, maxFireAmount, maxFireCooldown, maxFireDamage, maxLevel, maxParticipants, minFireAmount, minFireDamage, minLevel, minParticipants, moveSpeed, name, punchAngle, punchCoolDown, punchDamage, radarAngle, radarRadius, recycledLuchadorEnergyRestore, respawnAngle, respawnCooldown, respawnGunAngle, respawnX, respawnY, restoreEnergyperSecond, sceneComponents, sortOrder, suggestedCodes, turnGunSpeed, turnSpeed, type, unblockLevel);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ModelGameDefinition {\n");
    
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
    sb.append("    maxLevel: ").append(toIndentedString(maxLevel)).append("\n");
    sb.append("    maxParticipants: ").append(toIndentedString(maxParticipants)).append("\n");
    sb.append("    minFireAmount: ").append(toIndentedString(minFireAmount)).append("\n");
    sb.append("    minFireDamage: ").append(toIndentedString(minFireDamage)).append("\n");
    sb.append("    minLevel: ").append(toIndentedString(minLevel)).append("\n");
    sb.append("    minParticipants: ").append(toIndentedString(minParticipants)).append("\n");
    sb.append("    moveSpeed: ").append(toIndentedString(moveSpeed)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    punchAngle: ").append(toIndentedString(punchAngle)).append("\n");
    sb.append("    punchCoolDown: ").append(toIndentedString(punchCoolDown)).append("\n");
    sb.append("    punchDamage: ").append(toIndentedString(punchDamage)).append("\n");
    sb.append("    radarAngle: ").append(toIndentedString(radarAngle)).append("\n");
    sb.append("    radarRadius: ").append(toIndentedString(radarRadius)).append("\n");
    sb.append("    recycledLuchadorEnergyRestore: ").append(toIndentedString(recycledLuchadorEnergyRestore)).append("\n");
    sb.append("    respawnAngle: ").append(toIndentedString(respawnAngle)).append("\n");
    sb.append("    respawnCooldown: ").append(toIndentedString(respawnCooldown)).append("\n");
    sb.append("    respawnGunAngle: ").append(toIndentedString(respawnGunAngle)).append("\n");
    sb.append("    respawnX: ").append(toIndentedString(respawnX)).append("\n");
    sb.append("    respawnY: ").append(toIndentedString(respawnY)).append("\n");
    sb.append("    restoreEnergyperSecond: ").append(toIndentedString(restoreEnergyperSecond)).append("\n");
    sb.append("    sceneComponents: ").append(toIndentedString(sceneComponents)).append("\n");
    sb.append("    sortOrder: ").append(toIndentedString(sortOrder)).append("\n");
    sb.append("    suggestedCodes: ").append(toIndentedString(suggestedCodes)).append("\n");
    sb.append("    turnGunSpeed: ").append(toIndentedString(turnGunSpeed)).append("\n");
    sb.append("    turnSpeed: ").append(toIndentedString(turnSpeed)).append("\n");
    sb.append("    type: ").append(toIndentedString(type)).append("\n");
    sb.append("    unblockLevel: ").append(toIndentedString(unblockLevel)).append("\n");
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
