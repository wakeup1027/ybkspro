package com.sunlotus.sys.quartz.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.sunlotus.common.model.TaskConfig;

public class OpenNumber implements Job{
	
	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		//更新这个字段，然后让秒定时器读秒
		TaskConfig qco = TaskConfig.dao.findById(1);
    	qco.set("second", 600);//恢复600秒：10分钟
    	qco.update();
    	
		System.out.println("========开奖========");
		
	}

}
