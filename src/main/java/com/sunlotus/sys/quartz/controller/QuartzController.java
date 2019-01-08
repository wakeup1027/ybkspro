package com.sunlotus.sys.quartz.controller;

import org.quartz.SchedulerException;

import com.alibaba.fastjson.JSONObject;
import com.jfinal.core.Controller;
import com.sunlotus.sys.quartz.scan.QuartzScanner;

public class QuartzController extends Controller{
	
	public void index(){
		render("/html/quartz/quartz_config.html");
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
