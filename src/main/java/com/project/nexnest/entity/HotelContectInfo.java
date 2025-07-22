package com.project.nexnest.entity;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;



@Embeddable
public class HotelContectInfo {
    private String address;
    private String phoneNumber;
    private String location;
    private String email;
    @Id
    private Long id;





    ;
}
