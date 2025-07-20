package com.project.nexnest.entity;

import jakarta.persistence.*;
import lombok.*;
import org.antlr.v4.runtime.misc.LogManager;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
@Entity
@Table(uniqueConstraints = @UniqueConstraint(
      name="unique_hotel_name_date",
      columnNames = { "hotel_id","room_id","date"}
))
@Data
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Inventory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="hotel_id",nullable = false)
    private Hotel hotel;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="room_id",nullable = false)
    private  Room room; ;


    @Column(nullable = false)
    private LocalDate date;

    @Column(nullable = false,columnDefinition = "INTEGER DEFAULT 0")
    private Integer bookedCount;

    @Column(nullable = false,columnDefinition = "INTEGER DEFAULT 0")
    private Integer reservedCount;

    @Column(nullable = false)
    private Integer totalCount;

    @Column(nullable = false,precision = 5,scale = 2)
    private BigDecimal surgeFactor;

    @Column(nullable = false,precision = 10,scale = 2)
    private BigDecimal price;

    @Column(nullable = false)
    private String city;

    @Column(nullable = false)
    private Boolean closed;

    @CreationTimestamp
    @Column(nullable=false)
    private LocalDateTime createdAt;
    @UpdateTimestamp
    @Column(nullable=false)
    private LocalDateTime updatedAt;


}




