package com.project.nexnest.entity;

import com.project.nexnest.entity.enums.Role;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;
@Entity
@Table(name="app_user")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    private String name;


    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    private Set<Role> roles;


}
