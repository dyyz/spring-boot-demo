package com.example.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.demo.sender.HelloSender;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoRabbitmqApplicationTests {

	@Autowired HelloSender helloSender;
	@Autowired HelloSender helloSender2;
	
//	@Test
//	public void contextLoads() {
//		helloSender.send();
//	}
//	
//	@Test
//	public void manyReceiver() {
//		for(int i = 0; i < 100; i++) {
//			helloSender.sendMulti(i);
//			helloSender2.sendMulti(i);
//		}
//	}

//	@Test
//	public void testTopicExchange() {
//		helloSender.sendForTopicMessage();
//	}
//	
//	@Test
//	public void testTopicExchange2() {
//		helloSender.sendForTopicMessages();
//	}
//	
//	@Test
//	public void testTopic() {
//		helloSender.sendForTopic();
//	}
	
	@Test
	public void testTopicByCar() {
		helloSender.sendForTopicByCar();
	}
}
