package com.deliveryScheduler.demo.database.entity;

import com.deliveryScheduler.demo.enums.AvailabilityEnum;

import javax.persistence.*;

/**
 * @author Ankit Dwivedi
 * @version 1.0, 06/07/21
 **/
@Entity
@Table(name = "vehicle_slot")
public class VehicleSlot extends AbstractEntity {
    @Lob
    @JoinColumn(name = "delivery_vehicles_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = DeliveryVehicles.class)
    private DeliveryVehicles vehicle;

    @Lob
    @JoinColumn(name = "slot_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Slot slot;

    public DeliveryVehicles getVehicle() {
        return vehicle;
    }

    public void setVehicle(DeliveryVehicles vehicle) {
        this.vehicle = vehicle;
    }

    public Slot getSlot() {
        return slot;
    }

    public void setSlot(Slot slot) {
        this.slot = slot;
    }

}
