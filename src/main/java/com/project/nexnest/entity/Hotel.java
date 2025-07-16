package com.project.nexnest.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
@Entity
@Table(name="hotel")
public class Hotel {
 @Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)
 private long id;
 @Column(nullable = false)
 private String name;
 private String city;

 @Column(columnDefinition = "TEXT[]")
 private String photos;

 @Column(columnDefinition = "TEXT[]")
 private String amenities;

 @CreationTimestamp
 private LocalDateTime createdAt;
 @UpdateTimestamp
 private LocalDateTime updatedAt;

 @Embedded
 private HotelContectInfo contectInfo;

 @Column(nullable = false)
 private Boolean active=true;

 @OneToMany(mappedBy="hotel")
 private List<Room> rooms;

 @ManyToOne
 private User owner;

 public void setActive(boolean b) {
 }

 public Collection<Object> getRooms() {
  return null;
 }

 public String getCity() {
  return null;
 }

 public boolean getActive() {
  return false;
 }
}
