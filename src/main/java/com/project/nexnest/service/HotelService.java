package com.project.nexnest.service;

import com.project.nexnest.dto.HotelDto;
import org.springframework.http.ResponseEntity;

public interface HotelService  {

    HotelDto createNewHotel(HotelDto hotelDto);
HotelDto getHotelById(Long id);


    HotelDto updateHotelById(long hotelId, HotelDto hotelDto);

    void deleteHotelById(Long hotelId);

    void activateHotel(Long hotelId);
}
