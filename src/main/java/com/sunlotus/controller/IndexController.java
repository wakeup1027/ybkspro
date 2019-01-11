package com.sunlotus.controller;

import com.jfinal.core.Controller;
import com.sunlotus.common.model.TaskConfig;

public class IndexController extends Controller {

	public void index(){
		TaskConfig tc = TaskConfig.dao.findById(1);
		if(tc.getInt("second")==-1){
			setAttr("tc", -1);
		}else{
			setAttr("tc", tc.getInt("second")*1000);
		}
		render("/index2.html");
	}
}
