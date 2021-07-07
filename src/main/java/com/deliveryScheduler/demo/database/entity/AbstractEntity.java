package com.deliveryScheduler.demo.database.entity;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * @author Ankit Dwivedi
 * @version 1.0, 06/07/21
 **/


@MappedSuperclass
public abstract class AbstractEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private Long              id;

    @CreatedDate
    @Column(name = "created", columnDefinition = "timestamp default current_timestamp", updatable = false)
    private Date created;

    @LastModifiedDate
    @Column(name = "updated", columnDefinition = "timestamp default current_timestamp on update current_timestamp", updatable = false)
    private Date              updated;

    @Column(name = "deleted", columnDefinition = "tinyint(1) default 0")
    private boolean           deleted;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public AbstractEntity id(Long id) {
        this.id = id;
        return this;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }
}