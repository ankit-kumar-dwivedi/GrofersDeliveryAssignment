package com.deliveryScheduler.demo.database.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author Ankit Dwivedi
 * @version 1.0, 06/07/21
 **/
@Entity
@Table(name = "orders")
public class Orders extends AbstractEntity {

    @Column(name = "weight", nullable = false)
    private Long weight;

    public Long getWeight() {
        return weight;
    }

    public void setWeight(Long weight) {
        this.weight = weight;
    }
}
