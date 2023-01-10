package com.tfm.tfmbackend.spring.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@ComponentScan(basePackages = "com.tfm.tfmbackend")
@EnableJpaRepositories(value = "com.tfm.tfmbackend.repository")
@EntityScan(value = "com.tfm.tfmbackend.entity")
@EnableWebMvc
public class CommonConfig {
}
