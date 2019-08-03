package com.xkorey.subscribe;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.xkorey.subscribe.pojo.dto.BackUser;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@EnableScheduling
public class SubscribeApplication {



	public static void main(String[] args) {
		SpringApplication.run(SubscribeApplication.class, args);
	}


	@Bean
	RestTemplate getRestTemplate(){
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter(){

			@Override
			public void setSupportedMediaTypes(List<MediaType> supportedMediaTypes) {
				List<MediaType> tmp = new ArrayList<>(1);
				tmp.add(MediaType.TEXT_PLAIN);
				super.setSupportedMediaTypes(tmp);
			}
		});
		return restTemplate;
	}

	@Bean
	Cache applicationCache(){
		return CacheBuilder.newBuilder()
				.maximumSize(1000)
				.build();
	}

	@Bean
	ThreadLocal<BackUser> currentUser(){
		ThreadLocal<BackUser> userThreadLocal = new ThreadLocal<>();
		return userThreadLocal;
	}

	@Configuration
	class InterceptorConfig extends WebMvcConfigurerAdapter {

		@Autowired
		UserInterceptor userInterceptor;

		@Override
		public void addInterceptors(InterceptorRegistry registry) {
			registry.addInterceptor(userInterceptor).addPathPatterns("/back/**");
			super.addInterceptors(registry);
		}
	}


//	@Bean
//	ObjectMapper jacksonObjectMapper(Jackson2ObjectMapperBuilder builder){
//		ObjectMapper objectMapper = builder.createXmlMapper(false).build();
//		objectMapper.setPropertyNamingStrategy(com.fasterxml.jackson.databind.PropertyNamingStrategy.SNAKE_CASE);
//		return objectMapper;
//	}

}
