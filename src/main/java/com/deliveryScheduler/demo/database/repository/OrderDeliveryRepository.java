package com.deliveryScheduler.demo.database.repository;

import com.deliveryScheduler.demo.database.entity.OrderDelivery;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author Ankit Dwivedi
 * @version 1.0, 07/07/21
 **/
public interface OrderDeliveryRepository extends JpaRepository<OrderDelivery, Long> {
    List<OrderDelivery> findAllBySlotIdAndDeletedFalse(Long slotId);
}
