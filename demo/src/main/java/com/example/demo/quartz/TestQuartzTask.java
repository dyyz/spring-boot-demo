package com.example.demo.quartz;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.quartz.QuartzJobBean;

@DisallowConcurrentExecution
public class TestQuartzTask extends QuartzJobBean {

	private static final Logger LOG = LoggerFactory.getLogger(TestQuartzTask.class);
	
	@Override
	protected void executeInternal(JobExecutionContext arg0) throws JobExecutionException {
		// TODO Auto-generated method stub

		try {
        	LOG.info("s01-"+Thread.currentThread().getName());
//            TimeUnit.SECONDS.sleep(5000);
            Thread.sleep(6000);
            LOG.info("s02-"+Thread.currentThread().getName());
        } catch (Exception e) {
            e.printStackTrace();
        }
	}

}
