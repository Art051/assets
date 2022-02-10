package com.company.assets.users;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class UserEntity {

   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Id
   @Column(name = "user_id", nullable = false)
   private int userID;
   @Column(name = "first_name", nullable = false)
   private String firstName;
   @Column(name = "last_name")
   private String lastName;

//   @OneToMany(mappedBy="userEntity")
//   private Set<AssetEntity> assets;

   public UserEntity() {
   }

   public UserEntity(String firstName, String lastName) {
      this.firstName = firstName;
      this.lastName = lastName;
   }

   public UserEntity(int userID, String firstName, String lastName) {
      this.userID = userID;
      this.firstName = firstName;
      this.lastName = lastName;
   }

   public int getUserID() {
      return userID;
   }

   public void setUserID(int id) {
      this.userID = id;
   }

   public String getFirstName() {
      return firstName;
   }

   public void setFirstName(String firstName) {
      this.firstName = firstName;
   }

   public String getLastName() {
      return lastName;
   }

   public void setLastName(String lastName) {
      this.lastName = lastName;
   }

   @Override
   public String toString() {
      return "UserEntity{" +
              "id=" + userID +
              ", firstName='" + firstName + '\'' +
              ", lastName='" + lastName + '\'' +
              '}';
   }
}
