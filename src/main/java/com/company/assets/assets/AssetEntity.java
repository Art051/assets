package com.company.assets.assets;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "assets")
public class AssetEntity {

   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Id
   @Column(name = "asset_id", nullable = false)
   private int assetID;
   @Column(name = "description", nullable = false)
   private String description;
   @Column(name = "make", length = 50)
   private String make;
   @Column(name = "model", length = 50)
   private String model;
   @Column(name = "value", nullable = false, precision = 2)
   private BigDecimal value;
   @Column(name = "user_id")
   private Integer userID;
   @Column(name = "location_id")
   private Integer locationID;

   /*
   @ManyToOne
   @JoinColumn(name="user_id", insertable = true, updatable = true)
   private UserEntity userEntity;

   @ManyToOne
   @JoinColumn(name="location_id", insertable = true, updatable = true)
   private LocationEntity locationEntity;

*/

   public AssetEntity() {

   }

   public AssetEntity(String description, String make, String model, BigDecimal value, Integer userID, Integer locationID) {
      this.description = description;
      this.make = make;
      this.model = model;
      this.value = value;
      this.userID = userID;
      this.locationID = locationID;
   }

   public AssetEntity(int assetID, String description, String make, String model, BigDecimal value, Integer userID, Integer locationID) {
      this.assetID = assetID;
      this.description = description;
      this.make = make;
      this.model = model;
      this.value = value;
      this.userID = userID;
      this.locationID = locationID;
   }

   public int getAssetID() {
      return assetID;
   }

   public void setAssetID(int id) {
      this.assetID = id;
   }

   public String getDescription() {
      return description;
   }

   public void setDescription(String description) {
      this.description = description;
   }

   public String getMake() {
      return make;
   }

   public void setMake(String make) {
      this.make = make;
   }

   public String getModel() {
      return model;
   }

   public void setModel(String model) {
      this.model = model;
   }

   public BigDecimal getValue() {
      return value;
   }

   public void setValue(BigDecimal value) {
      this.value = value;
   }

   public Integer getUserID() {
      return userID;
   }

   public void setUserID(Integer userId) {
      this.userID = userId;
   }

   public Integer getLocationID() {
      return locationID;
   }

   public void setLocationID(Integer locationId) {
      this.locationID = locationId;
   }

   @Override
   public String toString() {
      return "AssetEntity{" +
              "id=" + assetID +
              ", description='" + description + '\'' +
              ", make='" + make + '\'' +
              ", model='" + model + '\'' +
              ", value=" + value +
              ", userId=" + userID +
              ", locationId=" + locationID +
              '}';
   }
}
