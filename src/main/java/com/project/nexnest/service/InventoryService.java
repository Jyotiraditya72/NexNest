package com.project.nexnest.service;

import com.project.nexnest.dto.HotelPriceDto;
import com.project.nexnest.dto.HotelSearchRequest;
import com.project.nexnest.entity.Room;
import org.springframework.data.domain.Page;

public interface InventoryService {


    void initializeRoomForAYear(Room room);

    void deleteAllInventories(Room room);

    Page<HotelPriceDto> searchHotels(HotelSearchRequest hotelSearchRequest);
}
