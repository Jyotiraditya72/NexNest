package com.project.nexnest.controller;

import com.project.nexnest.dto.RoomDto;
import com.project.nexnest.repository.HotelRepository;
import com.project.nexnest.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/hotels/{hotelId}/rooms")
@RequiredArgsConstructor
public class RoomController {
    private final RoomService roomService;

    @PostMapping
    public ResponseEntity<RoomDto> createNewRoom(@PathVariable Long hotelId, @RequestBody RoomDto roomDto) {
        roomService.createNewRoom(hotelId,roomDto);
        return new ResponseEntity<>(roomDto, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<RoomDto>> getAllRooms(@PathVariable Long hotelId) {
        return ResponseEntity.ok(roomService.getAllRoomsInHotels(hotelId));
    }

    @GetMapping("/{roomId}")
    public ResponseEntity<RoomDto> getRoomById(@PathVariable Long roomId) {
        return ResponseEntity.ok((RoomDto) roomService.getRoomById(roomId));
    }
@DeleteMapping("/{roomId}")

public ResponseEntity<RoomDto> deleteRoomById(@PathVariable Long roomId) {
    roomService.deleteRoomById(roomId);
    return ResponseEntity.noContent().build();
    }
}
