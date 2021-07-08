package com.deliveryScheduler.demo.convertor;

import com.deliveryScheduler.demo.database.entity.DeliveryPartners;
import com.deliveryScheduler.demo.database.entity.OrderDelivery;
import com.deliveryScheduler.demo.database.entity.Orders;
import com.deliveryScheduler.demo.database.entity.Slot;
import com.deliveryScheduler.demo.dto.AvailableVehicleDTO;
import com.deliveryScheduler.demo.dto.OrderDTO;
import com.deliveryScheduler.demo.dto.ScheduledOrdersResponseDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Ankit Dwivedi
 * @version 1.0, 07/07/21
 **/
public class Converter {

    public static AvailableVehicleDTO convertToAvailableVehicleDTO(DeliveryPartners partners) {
        AvailableVehicleDTO dto = new AvailableVehicleDTO();
        dto.setPartnerId(partners.getId());
        dto.setRemainingCapacity(partners.getVehicle().getCapacity());
        dto.setVehicleType(partners.getVehicle().getVehicleType());
        dto.setMaxCapacity(partners.getVehicle().getCapacity());
        return dto;
    }

    public static Orders convertToOrder(OrderDTO orderDTO) {
        Orders entity = new Orders();
        entity.setWeight(orderDTO.getOrderWeight());
        entity.setId(orderDTO.getOrderId());
        return entity;
    }

    public static OrderDelivery convertToOrderDelivery(Orders order, DeliveryPartners deliveryPartner, Slot preferredSlot) {
        OrderDelivery entity = new OrderDelivery();
        entity.setDeliveryPartner(deliveryPartner);
        entity.setOrder(order);
        entity.setSlot(preferredSlot);
        return entity;
    }

    public static List<ScheduledOrdersResponseDTO> convertToScheduledOrdersResponseDTOList(List<OrderDelivery> orderDeliveries, boolean setSlotInfo) {
        List<ScheduledOrdersResponseDTO> list = new ArrayList<>();
        Map<DeliveryPartners, List<OrderDelivery>> deliveryPartnersListMap =
                orderDeliveries.stream().collect(Collectors.groupingBy(OrderDelivery::getDeliveryPartner));
        deliveryPartnersListMap.forEach((partner,orderDeliveryList)->{
            ScheduledOrdersResponseDTO responseDTO = new ScheduledOrdersResponseDTO();
            responseDTO.setDeliveryPartnerId(partner.getId());
            responseDTO.setVehicleType(partner.getVehicle().getVehicleType().name().toLowerCase());
            if (setSlotInfo) {
                responseDTO.setSlotNumber(orderDeliveryList.get(0).getSlot().getId());
            }
            List<Long> orderIds = orderDeliveryList
                    .stream()
                    .map(OrderDelivery::getOrder)
                    .map(Orders::getId)
                    .collect(Collectors.toList());
            responseDTO.setListOrderIdsAssigned(orderIds);
            list.add(responseDTO);
        });
        return list;
    }
}
