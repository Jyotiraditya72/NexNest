package com.project.nexnest.service;

import com.project.nexnest.dto.HotelDto;
import com.project.nexnest.dto.HotelInfoDto;
import com.project.nexnest.dto.RoomDto;
import com.project.nexnest.entity.Hotel;
import com.project.nexnest.entity.Room;
import com.project.nexnest.exception.ResourceNotFoundException;
import com.project.nexnest.repository.HotelRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.ResourceAccessException;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor

public class HotelServiceImpl implements HotelService {
 private final HotelRepository hotelRepository;
 private final ModelMapper modelMapper;
    private final InventoryService inventoryService;


    @Override
   public HotelDto createNewHotel(HotelDto hotelDto){
     log.info("creating a new hotel with name:{}", hotelDto.getName());
     Hotel hotel = modelMapper.map(hotelDto,Hotel.class);
        hotel.setActive(false);
     hotelRepository.save(hotel);
     return modelMapper.map(hotel,HotelDto.class);
    }
    @Override
    public HotelDto getHotelById(Long id){
     log.info("getting hotel with id:{}", id);
     Hotel hotel = hotelRepository.findById(id)
             .orElseThrow(()->new ResourceAccessException("Hotel not found:"+id));
        return modelMapper.map(hotel,HotelDto.class);
    }

    @Override
    public HotelDto updateHotelById(long hotelId, HotelDto hotelDto) {
    return  null;
    }

    @Override
    public void deleteHotelById(Long hotelId) {
        if (!hotelRepository.existsById(hotelId)) {
            throw new ResourceNotFoundException("Hotel not found with id: " + hotelId);
        }
        hotelRepository.deleteById(hotelId);
    }



    @Override
    public void activateHotel(Long hotelId) {
     log.info("activating hotel with id:{}", hotelId);
     Hotel hotel=hotelRepository.findById(hotelId)
             .orElseThrow(()->new ResourceNotFoundException("Hotel not found with id: " + hotelId));
     hotel.setActive(true);
        hotelRepository.save(hotel);

        for (Room room : hotel.getRooms()) {
            inventoryService.initializeRoomForAYear(room);
        }
}

    @Override
    public HotelInfoDto getHotelInfoById(Long hotelId) {
        Hotel hotel=hotelRepository.findById(hotelId)
                .orElseThrow(()->new ResourceNotFoundException("Hotel not found with id: " + hotelId));
        hotel.setActive(true);
        List<RoomDto> rooms=hotel.getRooms()
                .stream()
                .map((element)->modelMapper.map(element,RoomDto.class))
                .toList();
        return new HotelInfoDto(modelMapper.map(hotel,HotelDto.class),rooms);
    }


}


