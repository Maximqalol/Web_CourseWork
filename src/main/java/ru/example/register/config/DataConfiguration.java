package ru.example.register.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * @author Maxim Komov
 * DataConfiguration
 */
@Configuration
@EntityScan(basePackages = "ru.example.register.db.entity")
@EnableJpaRepositories(basePackages = "ru.example.register.db.repository")
public class DataConfiguration {
}