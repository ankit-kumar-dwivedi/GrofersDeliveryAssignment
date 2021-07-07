package com.deliveryScheduler.demo.database.entity;

import com.deliveryScheduler.demo.enums.VehicleTypeEnum;

import javax.persistence.*;

/**
 * @author Ankit Dwivedi
 * @version 1.0, 06/07/21
 **/

@Entity
@Table(name = "delivery_vehicles")
public class DeliveryVehicles extends AbstractEntity {


    @Column(name = "vehicle_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private VehicleTypeEnum vehicleType;

    @Column(name = "capacity", nullable = false)
    private Long capacity;

    @Column(name = "daily_count", nullable = false)
    private Long dailyCount;

    public VehicleTypeEnum getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(VehicleTypeEnum vehicleType) {
        this.vehicleType = vehicleType;
    }

    public Long getCapacity() {
        return capacity;
    }

    public void setCapacity(Long capacity) {
        this.capacity = capacity;
    }

    public Long getDailyCount() {
        return dailyCount;
    }

    public void setDailyCount(Long dailyCount) {
        this.dailyCount = dailyCount;
    }
}
