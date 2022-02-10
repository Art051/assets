package com.company.assets.locations;

import javax.persistence.*;

@Entity
@Table(name = "locations")
public class LocationEntity {

   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Id
   @Column(name = "location_id", nullable = false)
   private int locationID;
   @Column(name = "description", nullable = false)
   private String description;

//   @OneToMany(mappedBy="locationEntity")
//   private Set<AssetEntity> assets;

   public LocationEntity() {
   }

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


   @Override
   public String toString() {
      return "LocationEntity{" +
              "id=" + locationID +
              ", description='" + description + '\'' +
              '}';
   }
}
