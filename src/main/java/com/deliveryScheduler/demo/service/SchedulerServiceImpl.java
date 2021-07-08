package com.deliveryScheduler.demo.service;

import com.deliveryScheduler.demo.convertor.Converter;
import com.deliveryScheduler.demo.database.entity.*;
import com.deliveryScheduler.demo.dto.ScheduleOrdersRequestDTO;
import com.deliveryScheduler.demo.dto.ScheduledOrdersResponseDTO;
import com.deliveryScheduler.demo.enums.SchedulerStrategyEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author Ankit Dwivedi
 * @version 1.0, 06/07/21
 **/
@Service
public class SchedulerServiceImpl implements  SchedulerService{

    @Autowired
    private SlotService slotService;

    @Autowired
    private OrderDeliveryService orderDeliveryService;

    @Autowired
    private SchedulerStrategyFactory schedulerStrategy;

    @Override
    public void scheduleOrders(ScheduleOrdersRequestDTO requestDTO) {
        Slot preferredSlot = null;
        // get slot by number
        if (requestDTO.getSlotNumber() == null) {
           preferredSlot = slotService.getSlotForCurrentTime();
        }
        else {
            preferredSlot = slotService.getSlotByNumber(requestDTO.getSlotNumber());
        }
        SchedulerStrategy strategy = schedulerStrategy.findStrategy(SchedulerStrategyEnum.GREEDY);
        strategy.scheduleOrder(requestDTO.getOrders(), preferredSlot);
    }

    @Override
    public List<ScheduledOrdersResponseDTO> fetchScheduledOrders(Long slotNumber) {
        List<OrderDelivery> orderDeliveries = orderDeliveryService.findAllOrdersForSlot(slotNumber);
        return Converter.convertToScheduledOrdersResponseDTOList(orderDeliveries, false);
    }

    @Override
    public List<ScheduledOrdersResponseDTO> fetchAllScheduledOrders() {
        List<OrderDelivery> orderDeliveries = orderDeliveryService.getAllOrderDeliveries();
        return Converter.convertToScheduledOrdersResponseDTOList(orderDeliveries, true);
    }

}
