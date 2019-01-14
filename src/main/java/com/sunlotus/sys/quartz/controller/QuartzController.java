package com.sunlotus.sys.quartz.controller;

import org.quartz.SchedulerException;

import com.alibaba.fastjson.JSONObject;
import com.jfinal.aop.Before;
import com.jfinal.core.Controller;
import com.sunlotus.common.UserInterceptor;
import com.sunlotus.common.model.TaskConfig;
import com.sunlotus.sys.quartz.scan.QuartzScanner;

@Before(UserInterceptor.class)
public class QuartzController extends Controller{
	
	public void index(){
		TaskConfig qco = TaskConfig.dao.findById(1);
		setAttr("qurze_status", qco);
		render("/html/quartz/quartz_config.html");
	}
	
	public void doSomeThin(){
		String status = getPara("status");
		if("PAUSED".equals(status)){
			stopQuzar();
		}
	}
	
	/**
	 * 恢复任务
	 */
	public void starQuzar(){
		JSONObject json = new JSONObject();
		try {
			QuartzScanner.getInstance().addQuzar();
			json.put("status", true);
		} catch (SchedulerException e) {
			e.printStackTrace();
			json.put("msg", "恢复任务失败，请稍后再试");
			json.put("status", false);
		}
		renderJson(json);
	}
	
	/**
	 * 暂停任务
	 */
	public void stopQuzar(){
		JSONObject json = new JSONObject();
		try {
			QuartzScanner.getInstance().removeQuzar();
			json.put("status", true);
		} catch (SchedulerException e) {
			e.printStackTrace();
			json.put("msg", "暂停任务失败，请稍后再试");
			json.put("status", false);
		}
		renderJson(json);
	}
	
}
