package com.example.demo.receiver;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
//@RabbitListener(queues = "hello_multi")
@RabbitListener(queues = "topic.messages")
public class HelloReceiver2 {

	@RabbitHandler
	public void process(String message) {
		System.out.println();
		System.out.println("Receiver2: " + message);
		System.out.println();
	}
}
