package com.example.workout;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Bean;
import org.testcontainers.containers.PostgreSQLContainer;

@TestConfiguration(proxyBeanMethods = false)
public class TestcontainersConfiguration {
    @Bean
    @ServiceConnection
    PostgreSQLContainer<?> container() {
        return new PostgreSQLContainer<>("postgres:16");
    }

    public static void main(String[] args) {
        SpringApplication.from(TestcontainersConfiguration::main).with(TestcontainersConfiguration.class).run(args);
    }
}
