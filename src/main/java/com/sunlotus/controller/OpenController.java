package com.sunlotus.controller;

import com.jfinal.core.Controller;
import com.sunlotus.common.model.OpenNumber;
import com.sunlotus.service.OpenService;
import com.sunlotus.sys.until.FormString;

public class OpenController extends Controller {

	public void index(){
		render("/html/open/index.html");
	}
	
	/**
	 * 主页加载全部数据
	 */
	public void tabledate(){
		renderJson(new OpenService().LoadTableData(getParaToInt("page"), getParaToInt("limit")));
	}

	
	/**
	 * 删除数据
	 */
	public void delet(){
		renderJson(new OpenService().delUser(getPara("delId")));
	}
	
	/**
	 * 新增数据
	 */
	public void save(){
		renderJson(new OpenService().savaUser(getModel(OpenNumber.class,"form")));
	}
	
	/**
	 * 加载期号
	 */
	public void loadQh(){
		renderJson(FormString.BuilderQihao());
	}
}
