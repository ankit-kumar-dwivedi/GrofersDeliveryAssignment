package com.deliveryScheduler.demo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @author Ankit Dwivedi
 * @version 1.0, 07/07/21
 **/
@Data
public class ScheduledOrdersResponseDTO {
    @ApiModelProperty(notes = "which vehicle type is assigned")
    @JsonProperty("vehicle_type")
    private String     vehicleType;
    @ApiModelProperty(notes = "id of delivery partner assigned")
    @JsonProperty("delivery_partner_id")
    private Long       deliveryPartnerId;
    @ApiModelProperty(notes = "list of order ids assigned to given slot and partner")
    @JsonProperty("list_order_ids_assigned")
    private List<Long> listOrderIdsAssigned;
}
