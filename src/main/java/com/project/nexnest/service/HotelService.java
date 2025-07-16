package com.project.nexnest.service;

import com.project.nexnest.dto.HotelDto;

public interface HotelService  {

    HotelDto createNewHotel(HotelDto hotelDto);
HotelDto getHotelById(Long id);
}
