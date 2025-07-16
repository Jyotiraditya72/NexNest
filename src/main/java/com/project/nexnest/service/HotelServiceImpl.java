package com.project.nexnest.service;

import com.project.nexnest.dto.HotelDto;
import com.project.nexnest.entity.Hotel;
import com.project.nexnest.exception.ResourseNotFoundException;
import com.project.nexnest.repository.HotelRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor

public class HotelServiceImpl implements HotelService {
 private final HotelRepository hotelRepository;
 private final ModelMapper modelMapper;
 @Override
   public HotelDto createNewHotel(HotelDto hotelDto){
     log.info("creating a new hotel with name:{}", hotelDto.getName());
     Hotel hotel = modelMapper.map(hotelDto,Hotel.class);
     hotelRepository.save(hotel);
     hotel.setActive(false);
     hotel=hotelRepository.save(hotel);

     return modelMapper.map(hotel,HotelDto.class);
    }
    @Override
    public HotelDto getHotelById(Long id){
     log.info("getting hotel with id:{}", id);
     Hotel hotel = hotelRepository.findById(id)
             .orElseThrow(()->new ResourseNotFoundException("Hotel not found:"+id));
        return modelMapper.map(hotel,Hotel.class);
    }

}
