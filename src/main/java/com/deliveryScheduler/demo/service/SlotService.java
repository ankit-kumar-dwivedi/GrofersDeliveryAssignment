package com.deliveryScheduler.demo.service;

import com.deliveryScheduler.demo.database.entity.Slot;
import com.deliveryScheduler.demo.database.repository.SlotRepository;
import com.deliveryScheduler.demo.exception.ApiException;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * @author Ankit Dwivedi
 * @version 1.0, 06/07/21
 **/
@Log4j2
@Service
public class SlotService {

    @Autowired
    private SlotRepository slotRepository;

    public Slot getSlotForCurrentTime() {
        LocalTime time = LocalTime.now();
        LocalTime maxTime = LocalTime.parse("23:00:01", DateTimeFormatter.ISO_TIME);
        if (maxTime.isAfter(time)) {
            log.warn("cannot accept orders");
            throw new ApiException("Cannot schedule order at the moment try later!");
        }
        Slot slot = slotRepository.findByStartTimeGreaterThanEqualAndEndTimeLessThan(time, maxTime);
        if (slot == null) {
            throw new ApiException("Cannot schedule order at the moment try later!");
        }
        return slot;
    }

    public Slot getSlotByNumber(Long slotNumber) {
       return slotRepository.findByIdAndDeletedFalse(slotNumber);
    }
}
