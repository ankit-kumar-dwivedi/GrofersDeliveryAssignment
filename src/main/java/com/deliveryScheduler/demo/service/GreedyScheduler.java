package com.deliveryScheduler.demo.service;

import com.deliveryScheduler.demo.convertor.Converter;
import com.deliveryScheduler.demo.database.entity.*;
import com.deliveryScheduler.demo.database.repository.*;
import com.deliveryScheduler.demo.dto.AvailableVehicleDTO;
import com.deliveryScheduler.demo.dto.OrderDTO;
import com.deliveryScheduler.demo.enums.SchedulerStrategyEnum;
import com.deliveryScheduler.demo.enums.VehicleTypeEnum;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Ankit Dwivedi
 * @version 1.0, 07/07/21
 **/
@Service
public class GreedyScheduler implements SchedulerStrategy {

    private List<AvailableVehicleDTO> availablePartners = new ArrayList<>();
    private Slot lastUsedSlot = null;
    private Long remainingSlotCapacity = null;

    @Autowired
    private DeliveryPartnerRepository deliveryPartnerRepository;

    @Autowired
    private VehicleSlotRepository vehicleSlotRepository;

    @Autowired
    private SlotRepository slotRepository;

    @Autowired
    private OrdersRepository ordersRepository;

    @Autowired
    private OrderDeliveryRepository orderDeliveryRepository;

    @Override
    public SchedulerStrategyEnum getName() {
        return SchedulerStrategyEnum.GREEDY;
    }

    @Override
    public void scheduleOrder(List<OrderDTO> orderDTO, Slot preferredSlot) {
        // these are our first orders
        if (lastUsedSlot == null || lastUsedSlot.getId() < preferredSlot.getId()) {
            initSlotConstants(preferredSlot);
        }
        //sorted according to wt in decreasing order
        orderDTO.sort(Collections.reverseOrder(Comparator.comparingLong(OrderDTO::getOrderWeight)));
        Set<VehicleTypeEnum> vehiclesAllowedForSlot = CollectionUtils.emptyIfNull(
                vehicleSlotRepository.findBySlotIdAndDeletedFalse(preferredSlot.getId())
        )
                .stream()
                .map(VehicleSlot::getVehicle)
                .map(DeliveryVehicles::getVehicleType)
                .collect(Collectors.toSet());
        List<OrderDTO> unassignedOrders = new ArrayList<>();
        assignPartnerToOrders(orderDTO, vehiclesAllowedForSlot, unassignedOrders, preferredSlot);
        // remove partially used vehicles as they are assigned now
        availablePartners.removeIf(p -> p.getRemainingCapacity() < p.getMaxCapacity());
        while (CollectionUtils.isNotEmpty(unassignedOrders) && preferredSlot != null) {
            // try reassigning on next slot
            preferredSlot = getNextSlot(preferredSlot);
            unassignedOrders = new ArrayList<>();
            if (preferredSlot != null) {
                assignPartnerToOrders(orderDTO, vehiclesAllowedForSlot, unassignedOrders, preferredSlot);
                availablePartners.removeIf(p -> p.getRemainingCapacity() < p.getMaxCapacity());
            }
        }
    }

    private Slot getNextSlot(Slot preferredSlot) {
       return slotRepository.findFirstByIdGreaterThan(preferredSlot.getId());
    }

    private void initSlotConstants(Slot preferredSlot) {
        List<DeliveryPartners> deliveryPartners = deliveryPartnerRepository.findAll();
        availablePartners = deliveryPartners
                .stream()
                .map(Converter::convertToAvailableVehicleDTO)
                .sorted(Comparator.comparingLong(AvailableVehicleDTO::getRemainingCapacity))
                .collect(Collectors.toList());
        lastUsedSlot = preferredSlot;
        remainingSlotCapacity = lastUsedSlot.getCapacity();
    }

    private void assignPartnerToOrders(List<OrderDTO> ordersDTOList, Set<VehicleTypeEnum> vehiclesAllowedForSlot, List<OrderDTO> unassignedOrders, Slot preferredSlot) {
        for (OrderDTO orderDTO : ordersDTOList) {
            boolean assigned = false;
            for (int i = 0; i < availablePartners.size(); i++) {
                if (isAssignmentValid(vehiclesAllowedForSlot, orderDTO, i)) {
                    // assign order
                    AvailableVehicleDTO availableVehicleDTO = availablePartners.get(i);
                    availablePartners.get(i).setRemainingCapacity(availablePartners.get(i).getRemainingCapacity() - orderDTO.getOrderWeight());
                    if (availablePartners.get(i).getRemainingCapacity() == 0) {
                        availablePartners.remove(i);// replace with index
                    }
                    // get order entity
                    Orders order = Converter.convertToOrder(orderDTO);
                    // order delivery entity
                    order = ordersRepository.save(order);
                    OrderDelivery orderDelivery = getOrderDelivery(order, availableVehicleDTO, preferredSlot);
                    orderDelivery = orderDeliveryRepository.save(orderDelivery);
                    remainingSlotCapacity -= orderDTO.getOrderWeight();
                    assigned = true;
                    break;
                }
            }
            if (!assigned) {
                unassignedOrders.add(orderDTO);
            }
        }
    }

    private OrderDelivery getOrderDelivery(Orders order, AvailableVehicleDTO availableVehicleDTO, Slot preferredSlot) {
        DeliveryPartners deliveryPartner = deliveryPartnerRepository.findByIdAndDeletedFalse(availableVehicleDTO.getPartnerId());
        return Converter.convertToOrderDelivery(order, deliveryPartner, preferredSlot);
    }

    private boolean isAssignmentValid(Set<VehicleTypeEnum> vehiclesAllowedForSlot, OrderDTO dto, int i) {
        return vehiclesAllowedForSlot.contains(availablePartners.get(i).getVehicleType()) && availablePartners.get(i).getRemainingCapacity() >= dto.getOrderWeight()
                && remainingSlotCapacity >= dto.getOrderWeight();
    }

}
