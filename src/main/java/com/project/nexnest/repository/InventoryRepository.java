package com.project.nexnest.repository;

import com.project.nexnest.entity.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InventoryRepository  extends JpaRepository<Inventory, Long> {
}
