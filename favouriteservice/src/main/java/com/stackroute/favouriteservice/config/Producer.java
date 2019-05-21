package com.stackroute.favouriteservice.config;

import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.stackroute.favouriteservice.rabittmq.domain.CricDTO;

@Component
public class Producer {

	private RabbitTemplate rabbitTemplate;
	private DirectExchange exchange;
	
	@Autowired
	public Producer(RabbitTemplate rabbitTemplate, DirectExchange exchange) {
		this.exchange = exchange;
		this.rabbitTemplate = rabbitTemplate;
	}
	
	public void sendAddFaviroteToMQ(CricDTO cricDTO) {
		System.out.println("in send MessagetoMQ" + exchange.getName());
		
		rabbitTemplate.convertAndSend(exchange.getName(), "favirote_add_routing" , cricDTO);
	}
	
	public void sendDeleteFaviroteToMQ(CricDTO cricDTO) {
		
		rabbitTemplate.convertAndSend(exchange.getName(), "favirote_delete_routing", cricDTO);
	}
}
