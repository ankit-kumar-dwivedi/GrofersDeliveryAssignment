package com.deliveryScheduler.demo.database.repository;

import com.deliveryScheduler.demo.database.entity.VehicleSlot;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author Ankit Dwivedi
 * @version 1.0, 07/07/21
 **/
public interface VehicleSlotRepository extends JpaRepository<VehicleSlot, Long> {
    List<VehicleSlot> findBySlotIdAndDeletedFalse(Long slotId);
}
