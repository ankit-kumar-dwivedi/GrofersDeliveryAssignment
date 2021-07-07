package com.deliveryScheduler.demo.database.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Ankit Dwivedi
 * @version 1.0, 06/07/21
 **/
@Data
@Entity
@Table(name = "orders")
public class Orders {

    @Id
    @Column(name = "id")
    Long id;

    @Column(name = "weight", nullable = false)
    private Long weight;

}
