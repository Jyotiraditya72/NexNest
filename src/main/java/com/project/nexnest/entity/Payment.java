package com.project.nexnest.entity;

import com.project.nexnest.entity.enums.PaymentStatus;
import jakarta.persistence.*;

import java.math.BigDecimal;
@Entity
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true,nullable = false)
    private String transactiomId;


    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PaymentStatus paymentStatus;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal amount;




}
