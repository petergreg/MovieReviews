package com.greg.moviereviews.postgresql.adapter.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@ComponentScan("com.greg.moviereviews.postgresql.adapter")
@Configuration
@EnableAutoConfiguration
@EnableJpaRepositories(basePackages = "com.greg.moviereviews.postgresql.adapter.repository")
@EntityScan("com.greg.moviereviews.postgresql.adapter.model")
public class PostgresqlConfiguration {}
