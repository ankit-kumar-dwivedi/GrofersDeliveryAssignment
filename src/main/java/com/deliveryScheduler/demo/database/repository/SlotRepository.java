package com.deliveryScheduler.demo.database.repository;

import com.deliveryScheduler.demo.database.entity.Slot;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalTime;

/**
 * @author Ankit Dwivedi
 * @version 1.0, 06/07/21
 **/
public interface SlotRepository extends JpaRepository<Slot, Long> {

    Slot findByStartTimeGreaterThanEqualAndEndTimeLessThan(LocalTime startTime, LocalTime endTime);

    Slot findByIdAndDeletedFalse(Long Id);

    Slot findFirstByIdGreaterThan(Long id);

}
