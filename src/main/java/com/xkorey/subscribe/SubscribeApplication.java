package com.xkorey.subscribe;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class SubscribeApplication {


	public static void main(String[] args) {
		SpringApplication.run(SubscribeApplication.class, args);
	}


	@Bean
	RestTemplate getRestTemplate(){
		RestTemplate restTemplate = new RestTemplate();
		return restTemplate;
	}

//	@Bean
//	ObjectMapper jacksonObjectMapper(Jackson2ObjectMapperBuilder builder){
//		ObjectMapper objectMapper = builder.createXmlMapper(false).build();
//		objectMapper.setPropertyNamingStrategy(com.fasterxml.jackson.databind.PropertyNamingStrategy.SNAKE_CASE);
//		return objectMapper;
//	}

}
