package com.project.nexnest.dto;

import com.project.nexnest.entity.HotelContectInfo;
import jakarta.persistence.Column;
import lombok.Data;
import lombok.NonNull;
import org.antlr.v4.runtime.misc.NotNull;

@Data
public class HotelDto {
    private long id;
    @NotNull
    private String name;
    private String city;
    private String photos;
    private String amenities;
    private HotelContectInfo contectInfo;
    private Boolean active;
}
