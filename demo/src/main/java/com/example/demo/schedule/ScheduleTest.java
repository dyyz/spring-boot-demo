package com.example.demo.schedule;

//import java.text.DateFormat;
//import java.text.SimpleDateFormat;
//import java.util.Date;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.scheduling.annotation.Async;
//import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduleTest {

//	private static final Logger LOG = LoggerFactory.getLogger(ScheduleTest.class);
	
//	@Scheduled(cron = "0 0/1 * * * ?")
//	public void executeTaskTest() {
//		Thread current = Thread.currentThread();
//		System.out.println("定时任务1：" + current.getId());
//		LOG.info("ScheduledTest.executeTaskTest 定时任务1:" + current.getId() + ", name:"+current.getName());
//	}
//	
//	@Scheduled(cron="0/2 * * * * ? ")   //每2秒执行一次 
//    public void testCron2() {
//       DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
//       LOG.info(sdf.format(new Date())+"*********每2秒执行一次");
//    }
//   
//	@Scheduled(cron = "0/10 * * ? * ?")
//    @Async
//    public void test00() {
//        try {
//        	LOG.info("s01-"+Thread.currentThread().getName());
////            TimeUnit.SECONDS.sleep(12);
//            Thread.sleep(1000*20);
//            LOG.info("s02-"+Thread.currentThread().getName());
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
}
