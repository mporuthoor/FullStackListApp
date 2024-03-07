package com.project.listconstructorbackend.steps.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {"com.project.listconstructorbackend", "com.project.listconstructorbackend.client"})
@EnableAutoConfiguration
public class TestConfig {

}