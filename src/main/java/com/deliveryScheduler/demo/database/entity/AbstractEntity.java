package com.deliveryScheduler.demo.database.entity;
import lombok.Getter;

import javax.persistence.*;

/**
 * @author Ankit Dwivedi
 * @version 1.0, 06/07/21
 **/
@MappedSuperclass
@Getter
public abstract class AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Long id;

    @Column(name = "deleted", columnDefinition = "boolean default false")
    private boolean deleted;

    public AbstractEntity setId(Long id) {
        this.id = id;
        return this;
    }

}