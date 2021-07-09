package com.deliveryScheduler.demo.validation;

import com.deliveryScheduler.demo.dto.OrderDTO;
import com.deliveryScheduler.demo.dto.ScheduleOrdersRequestDTO;
import com.deliveryScheduler.demo.exception.ApiException;
import org.apache.commons.collections4.CollectionUtils;

/**
 * @author Ankit Dwivedi
 * @version 1.0, 07/07/21
 **/
public class Validator {
    public static void validate(ScheduleOrdersRequestDTO requestDTO) {
        if (CollectionUtils.isEmpty(requestDTO.getOrders())) {
            throw new ApiException("400","Bad request orders cannot be empty");
        }
        for (OrderDTO order : requestDTO.getOrders()) {
            if (order.getOrderId() == null) {
                throw new ApiException("400","Bad request orders id cannot be empty");
            }
            if (order.getOrderWeight() == null) {
                throw new ApiException("400","Bad request orders weight cannot be empty");
            }
            if (order.getOrderWeight() > 100L) {
                throw new ApiException("400","Weight greater than 100 cannot be served");
            }
        }
    }
}
