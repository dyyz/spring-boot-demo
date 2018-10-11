package com.example.demo.receiver;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
//@RabbitListener(queues = {"hello", "hello_multi"})
@RabbitListener(queues = {"topic.message"})
public class HelloReceiver {

	@RabbitHandler
	public void process(String message) {
		System.out.println();
		System.out.println("Receiver: " + message);
		System.out.println();
	}
}
