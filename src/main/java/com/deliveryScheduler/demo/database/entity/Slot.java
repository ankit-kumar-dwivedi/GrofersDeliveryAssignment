package com.deliveryScheduler.demo.database.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalTime;

/**
 * @author Ankit Dwivedi
 * @version 1.0, 06/07/21
 **/
@Entity
@Table(name = "slots")
public class Slot extends AbstractEntity {
    @Column(name = "start_time", columnDefinition = "TIME", nullable = false)
    private LocalTime startTime;
    @Column(name = "end_time", columnDefinition = "TIME", nullable = false)
    private LocalTime      endTime;
    @Column(name = "capacity", nullable = false)
    private Long capacity;

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    public Long getCapacity() {
        return capacity;
    }

    public void setCapacity(Long capacity) {
        this.capacity = capacity;
    }
}
