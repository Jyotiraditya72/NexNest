package com.project.nexnest.service;

import com.project.nexnest.dto.HotelDto;
import com.project.nexnest.dto.HotelSearchRequest;
import com.project.nexnest.entity.Hotel;
import com.project.nexnest.entity.Inventory;
import com.project.nexnest.entity.Room;
import com.project.nexnest.repository.InventoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Slf4j
@Service
@RequiredArgsConstructor
public class InventoryServiceImpl implements InventoryService {
    private final InventoryRepository inventoryRepository;
    private final ModelMapper modelMapper;

    @Override
    public void initializeRoomForAYear(Room room) {
        LocalDate today = LocalDate.now();
        LocalDate endday = today.minusDays(1);
        for (; !today.isAfter(endday); today = endday.plusDays(1)) {
            Inventory inventory = Inventory.builder()
                    .hotel(room.getHotel())
                    .room(room)
                    .bookedCount(0)
                    .reservedCount(0)
                    .city(room.getHotel().getCity())
                    .date(today)
                    .price(room.getBasePrice())
                    .surgeFactor(BigDecimal.ONE)
                    .totalCount(room.getTotalCount())
                    .closed(false)
                    .build();

            inventoryRepository.save(inventory);
        }
    }

    @Override
    public void deleteAllInventories(Room room) {
        log.info("Deleting all inventories of room {}", room.getId());
     LocalDate today = LocalDate.now();
     inventoryRepository.deleteByDateAfterAndRoom(today,room);
    }

    @Override
    public Page<HotelDto> searchHotels(HotelSearchRequest hotelSearchRequest) {
        log.info("Searching hotels for {} city,from {} to {}", hotelSearchRequest.getCity(),hotelSearchRequest.getStartDate(),hotelSearchRequest.getEndDate());
        Pageable pageable= (Pageable) PageRequest.of(hotelSearchRequest.getPage(),hotelSearchRequest.getSize());
       long dataCount= ChronoUnit.DAYS.between(hotelSearchRequest.getStartDate(),hotelSearchRequest.getEndDate())+1;

       Page<Hotel> hotelPage=inventoryRepository.findHotelsWithAvailableInventory(hotelSearchRequest.getCity(),
               hotelSearchRequest.getStartDate()
       ,hotelSearchRequest.getEndDate(),hotelSearchRequest.getRoomsCount(),
             dataCount, pageable);
        return hotelPage.map((element) -> modelMapper.map(element, HotelDto.class));
    }
}
