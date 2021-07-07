package com.deliveryScheduler.demo.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @author Ankit Dwivedi
 * @version 1.0, 07/07/21
 **/
@Data
public class ScheduleOrdersRequestDTO {
    @ApiModelProperty(notes = "list of orders")
    private List<OrderDTO> orders;
    @ApiModelProperty(notes = "preferred slot number (optional)")
    private Long slotNumber;

}
