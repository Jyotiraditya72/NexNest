package com.project.nexnest.repository;

import com.project.nexnest.entity.Hotel;
import com.project.nexnest.entity.Inventory;
import com.project.nexnest.entity.Room;
import jakarta.persistence.LockModeType;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
@Repository


public interface InventoryRepository  extends JpaRepository<Inventory, Long> {


    void deleteByDateAfterAndRoom(LocalDate today, Room room);

    @Query("""
      
       SELECT DISTINCT i.hotel
            FROM Inventory i
            WHERE i.city=:city
            AND i.date BETWEEN :startDate and:endDate
            AND (i.totalCount-i.bookedCount>= :roomsCount)
            GROUP by i.hotel,i.room
            HAVING count( i.date)= :dataCount
                   """)
    Page<Hotel> findHotelsWithAvailableInventory(
            @Param("city") String city,
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate,
            @Param("roomCount") Integer roomsCount,
            @Param("dataCount") Long dataCount
             ,Pageable pageable

    );
@Query("""
SELECT DISTINCT i
FROM Inventory i
WHERE i.room.id=:roomId
AND i.date BETWEEN :startDate and:endDate
AND i.closed=false 
AND (i.totalCount-i.bookedCount- i.reservedCount>= :roomsCount)
""")
@Lock(LockModeType.PESSIMISTIC_WRITE)
    List<Inventory> findAndLockAvailableInventory(
            @Param("roomId") Long roomId,
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate,
            @Param("roomsCount") Integer roomsCount
    );
}
