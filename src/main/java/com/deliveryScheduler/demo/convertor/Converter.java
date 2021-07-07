package com.deliveryScheduler.demo.convertor;

import com.deliveryScheduler.demo.database.entity.DeliveryPartners;
import com.deliveryScheduler.demo.database.entity.OrderDelivery;
import com.deliveryScheduler.demo.database.entity.Orders;
import com.deliveryScheduler.demo.database.entity.Slot;
import com.deliveryScheduler.demo.dto.AvailableVehicleDTO;
import com.deliveryScheduler.demo.dto.OrderDTO;


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
}
