package com.company.assets.locations;

import com.company.assets.assets.AssetEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "locations")
public class LocationEntity {

   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Id
   @Column(name = "location_id", nullable = false)
   private int locationID;
   @Column(name = "description", nullable = false)
   private String description;
   @JsonIgnore
   @OneToMany(mappedBy="locationEntity")
   private Set<AssetEntity> assets;

   public LocationEntity() {}

   public LocationEntity(String description) {
      this.description = description;
   }

   public LocationEntity(int locationID, String description) {
      this.locationID = locationID;
      this.description = description;
   }


   public int getLocationID() {
      return locationID;
   }

   public void setLocationID(int id) {
      this.locationID = id;
   }

   public String getDescription() {
      return description;
   }

   public void setDescription(String description) {
      this.description = description;
   }

   public Set<AssetEntity> getAssets() {
      return assets;
   }

   public void setAssets(Set<AssetEntity> assets) {
      this.assets = assets;
   }

   @Override
   public String toString() {
      return "LocationEntity{" +
              "id=" + locationID +
              ", description='" + description + '\'' +
              '}';
   }
}
