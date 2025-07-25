package com.project.nexnest.entity;

import com.project.nexnest.entity.enums.Gender;
import jakarta.persistence.*;

import java.util.Set;
@Entity
public class Guest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    @Column(nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @ManyToMany

    private Set<Booking> bookings;





}
