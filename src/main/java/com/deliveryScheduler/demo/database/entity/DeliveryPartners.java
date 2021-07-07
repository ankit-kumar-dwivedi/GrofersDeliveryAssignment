package com.deliveryScheduler.demo.database.entity;

import javax.persistence.*;

/**
 * @author Ankit Dwivedi
 * @version 1.0, 06/07/21
 **/
@Entity
@Table(name = "delivery_partners")
public class DeliveryPartners extends AbstractEntity {

    private String name;

    @Lob
    @JoinColumn(name = "delivery_vehicles_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = DeliveryVehicles.class)
    private DeliveryVehicles vehicle;

    @Column(name = "name", nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public DeliveryVehicles getVehicle() {
        return vehicle;
    }

    public void setVehicle(DeliveryVehicles vehicle) {
        this.vehicle = vehicle;
    }
}
