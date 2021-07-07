package com.deliveryScheduler.demo.service;

import com.deliveryScheduler.demo.database.entity.Slot;
import com.deliveryScheduler.demo.dto.OrderDTO;
import com.deliveryScheduler.demo.enums.SchedulerStrategyEnum;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Ankit Dwivedi
 * @version 1.0, 07/07/21
 **/
@Service
public interface SchedulerStrategy {

    SchedulerStrategyEnum getName();

    void scheduleOrder(List<OrderDTO> orderDTO, Slot preferredSlot);
}
