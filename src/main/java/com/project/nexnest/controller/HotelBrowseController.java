package com.project.nexnest.controller;

import com.project.nexnest.dto.HotelDto;
import com.project.nexnest.dto.HotelSearchRequest;
import com.project.nexnest.repository.InventoryRepository;
import com.project.nexnest.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping
@RequiredArgsConstructor

public class HotelBrowseController {

    private final InventoryService inventoryService;
    @GetMapping("/search")
    public ResponseEntity<Page<HotelDto>> searchHotels(@RequestBody HotelSearchRequest hotelSearchRequest) {
        Page<HotelDto> page=inventoryService.searchHotels(hotelSearchRequest);
    return ResponseEntity.ok(page);
    }





}
