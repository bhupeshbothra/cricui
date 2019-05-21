package com.stackroute.favouriteservice.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class MessagingConfiguration {

	private String exchangename="favirote_exchange";
	private String favitoteAddQueue= "favirote_add_queue";
	private String favitotedeleteQueue= "favirote_delete_queue";
	
	@Bean
	public DirectExchange directExchange() {
		return new DirectExchange(exchangename);
	}
	
	@Bean
	public Queue faviroteAdd() {
		return new Queue(favitoteAddQueue,true);
	}
	
	@Bean
	public Queue faviroteDelete() {
		return new Queue(favitotedeleteQueue,true	);
	}
	
	@Bean
	public Jackson2JsonMessageConverter producerJackon2jsonMessageConverter() {
		return new Jackson2JsonMessageConverter();
	}
	
	@Bean
	public Binding bindingQueues(Queue faviroteAdd, DirectExchange directExchange) {
		
		return BindingBuilder.bind(faviroteAdd).to(directExchange).with("favirote_add_routing");
	}
	
	@Bean
	public Binding bindingTrackQueues(Queue faviroteDelete, DirectExchange directExchange) {
		
		return BindingBuilder.bind(faviroteDelete).to(directExchange).with("favirote_delete_routing");
	}
	
	@Bean
	public RabbitTemplate rabbitTemplate(final ConnectionFactory connectionFactory) {
		RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
		rabbitTemplate.setMessageConverter(producerJackon2jsonMessageConverter());
		return rabbitTemplate;
	}
	

}
