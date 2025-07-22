package com.project.nexnest.service;

import com.project.nexnest.dto.RoomDto;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@Transactional

public interface RoomService {
    RoomDto createRoom(Long hotelId, RoomDto roomDto); // Create a new room under a specific hotel
    RoomDto getRoomById(Long roomId); // Get a room by ID
    List<RoomDto> getRoomsByHotel(Long hotelId); // Get all rooms in a hotel
    void deleteRoomById(Long roomId); // Delete a room by ID

    void createNewRoom(Long hotelId, RoomDto roomDto);

    List<RoomDto> getAllRoomsInHotels(Long hotelId);
}
