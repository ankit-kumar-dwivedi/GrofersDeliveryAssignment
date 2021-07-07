package com.deliveryScheduler.demo.database.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * @author Ankit Dwivedi
 * @version 1.0, 06/07/21
 **/
@Data
@Entity
@Table(name = "order_delivery")
public class OrderDelivery extends AbstractEntity {

    @JoinColumn(name = "order_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Orders order;

    @JoinColumn(name = "delivery_partners_id", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private DeliveryPartners deliveryPartner;

    @JoinColumn(name = "slot_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Slot slot;

}
