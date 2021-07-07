package com.deliveryScheduler.demo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

/**
 * @author Ankit Dwivedi
 * @version 1.0, 07/07/21
 **/
@Data
public class ScheduledOrdersResponseDTO {
    @JsonProperty("vehicle_type")
    private String     vehicleType;
    @JsonProperty("delivery_partner_id")
    private Long       deliveryPartnerId;
    @JsonProperty("list_order_ids_assigned")
    private List<Long> listOrderIdsAssigned;
}
