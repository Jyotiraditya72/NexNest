package com.project.nexnest.dto;

import com.project.nexnest.entity.HotelContectInfo;
import jakarta.persistence.Column;
import lombok.*;

@Data
@NoArgsConstructor
@Getter
@Setter
public class HotelDto {
    private long id;
    private String name;
    private String city;
    private String photos;
    private String amenities;
    private HotelContectInfo contectInfo;
    private Boolean active;
}
