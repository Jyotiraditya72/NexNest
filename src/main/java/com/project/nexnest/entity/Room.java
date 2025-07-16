package com.project.nexnest.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
@Entity
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="hotel_id",nullable=false)
    private Hotel hotel;

    @Column(nullable=false)
    private String type;

    @Column(nullable=false,precision=10,scale=2)
    private BigDecimal basePrice;

    @Column(columnDefinition = "TEXT[]")
    private String photos;

    @Column(columnDefinition = "TEXT[]")
    private String amenities;
    @Column(nullable=false)
    private Integer totalCount;

    @Column(nullable=false)
    private Integer capacity;

    @CreationTimestamp
    @Column(nullable=false)
    private LocalDateTime createdAt;
    @UpdateTimestamp
    @Column(nullable=false)
    private LocalDateTime updatedAt;

    @Embedded
    private HotelContectInfo contectInfo;

    @Column(nullable = false)
    private boolean active;

    public void setHotel(Hotel hotel) {
    }

    public Hotel getHotel() {
        return null;
    }

    public BigDecimal getBasePrice() {
        return null;
    }

    public Integer getTotalCount() {
        return null;
    }
}
