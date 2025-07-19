package com.project.nexnest.service;

import com.project.nexnest.dto.HotelDto;
import com.project.nexnest.dto.HotelSearchRequest;
import com.project.nexnest.entity.Room;
import org.springframework.data.domain.Page;

public interface InventoryService {


    void initializeRoomForAYear(Room room);

    void deleteFutureInventories(Room room);

    Page<HotelDto> searchHotels(HotelSearchRequest hotelSearchRequest);
}
