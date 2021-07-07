package com.deliveryScheduler.demo.database;

import com.deliveryScheduler.demo.database.entity.DeliveryPartners;
import com.deliveryScheduler.demo.database.entity.DeliveryVehicles;
import com.deliveryScheduler.demo.database.entity.Slot;
import com.deliveryScheduler.demo.database.entity.VehicleSlot;
import com.deliveryScheduler.demo.database.repository.DeliveryPartnerRepository;
import com.deliveryScheduler.demo.database.repository.DeliveryVehiclesRepository;
import com.deliveryScheduler.demo.database.repository.SlotRepository;
import com.deliveryScheduler.demo.database.repository.VehicleSlotRepository;
import com.deliveryScheduler.demo.enums.VehicleTypeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

/**
 * @author Ankit Dwivedi
 * @version 1.0, 07/07/21
 **/
@Component
public class InitializeEntities {

    @Autowired
    DeliveryVehiclesRepository deliveryVehiclesRepository;

    @Autowired
    DeliveryPartnerRepository deliveryPartnerRepository;

    @Autowired
    VehicleSlotRepository vehicleSlotRepository;

    @Autowired
    SlotRepository slotRepository;

    @PostConstruct
    void init() {
        initializeEntities();
    }

    // initialize data when restarting server
    void initializeEntities() {
        DeliveryVehicles deliveryBikes = new DeliveryVehicles();
        deliveryBikes.setVehicleType(VehicleTypeEnum.BIKE);
        deliveryBikes.setCapacity(30L);
        deliveryBikes.setDailyCount(3L);
        deliveryBikes = deliveryVehiclesRepository.save(deliveryBikes);
        initializePartners(deliveryBikes);

        DeliveryVehicles deliveryScooters = new DeliveryVehicles();
        deliveryScooters.setVehicleType(VehicleTypeEnum.SCOOTER);
        deliveryScooters.setCapacity(50L);
        deliveryScooters.setDailyCount(2L);
        deliveryScooters = deliveryVehiclesRepository.save(deliveryScooters);
        initializePartners(deliveryScooters);

        DeliveryVehicles deliveryTrucks = new DeliveryVehicles();
        deliveryTrucks.setVehicleType(VehicleTypeEnum.TRUCK);
        deliveryTrucks.setCapacity(100L);
        deliveryTrucks.setDailyCount(1L);
        deliveryTrucks = deliveryVehiclesRepository.save(deliveryTrucks);
        initializePartners(deliveryTrucks);

        Slot slot1 = new Slot();
        slot1.setId(1L);
        slot1.setCapacity(100L);
        slot1.setStartTime(getLocalTime("06:00:00"));
        slot1.setEndTime(getLocalTime("09:00:00"));
        slotRepository.save(slot1);

        Slot slot2 = new Slot();
        slot2.setId(2L);
        slot2.setCapacity(100L);
        slot2.setStartTime(getLocalTime("09:00:00"));
        slot2.setEndTime(getLocalTime("13:00:00"));
        slotRepository.save(slot2);

        Slot slot3 = new Slot();
        slot3.setId(3L);
        slot3.setCapacity(100L);
        slot3.setStartTime(getLocalTime("16:00:00"));
        slot3.setEndTime(getLocalTime("19:00:00"));
        slotRepository.save(slot3);

        Slot slot4 = new Slot();
        slot4.setId(4L);
        slot4.setCapacity(100L);
        slot4.setStartTime(getLocalTime("19:00:00"));
        slot4.setEndTime(getLocalTime("23:00:00"));
        slotRepository.save(slot4);

        initializeVehicleSlot(deliveryBikes, Arrays.asList(slot1, slot2, slot3));
        initializeVehicleSlot(deliveryScooters, Arrays.asList(slot1, slot2, slot3));
        initializeVehicleSlot(deliveryTrucks, Arrays.asList(slot2, slot3, slot4));
    }

    private void initializePartners(DeliveryVehicles vehicles) {
        for (long i = 0; i < vehicles.getDailyCount(); ++i) {
            DeliveryPartners deliveryPartners = new DeliveryPartners();
            deliveryPartners.setVehicle(vehicles);
            deliveryPartners.setName("Partner " + vehicles.getVehicleType() + i);
            deliveryPartnerRepository.save(deliveryPartners);
        }
    }

    private void initializeVehicleSlot(DeliveryVehicles vehicle, List<Slot> slotList) {
        for (Slot slot : slotList) {
            VehicleSlot vehicleSlot = new VehicleSlot();
            vehicleSlot.setSlot(slot);
            vehicleSlot.setVehicle(vehicle);
            vehicleSlotRepository.save(vehicleSlot);
        }
    }

    private LocalTime getLocalTime(String format) {
        return LocalTime.parse(format, DateTimeFormatter.ISO_TIME);
    }
}
