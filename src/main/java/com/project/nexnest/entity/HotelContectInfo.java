package com.project.nexnest.entity;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Entity;


@Embeddable
public class HotelContectInfo {
    private String address;
    private String phoneNumber;
    private String location;
    private String email;
            ;
}
