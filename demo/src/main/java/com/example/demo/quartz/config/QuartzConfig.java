package com.example.demo.quartz.config;

import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.demo.quartz.MyQuartzTask;
import com.example.demo.quartz.TestQuartzTask;

@Configuration
public class QuartzConfig {

	@Bean
	public JobDetail uploadTaskDetail() {
		return JobBuilder.newJob(MyQuartzTask.class).
				withIdentity("myQuartzTask").storeDurably().build();
	}
	
	@Bean
	public Trigger uploadTaskTrigger() {
		CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule("0 0 0/5 * * ?");
		return TriggerBuilder.newTrigger().forJob(uploadTaskDetail())
				.withIdentity("myQuartzTask")
				.withSchedule(scheduleBuilder)
				.build();
	}
	
	@Bean
	public JobDetail testQuartzUploadTaskDetail() {
		return JobBuilder.newJob(TestQuartzTask.class).
				withIdentity("testQuartzTask").storeDurably().build();
	}
	
	@Bean
	public Trigger testQuartzUploadTaskTrigger() {
		CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule("0 0 0/5 * * ?");
		return TriggerBuilder.newTrigger().forJob(testQuartzUploadTaskDetail())
				.withIdentity("testQuartzTask")
				.withSchedule(scheduleBuilder)
				.build();
	}
}
