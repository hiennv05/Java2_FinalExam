package com.vti.rw41.FinalExam.configuration;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ComponentConfiguration {
    @Bean
    public ModelMapper init() {
        return new ModelMapper();
    }
}
