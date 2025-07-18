package com.project.nexnest.dto;

import com.project.nexnest.entity.Hotel;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

import java.math.BigDecimal;
@Data
public class RoomDto {

    private Long id;
    private String type;
    private BigDecimal basePrice;
    private String photos;
    private String amenities;
    private Integer totalCount;
    private Integer capacity;
}
