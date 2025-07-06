package com.capg.config;

import org.modelmapper.ModelMapper;

import org.modelmapper.PropertyMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.capg.dto.LensesDTO;
import com.capg.dto.ProductDTO;

@Configuration
public class AppConfig {
	@Bean
	public ModelMapper modelMapper() {

		ModelMapper modelMapper = new ModelMapper();

		return new ModelMapper();
	}

}
