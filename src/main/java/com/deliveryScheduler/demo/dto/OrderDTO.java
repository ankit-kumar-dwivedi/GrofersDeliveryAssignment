package com.deliveryScheduler.demo.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * @author Ankit Dwivedi
 * @version 1.0, 06/07/21
 **/
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderDTO {
    @JsonProperty("order_id")
    private Long orderId;
    @JsonProperty("order_weight")
    private Long orderWeight;

}
