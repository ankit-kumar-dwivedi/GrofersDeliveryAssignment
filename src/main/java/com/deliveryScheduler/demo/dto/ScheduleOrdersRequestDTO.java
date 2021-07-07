package com.deliveryScheduler.demo.dto;

import lombok.Data;

import java.util.List;

/**
 * @author Ankit Dwivedi
 * @version 1.0, 07/07/21
 **/
@Data
public class ScheduleOrdersRequestDTO {
    private List<OrderDTO> orders;
    private Long slotNumber;

}
