package com.sunlotus.controller;

import java.util.List;

import com.jfinal.core.Controller;
import com.sunlotus.common.model.Opend_log;
import com.sunlotus.common.model.TaskConfig;

public class IndexController extends Controller {

	public void index(){
		List<Opend_log> opendlog = Opend_log.dao.find("SELECT * FROM opennumber_log ORDER BY create_time DESC LIMIT 200");
		TaskConfig tc = TaskConfig.dao.findById(1);
		if(tc.getInt("second")==-1){
			setAttr("tc", -1);
		}else{
			setAttr("tc", tc.getInt("second")*1000);
		}
		setAttr("opendlog", opendlog);
		Opend_log olg = opendlog.get(0);
		String[] num = olg.getStr("create_open").split(",");
		setAttr("num1", num[0]);
		setAttr("num2", num[1]);
		setAttr("num3", num[2]);
		render("/index2.html");
	}
}
