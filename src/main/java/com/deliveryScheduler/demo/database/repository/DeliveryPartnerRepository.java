package com.deliveryScheduler.demo.database.repository;

import com.deliveryScheduler.demo.database.entity.DeliveryPartners;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Ankit Dwivedi
 * @version 1.0, 07/07/21
 **/
public interface DeliveryPartnerRepository extends JpaRepository<DeliveryPartners, Long> {

    DeliveryPartners findByIdAndDeletedFalse(Long id);
}
