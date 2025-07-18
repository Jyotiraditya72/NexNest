package com.project.nexnest.repository;

import com.project.nexnest.entity.Inventory;
import com.project.nexnest.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;

public interface InventoryRepository  extends JpaRepository<Inventory, Long> {
    void deleteByDateAfterAndRoom(LocalDate today, Room room);

}
