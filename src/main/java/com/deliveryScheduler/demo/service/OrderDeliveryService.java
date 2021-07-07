package com.deliveryScheduler.demo.service;

import com.deliveryScheduler.demo.database.entity.OrderDelivery;
import com.deliveryScheduler.demo.database.repository.OrderDeliveryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Ankit Dwivedi
 * @version 1.0, 07/07/21
 **/

@Service
public class OrderDeliveryService {
    @Autowired
    private OrderDeliveryRepository orderDeliveryRepository;

    public List<OrderDelivery> findAllOrdersForSlot(Long slotNumber) {
        return orderDeliveryRepository.findAllBySlotIdAndDeletedFalse(slotNumber);
    }
}
