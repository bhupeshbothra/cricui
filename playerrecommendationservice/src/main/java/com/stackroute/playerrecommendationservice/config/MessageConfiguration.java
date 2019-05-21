package com.stackroute.playerrecommendationservice.config;

import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MessageConfiguration {

	@Bean
	public Jackson2JsonMessageConverter producerJackon2jsonMessageConverter() {
		return new Jackson2JsonMessageConverter();
	}
	
}
