package com.deliveryScheduler.demo.service;

import com.deliveryScheduler.demo.enums.SchedulerStrategyEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author Ankit Dwivedi
 * @version 1.0, 07/07/21
 **/
@Component
public class SchedulerStrategyFactory {
    private Map<SchedulerStrategyEnum, SchedulerStrategy> strategies;

    @Autowired
    public SchedulerStrategyFactory(Set<SchedulerStrategy> strategySet) {
        createStrategy(strategySet);
    }

    public SchedulerStrategy findStrategy(SchedulerStrategyEnum strategyName) {
        return strategies.get(strategyName);
    }

    private void createStrategy(Set<SchedulerStrategy> strategySet) {
        strategies = new HashMap<SchedulerStrategyEnum, SchedulerStrategy>();
        strategySet.forEach(strategy -> strategies.put(strategy.getName(), strategy));
    }


}
