package com.deliveryScheduler.demo.dto;

import com.deliveryScheduler.demo.enums.VehicleTypeEnum;
import lombok.Data;

/**
 * @author Ankit Dwivedi
 * @version 1.0, 07/07/21
 **/
@Data
public class AvailableVehicleDTO {
    private Long partnerId;
    private Long remainingCapacity;
    private Long maxCapacity;
    private VehicleTypeEnum vehicleType;

}
