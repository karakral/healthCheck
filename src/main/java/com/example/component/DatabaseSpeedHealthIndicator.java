package com.example.component;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.Statement;
import java.util.Map;

@Component
public class DatabaseSpeedHealthIndicator implements HealthIndicator {

    @Autowired
    private Map<String, HealthIndicator> healthIndicators;
    private final ApplicationContext applicationContext;

    private final DataSource dataSource;

    public DatabaseSpeedHealthIndicator(DataSource dataSource, ApplicationContext applicationContext) {
        this.dataSource = dataSource;
        this.applicationContext = applicationContext;
    }

    @PostConstruct
    public void logHealthIndicators() {
        Map<String, HealthIndicator> indicators = applicationContext.getBeansOfType(HealthIndicator.class);
        System.out.println("Registered Health Indicators: " + indicators.keySet());
    }

    @Override
    public Health health() {
        System.out.println("DatabaseSpeedHealthIndicator is being invoked!");
        long startTime = System.currentTimeMillis();
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()) {
            statement.executeQuery("SELECT 1"); // Test query
            long elapsedTime = System.currentTimeMillis() - startTime;

            // Threshold: Adjust as per your project's normal speed
            long threshold = 500; // in milliseconds
            if (elapsedTime > threshold) {
                System.out.println("wrong wrong wrong ");
                return Health.down().withDetail("responseTime", elapsedTime + "ms").build();
           }
            System.out.println("health health health ");
            return Health.up().withDetail("responseTime", elapsedTime + "ms").build();
        } catch (Exception e) {
            return Health.down(e).build();
        }
    }

}
