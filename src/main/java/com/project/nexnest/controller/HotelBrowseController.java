package com.project.nexnest.controller;

import com.project.nexnest.dto.HotelDto;
import com.project.nexnest.dto.HotelInfoDto;
import com.project.nexnest.dto.HotelPriceDto;
import com.project.nexnest.dto.HotelSearchRequest;
import com.project.nexnest.repository.InventoryRepository;
import com.project.nexnest.service.HotelService;
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
    private final HotelService hotelService;

    @GetMapping("/search")
    public ResponseEntity<Page<HotelPriceDto>> searchHotels(@RequestBody HotelSearchRequest hotelSearchRequest) {
        Page<HotelPriceDto> page=inventoryService.searchHotels(hotelSearchRequest);
    return ResponseEntity.ok(page);
    }

    @GetMapping("/{hotelId}/info")
    public ResponseEntity<HotelInfoDto> getHotelInfo(@PathVariable Long hotelId) {
        return ResponseEntity.ok(hotelService.getHotelInfoById(hotelId));
    }





}
