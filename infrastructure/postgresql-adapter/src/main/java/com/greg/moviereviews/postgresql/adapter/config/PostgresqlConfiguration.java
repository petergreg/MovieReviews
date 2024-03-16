package com.greg.moviereviews.postgresql.adapter.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@ComponentScan("com.greg.moviereviews.postgresql.adapter")
@Configuration
@EnableAutoConfiguration
public class PostgresqlConfiguration {}
