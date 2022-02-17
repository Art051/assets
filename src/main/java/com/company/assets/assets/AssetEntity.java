package com.company.assets.assets;

import com.company.assets.locations.LocationEntity;
import com.company.assets.users.UserEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;

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
   @JsonIgnore
   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name="user_id")
   private UserEntity userEntity;
   @JsonIgnore
   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name="location_id")
   private LocationEntity locationEntity;

   public AssetEntity() {}

   public AssetEntity(String description, String make, String model, BigDecimal value, UserEntity userEntity, LocationEntity locationEntity) {
      this.description = description;
      this.make = make;
      this.model = model;
      this.value = value;
      this.userEntity = userEntity;
      this.locationEntity = locationEntity;
   }

   public int getAssetID() {
      return assetID;
   }

   public void setAssetID(int assetID) {
      this.assetID = assetID;
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

   public UserEntity getUserEntity() {
      return userEntity;
   }

   public void setUserEntity(UserEntity userEntity) {
      this.userEntity = userEntity;
   }

   public LocationEntity getLocationEntity() {
      return locationEntity;
   }

   public void setLocationEntity(LocationEntity locationEntity) {
      this.locationEntity = locationEntity;
   }

   @Override
   public String toString() {
      return "AssetEntity{" +
              "assetID=" + assetID +
              ", description='" + description + '\'' +
              ", make='" + make + '\'' +
              ", model='" + model + '\'' +
              ", value=" + value +
              ", userEntity=" + userEntity +
              ", locationEntity=" + locationEntity +
              '}';
   }
}