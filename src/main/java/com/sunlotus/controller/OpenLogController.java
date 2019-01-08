package com.sunlotus.controller;

import com.jfinal.core.Controller;
import com.sunlotus.common.model.Opend_log;
import com.sunlotus.service.OpenLogService;

public class OpenLogController extends Controller {

	public void index(){
		render("/html/openlog/index.html");
	}
	
	/**
	 * 主页加载全部数据
	 */
	public void tabledate(){
		renderJson(new OpenLogService().LoadTableData(getParaToInt("page"), getParaToInt("limit")));
	}
	
	/**
	 * 搜索
	 */
	public void sreachdate(){
		renderJson(new OpenLogService().Sreach(getPara("username"), getParaToInt("page"), getParaToInt("limit")));
	}
	
	/**
	 * 搜索加载数据单挑
	 */
	public void findload(){
		renderJson(new OpenLogService().umodel(getPara("findId")));
	}
	
	/**
	 * 修改数据
	 */
	public void update(){
		renderJson(new OpenLogService().updataUser(getModel(Opend_log.class,"form")));
	}
}
