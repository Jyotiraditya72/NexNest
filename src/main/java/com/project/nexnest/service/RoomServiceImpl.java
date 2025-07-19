package com.project.nexnest.service;

import com.project.nexnest.dto.RoomDto;
import com.project.nexnest.entity.Hotel;
import com.project.nexnest.entity.Room;
import com.project.nexnest.exception.ResourceNotFoundException;
import com.project.nexnest.repository.HotelRepository;
import com.project.nexnest.repository.RoomRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class RoomServiceImpl implements RoomService {

    private final RoomRepository roomRepository;
    private final ModelMapper modelMapper;
    private final HotelRepository hotelRepository;
    private final InventoryService inventoryService;

    @Override
    public RoomDto createRoom(Long hotelId, RoomDto roomDto) {
        log.info("Creating a new room for hotelId: {}", hotelId);

        Hotel hotel = hotelRepository.findById(hotelId)
                .orElseThrow(() -> new ResourceNotFoundException("Hotel not found with ID: " + hotelId));

        Room room = modelMapper.map(roomDto, Room.class);
        room.setHotel(hotel);

        room = roomRepository.save(room);

        if (Boolean.TRUE.equals(hotel.getActive())) {
            inventoryService.initializeRoomForAYear(room);
        }

        return modelMapper.map(room, RoomDto.class);
    }

    @Override
    public RoomDto getRoomById(Long roomId) {
        log.info("Getting room by ID: {}", roomId);

        Room room = roomRepository.findById(roomId)
                .orElseThrow(() -> new ResourceNotFoundException("Room not found with ID: " + roomId));

        return modelMapper.map(room, RoomDto.class);
    }

    @Override
    public List<RoomDto> getRoomsByHotel(Long hotelId) {
        log.info("Getting all rooms for hotelId: {}", hotelId);

        Hotel hotel = hotelRepository.findById(hotelId)
                .orElseThrow(() -> new ResourceNotFoundException("Hotel not found with ID: " + hotelId));

        if (hotel.getRooms() == null || hotel.getRooms().isEmpty()) {
            return List.of();
        }

        return hotel.getRooms()
                .stream()
                .map(room -> modelMapper.map(room, RoomDto.class))
                .collect(Collectors.toList());
    }

    @Transactional
    @Override
    public void deleteRoomById(Long roomId) {
        log.info("Deleting room by ID: {}", roomId);

        Room room = roomRepository.findById(roomId)
                .orElseThrow(() -> new ResourceNotFoundException("Room not found with ID: " + roomId));

        // Delete related inventories
        inventoryService.deleteFutureInventories(room);

        // Delete room
        roomRepository.delete(room);
    }

    @Override
    public void createNewRoom(Long hotelId, RoomDto roomDto) {

    }

    @Override
    public List<RoomDto> getAllRoomsInHotels(Long hotelId) {
        return List.of();
    }
}


