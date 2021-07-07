package com.deliveryScheduler.demo.database.repository;

import com.deliveryScheduler.demo.database.entity.DeliveryVehicles;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Ankit Dwivedi
 * @version 1.0, 07/07/21
 **/
public interface DeliveryVehiclesRepository extends JpaRepository<DeliveryVehicles, Long> {
}
