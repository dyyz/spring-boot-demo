package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.rabbitmq.client.AMQP.Exchange;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;

@Configuration
public class RabbitConfig {

	final static String message = "topic.message";
    final static String messages = "topic.messages";

	@Bean
	public Queue queue() {
		return new Queue("hello");
	}
	
	@Bean
	public Queue multiReceiverQueue() {
		return new Queue("hello_multi");
	}
	
	@Bean
	public Queue queueMessage() {
		return new Queue(RabbitConfig.message);
	}
	
	@Bean
	public Queue queueMessages() {
		return new Queue(RabbitConfig.messages);
	}
	
	@Bean
	public TopicExchange exchange() {
		return new TopicExchange("exchange");
	}
	
	@Bean
	public Binding bind(Queue queueMessage, TopicExchange topicExchange) {
		return BindingBuilder.bind(queueMessage).to(topicExchange).with("topic.message");
	}
	
	@Bean
	public Binding bindMessages(Queue queueMessages, TopicExchange topicExchange) {
		return BindingBuilder.bind(queueMessages).to(topicExchange).with("topic.#");
	}
	
	@Bean
	public Binding bindQueue(Queue queueMessages, TopicExchange topicExchange) {
		return BindingBuilder.bind(queueMessages).to(topicExchange).with("*.*.car");
	}
}
