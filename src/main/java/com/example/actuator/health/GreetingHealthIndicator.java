package com.example.actuator.health;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.example.model.Greeting;
import com.example.service.GreetingService;

@Component
public class GreetingHealthIndicator implements HealthIndicator {

    @Autowired
    private GreetingService greetingService;

    @Override
    public Health health() {

        List<Greeting> greetings = greetingService.findAll();

        if (CollectionUtils.isEmpty(greetings)) {
            return Health.down().withDetail("count", 0).build();
        }

        return Health.up().withDetail("count", greetings.size()).build();
    }

}
