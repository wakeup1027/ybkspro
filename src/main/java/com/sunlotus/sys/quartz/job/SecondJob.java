package com.sunlotus.sys.quartz.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.sunlotus.common.model.TaskConfig;

public class SecondJob implements Job{

	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		TaskConfig qco = TaskConfig.dao.findById(1);
		/**
		 * 如果小于0或等于0，就说明是读秒完了，应该到开奖的时间了。
		 * 又有一种情况是：距离不够十分钟就开奖了，需要秒定时器等完开完一期之后再读秒，这种情况一般是结束开奖时又开始恢复开奖回来才出现这种情况
		 */
		if(qco.getInt("second")>=1){
			qco.set("second", qco.getInt("second")-1);//倒计时1秒直到为0
	    	qco.update();
		}else if(qco.getInt("second")==0){//到0的时候，需要恢复成10分钟回来 
			qco.set("second", 300);//倒计时1秒直到为0
	    	qco.update();
		}
		
	}
	
}
