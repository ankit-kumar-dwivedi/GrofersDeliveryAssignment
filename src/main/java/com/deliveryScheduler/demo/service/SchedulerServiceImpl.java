package com.deliveryScheduler.demo.service;

import com.deliveryScheduler.demo.database.entity.AbstractEntity;
import com.deliveryScheduler.demo.database.entity.DeliveryPartners;
import com.deliveryScheduler.demo.database.entity.OrderDelivery;
import com.deliveryScheduler.demo.database.entity.Slot;
import com.deliveryScheduler.demo.dto.ScheduleOrdersRequestDTO;
import com.deliveryScheduler.demo.dto.ScheduledOrdersResponseDTO;
import com.deliveryScheduler.demo.enums.SchedulerStrategyEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

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
        List<ScheduledOrdersResponseDTO> list = new ArrayList<>();
        List<OrderDelivery> orderDeliveries = orderDeliveryService.findAllOrdersForSlot(slotNumber);
        Map<DeliveryPartners, List<OrderDelivery>> deliveryPartnersListMap =
                orderDeliveries.stream().collect(Collectors.groupingBy(OrderDelivery::getDeliveryPartner));
        deliveryPartnersListMap.forEach((partner,orderDeliveryList)->{
            ScheduledOrdersResponseDTO responseDTO = new ScheduledOrdersResponseDTO();
            responseDTO.setDeliveryPartnerId(partner.getId());
            responseDTO.setVehicleType(partner.getVehicle().getVehicleType().name().toLowerCase());
            List<Long> orderIds = orderDeliveryList
                    .stream()
                    .map(OrderDelivery::getOrder)
                    .map(AbstractEntity::getId)
                    .collect(Collectors.toList());
            responseDTO.setListOrderIdsAssigned(orderIds);
        });
        return list;
    }

}
