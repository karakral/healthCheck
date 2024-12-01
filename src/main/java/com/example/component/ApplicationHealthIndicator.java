package com.example.component;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

@Component
public class ApplicationHealthIndicator implements HealthIndicator {

    @Override
    public Health health() {
        System.out.println("Application running is being invoked!");
        return Health.up().withDetail("application", "Running").build();
    }
}
