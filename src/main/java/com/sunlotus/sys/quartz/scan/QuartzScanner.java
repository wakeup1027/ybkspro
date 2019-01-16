package com.sunlotus.sys.quartz.scan;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;

import org.quartz.CronScheduleBuilder;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerKey;
import org.quartz.impl.StdSchedulerFactory;

import com.sunlotus.common.model.TaskConfig;
import com.sunlotus.sys.quartz.job.OpenNumberJob;
import com.sunlotus.sys.quartz.job.SecondJob;

public class QuartzScanner {
	private Scheduler scheduler = null;
	private static QuartzScanner instance = null;
	
	public void Quzar(){
        try {
        	//如果是直接断开服务器，而不是停止开奖器之后再停服务器，所以我们需要在开启服务器的时候更新倒计时
        	TaskConfig qco = TaskConfig.dao.findById(1);
        	
			scheduler = StdSchedulerFactory.getDefaultScheduler();
			//加入调度任务1
			JobDetail OpenJob = newJob(OpenNumberJob.class).withIdentity("openjob", "opengroup").build();
			Trigger OpenTrigger = newTrigger().withIdentity("openjob", "opengroup").withSchedule(CronScheduleBuilder.cronSchedule("0 0/5 * * * ?").withMisfireHandlingInstructionDoNothing()).build();
			
			//加入调度任务2
			JobDetail SecondJob = newJob(SecondJob.class).withIdentity("secondjob", "secondgroup").build();
			Trigger SecondTrigger = newTrigger().withIdentity("secondjob", "secondgroup").withSchedule(CronScheduleBuilder.cronSchedule("0/1 * * * * ?").withMisfireHandlingInstructionDoNothing()).build();
			
			if("NORMAL".equals(qco.getStr("status"))){//如果等于开启状态，则加入
				scheduler.scheduleJob(OpenJob, OpenTrigger);
				scheduler.scheduleJob(SecondJob, SecondTrigger);
				qco.set("second", 300);//正常的话就设置600秒，让秒针读取
			}else{
				qco.set("second", -1);//设置这个是不让秒定时器读秒，除非执行开奖之后，秒针才开始重新读
			}
        	qco.update();
            scheduler.start();
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
	}
	
	//暂停计时器的方法
	/*public void stopQuzar() throws SchedulerException{
        	scheduler = StdSchedulerFactory.getDefaultScheduler();
        	//定时器1
        	JobKey jk1 = JobKey.jobKey("openjob", "opengroup");
        	scheduler.pauseJob(jk1);
        	//定时器2
        	JobKey jk2 = JobKey.jobKey("secondjob", "secondgroup");
        	scheduler.pauseJob(jk2);

			TaskConfig qco = TaskConfig.dao.findById(1);
        	qco.set("status", "PAUSED");//暂停状态
        	qco.update();
	}*/
	
	//重新开启计时器的方法
	/*public void starQuzar() throws SchedulerException{
			TaskConfig qco = TaskConfig.dao.findById(1);
			scheduler = StdSchedulerFactory.getDefaultScheduler();
        	//恢复定时器1
        	JobKey jk1 = JobKey.jobKey("openjob", "opengroup");
        	scheduler.resumeJob(jk1);
        	//恢复定时器2
        	JobKey jk2 = JobKey.jobKey("secondjob", "secondgroup");
        	scheduler.resumeJob(jk2);
        	
        	qco.set("status", "NORMAL");//正常状态
        	qco.update();
	}*/
	
	//往调度中添加任务
	public void addQuzar() throws SchedulerException{
		 try {
			scheduler = StdSchedulerFactory.getDefaultScheduler();

			//加入调度任务1
			JobDetail OpenJob = newJob(OpenNumberJob.class).withIdentity("openjob", "opengroup").build();
			Trigger OpenTrigger = newTrigger().withIdentity("openjob", "opengroup").withSchedule(CronScheduleBuilder.cronSchedule("0 0/5 * * * ?").withMisfireHandlingInstructionDoNothing()).build();
			
			//加入调度任务2
			JobDetail SecondJob = newJob(SecondJob.class).withIdentity("secondjob", "secondgroup").build();
			Trigger SecondTrigger = newTrigger().withIdentity("secondjob", "secondgroup").withSchedule(CronScheduleBuilder.cronSchedule("0/1 * * * * ?").withMisfireHandlingInstructionDoNothing()).build();
			
			scheduler.scheduleJob(OpenJob, OpenTrigger);
			scheduler.scheduleJob(SecondJob, SecondTrigger);
			
			TaskConfig qco = TaskConfig.dao.findById(1);
			qco.set("second", -1);//设置这个是不让秒定时器读秒，除非执行开奖之后，秒针才开始重新读
        	qco.set("status", "NORMAL");//正常状态
        	qco.update();
	        
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
	}
	
	//移除调度中的任务
	public void removeQuzar() throws SchedulerException{
		try {
			scheduler = StdSchedulerFactory.getDefaultScheduler();
			
			//删除任务1
			TriggerKey triggerKey1 = TriggerKey.triggerKey("openjob", "opengroup");
			scheduler.pauseTrigger(triggerKey1);// 停止触发器  
			scheduler.unscheduleJob(triggerKey1);// 移除触发器  
			scheduler.deleteJob(JobKey.jobKey("openjob", "opengroup"));// 删除任务
			
			//删除任务2
			TriggerKey triggerKey2 = TriggerKey.triggerKey("secondjob", "secondgroup");
			scheduler.pauseTrigger(triggerKey2);// 停止触发器  
			scheduler.unscheduleJob(triggerKey2);// 移除触发器  
			scheduler.deleteJob(JobKey.jobKey("secondjob", "secondgroup"));// 删除任务
			
			TaskConfig qco = TaskConfig.dao.findById(1);
			qco.set("second", -1);//设置这个是不让秒定时器读秒，除非执行开奖之后，秒针才开始重新读
        	qco.set("status", "PAUSED");//暂停状态
        	qco.update();
			
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
	}
	
	//获取对象
	public static QuartzScanner getInstance() {
		instance = new QuartzScanner();
		return instance;
	}
	
}
