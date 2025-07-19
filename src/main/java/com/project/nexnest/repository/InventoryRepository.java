package com.project.nexnest.repository;

import com.project.nexnest.entity.Hotel;
import com.project.nexnest.entity.Inventory;
import com.project.nexnest.entity.Room;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import org.springframework.data.domain.Pageable;
import java.time.LocalDate;

public interface InventoryRepository  extends JpaRepository<Inventory, Long> {


    void deleteByDateAfterAndRoom(LocalDate today, Room room);

    @Query("""
       SELECT DISTINCT i.hotel
            FROM Inventory i
            WHERE i.city=:city
            AND i.date BETWEEN :startDate and:endDate
            AND (i.totalCount-i.bookedCount>= :roomsCount)
            GROUP by i.hotel,i.room
            HAVING count( i.date)= :dateCount
""")
    Page<Hotel> findHotelsWithAvailableInventory(
            @Param("city") String city,
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate,
            @Param("roomCount") Integer roomsCount,
            @Param("dataCount") Long dataCount
             ,Pageable pageable

    );
}
