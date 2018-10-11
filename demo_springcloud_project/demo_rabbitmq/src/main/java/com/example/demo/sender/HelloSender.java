package com.example.demo.sender;

import java.time.LocalDateTime;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class HelloSender {

	@Autowired private AmqpTemplate amqpTemplate;
	
	public void send() {
		String context = "Hello ------------" + LocalDateTime.now();
		System.out.println("send: " + context);
		this.amqpTemplate.convertAndSend("hello", context);
	}
	
	public void sendMulti(int i) {
		String context = i + "";
		System.out.println(context + " --- send: ");
		this.amqpTemplate.convertAndSend("hello_multi", context);
	}
	
	public void sendForTopicMessage() {
		String context = "I am message. My routingKey is topic.message";
		System.out.println(" sendForTopicMessage send");
		this.amqpTemplate.convertAndSend("exchange", "topic.message", context);
	}
	
	public void sendForTopicMessages() {
		String context = "I am messages. My routingKey is topic.messages";
		System.out.println("sendForTopicMessages send");
		this.amqpTemplate.convertAndSend("exchange", "topic.messages", context);
	}
	
	public void sendForTopic() {
		String context = "I am messages. My routingKey is topics";
		System.out.println( " sendForTopic send ");
		this.amqpTemplate.convertAndSend("exchange", "topics", context);
	}
	
	public void sendForTopicByCar() {
		String context = "I am messages. My routingKey is topic.*.car";
		System.out.println( " sendForTopic send ");
		this.amqpTemplate.convertAndSend("exchange", "topic.*.car", context);
	}
}
