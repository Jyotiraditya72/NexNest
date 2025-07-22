package com.project.nexnest.dto;

import com.project.nexnest.entity.Hotel;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RoomDto {

    private Long id;
    private String type;
    private BigDecimal basePrice;
    private String photos;
    private String amenities;
    private Integer totalCount;
    private Integer capacity;
}
