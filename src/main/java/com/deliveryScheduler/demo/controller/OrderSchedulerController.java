package com.deliveryScheduler.demo.controller;

import com.deliveryScheduler.demo.Validation.Validator;
import com.deliveryScheduler.demo.dto.OrderDTO;
import com.deliveryScheduler.demo.dto.Response;
import com.deliveryScheduler.demo.dto.ScheduleOrdersRequestDTO;
import com.deliveryScheduler.demo.dto.ScheduledOrdersResponseDTO;
import com.deliveryScheduler.demo.service.SchedulerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Ankit Dwivedi
 * @version 1.0, 06/07/21
 **/

@RestController
@RequestMapping(value = "/order-schedule")
public class OrderSchedulerController {

    @Autowired
    private SchedulerService schedulerService;

    private static final Logger log = LoggerFactory.getLogger(OrderSchedulerController.class);

    @PostMapping("")
    public Response<HttpStatus> scheduleOrders(@RequestBody ScheduleOrdersRequestDTO requestDTO) {
        log.info("schedule orders request:{}", requestDTO);
        Validator.validate(requestDTO);
        schedulerService.scheduleOrders(requestDTO);
        return new Response<>(HttpStatus.OK);
    }

    @GetMapping("")
    public Response<List<ScheduledOrdersResponseDTO>> fetchScheduledOrders(@RequestParam("slotNumber") Long slotNumber) {
        return new Response<>(schedulerService.fetchScheduledOrders(slotNumber));
    }

}
