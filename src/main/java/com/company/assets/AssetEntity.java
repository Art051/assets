package com.company.assets;

import javax.persistence.*;

@Entity
@Table(name = "assets")
public class AssetEntity {

   @Id
   @Column(name = "id", nullable = false)
   private Integer id;
   @Column(nullable = false)
   private String description;
   @Column(name = "serial_num")
   private String serialNum;
   @Column(name = "price", nullable = false)
   private Double price;
   @Column(name = "location", nullable = false)
   private String location;
   @Column(name = "sub_location")
   private String subLocation;


   public AssetEntity (Integer id, String description, String brand, String model, String serialNum, double price, String location, String subLocation) {
      this.id = id;
      this.description = description;
      this.serialNum = serialNum;
      this.price = price;
      this.location = location;
      this.subLocation = subLocation;
   }

   public AssetEntity() {
   }

   public void setPrice(Double price) {
      this.price = price;
   }

   public Double getPrice() {
      return price;
   }

   public Integer getId() {
      return id;
   }

   public void setId (Integer id) {
      this.id = id;
   }

   public String getDescription() {
      return description;
   }

   public void setDescription(String description) {
      this.description = description;
   }

   public String getSerialNum() {
      return serialNum;
   }

   public void setSerialNum(String serialNum) {
      this.serialNum = serialNum;
   }

   public String getLocation() {
      return location;
   }

   public void setLocation(String location) {
      this.location = location;
   }

   public String getSubLocation() {
      return subLocation;
   }

   public void setSubLocation(String subLocation) {
      this.subLocation = subLocation;
   }


   @Override
   public String toString() {
      return "AssetEntity{" +
              "assetId=" + id +
              ", description='" + description + '\'' +
              ", serialNum='" + serialNum + '\'' +
              ", price=" + price +
              ", location='" + location + '\'' +
              ", subLocation='" + subLocation + '\'' +
              '}';
   }
}
