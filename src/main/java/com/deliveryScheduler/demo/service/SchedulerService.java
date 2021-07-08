package com.deliveryScheduler.demo.service;

import com.deliveryScheduler.demo.dto.ScheduleOrdersRequestDTO;
import com.deliveryScheduler.demo.dto.ScheduledOrdersResponseDTO;

import java.util.List;

/**
 * @author Ankit Dwivedi
 * @version 1.0, 06/07/21
 **/
public interface SchedulerService {

    void scheduleOrders(ScheduleOrdersRequestDTO requestDTO);

    List<ScheduledOrdersResponseDTO> fetchScheduledOrders(Long slotNumber);

    List<ScheduledOrdersResponseDTO> fetchAllScheduledOrders();
}
