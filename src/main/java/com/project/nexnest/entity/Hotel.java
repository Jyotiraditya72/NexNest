package com.project.nexnest.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "hotel")
@Data
public class Hotel {

 @Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)
 private long id;

 @Column(nullable = false)
 private String name;

 private String city;

 @ElementCollection
 @CollectionTable(name = "hotel_photos", joinColumns = @JoinColumn(name = "hotel_id"))
 @Column(name = "photo")
 private List<String> photos;

 @ElementCollection
 @CollectionTable(name = "hotel_amenities", joinColumns = @JoinColumn(name = "hotel_id"))
 @Column(name = "amenity")
 private List<String> amenities;

 @CreationTimestamp
 private LocalDateTime createdAt;

 @UpdateTimestamp
 private LocalDateTime updatedAt;

 @Embedded
 private HotelContectInfo contectInfo;

 @Column(nullable = false)
 private Boolean active;

 @OneToMany(mappedBy = "hotel", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
 private List<Room> rooms;
}
